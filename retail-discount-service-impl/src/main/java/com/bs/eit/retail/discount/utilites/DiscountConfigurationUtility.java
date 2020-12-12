package com.bs.eit.retail.discount.utilites;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import com.bs.eit.retail.discount.models.DiscountConfiguration;
import com.bs.eit.retail.discount.repos.DiscountConfigurationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscountConfigurationUtility {
	@Autowired
	private DiscountConfigurationRepo discountConfiguration;
	private HashMap<String, String> propertyMap;

	@PostConstruct
	public void init() {
		// cached
		List<DiscountConfiguration> findAll = discountConfiguration.findAll();
		propertyMap = new HashMap<>();
		if (findAll != null) {
			findAll.forEach(config -> propertyMap.put(config.getConfigName(), config.getConfigValue()));
		}
	}

	public String getProperty(String propertyKey) {
		return propertyMap.get(propertyKey);
	}
}
