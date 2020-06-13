package com.ups.retail.discount.utilites;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ups.retail.discount.models.DiscountConfiguration;
import com.ups.retail.discount.repos.DiscountConfigurationRepo;

@Component
public class DiscountConfigurationUtility {
	@Autowired
	DiscountConfigurationRepo discountConfiguration;
	HashMap<String, String> propertyMap;

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
