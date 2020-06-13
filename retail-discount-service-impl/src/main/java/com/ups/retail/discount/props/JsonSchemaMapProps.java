package com.ups.retail.discount.props;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:${APP_CONFIG_DIR:./..}/properties/jsonSchemas.properties")
@ConfigurationProperties("")
public class JsonSchemaMapProps {
	private Map<String, String> jsonSchemaPath;

	public Map<String, String> getJsonSchemaPath() {
		return jsonSchemaPath;
	}

	public void setJsonSchemaPath(Map<String, String> jsonSchemaPath) {
		this.jsonSchemaPath = jsonSchemaPath;
	}
}
