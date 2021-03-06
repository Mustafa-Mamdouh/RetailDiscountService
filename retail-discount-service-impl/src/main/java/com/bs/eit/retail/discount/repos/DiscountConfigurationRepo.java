package com.bs.eit.retail.discount.repos;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bs.eit.retail.discount.models.DiscountConfiguration;

public interface DiscountConfigurationRepo extends JpaRepository<DiscountConfiguration, Integer> {
	@Cacheable("configuration")
	List<DiscountConfiguration> findAll();
}
