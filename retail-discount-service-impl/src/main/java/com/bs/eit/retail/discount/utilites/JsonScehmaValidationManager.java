package com.bs.eit.retail.discount.utilites;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.JsonReader;

import org.leadpony.justify.api.JsonSchema;
import org.leadpony.justify.api.JsonValidationService;
import org.leadpony.justify.api.Problem;
import org.leadpony.justify.api.ProblemHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bs.eit.retail.discount.constants.ErrorMessages;
import com.bs.eit.retail.discount.exceptions.ApplicationException;
import com.bs.eit.retail.discount.props.JsonSchemaMapProps;

@Component
public class JsonScehmaValidationManager {

	Map<String, JsonSchema> jsonSchemaMap;
	JsonValidationService service;
	ObjectMapper ObjectMapper = new ObjectMapper();

	@Autowired
	public JsonScehmaValidationManager(JsonSchemaMapProps jsonSchemaMapProps) throws FileNotFoundException {
		Map<String, String> jsonSchemaPath = jsonSchemaMapProps.getJsonSchemaPath();
		service = JsonValidationService.newInstance();
		if (jsonSchemaPath != null) {
			jsonSchemaMap = new HashMap<String, JsonSchema>();
			for (Entry<String, String> entry : jsonSchemaPath.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				JsonSchema schema = service.readSchema(new FileInputStream(value));
				jsonSchemaMap.put(key, schema);
			}
		}
		ObjectMapper.setSerializationInclusion(Include.NON_NULL);
	}

	public void validate(Object jsonObjectParam, String schemaName) {
		List<Problem> problems = new ArrayList<Problem>();

		// Problem handler
		ProblemHandler handler = ProblemHandler.collectingTo(problems);
		String jsonObject = null;
		if (jsonObjectParam instanceof String) {
			jsonObject = (String) jsonObjectParam;
		} else {
			try {
				jsonObject = ObjectMapper.writeValueAsString(jsonObjectParam);
			} catch (JsonProcessingException e) {
				throw new ApplicationException(ErrorMessages.INTERNAL_SERVER_ERROR_Message);
			}
		}

		InputStream jsonObjAsInputStream = new ByteArrayInputStream(jsonObject.getBytes(StandardCharsets.UTF_8));

		try (JsonReader reader = service.createReader(jsonObjAsInputStream, getSchema(schemaName), handler);) {
			reader.readValue();
			for (int i = 0; i < problems.size(); i++) {
				String fullProblemId = problems.get(i).getSchema().id().toString();
				String problemId = fullProblemId.substring(fullProblemId.indexOf('#') + 1);
				String problemPath = problemId.replaceAll("/properties", "");
				if (problems.get(i).getKeyword().equals("required")) {
					String problemMessage = problems.get(i).getMessage();
					String parameterName = problemMessage.substring(problemMessage.indexOf("\"") + 1,
							problemMessage.lastIndexOf("\""));
					throw new ApplicationException(ErrorMessages.Bad_Request,
							"missing parameter " + "/" + parameterName);

				} else {
					throw new ApplicationException(ErrorMessages.Bad_Request,
							"missing parameter format " + problemPath);
				}

			}
		}
	}

	private JsonSchema getSchema(String key) {
		JsonSchema jsonSchema = jsonSchemaMap.get(key);
		if (jsonSchema == null) {
			throw new ApplicationException(ErrorMessages.INTERNAL_SERVER_ERROR_Message);
		}
		return jsonSchema;
	}

}
