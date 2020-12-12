package com.bs.eit.retail.discount.roles;

import com.bs.eit.retail.discount.constants.SystemConstants;
import com.bs.eit.retail.discount.dtos.ItemsDto;
import com.bs.eit.retail.discount.services.interfaces.DiscountRole;
import com.bs.eit.retail.discount.services.types.DiscountContext;
import com.bs.eit.retail.discount.utilites.DiscountConfigurationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Order(30)
class ExculsionDiscountRole implements DiscountRole {
    private Set<String> excludeFromDiscountItemTypes;

    @Autowired
    public ExculsionDiscountRole(DiscountConfigurationUtility discountConfigurationUtility) {
        excludeFromDiscountItemTypes = new HashSet<>();
        String property = discountConfigurationUtility.getProperty(SystemConstants.ExcludeFromDiscountKey);
        if (property != null) {
            String[] split = property.split(",");
            for (String string : split) {
                excludeFromDiscountItemTypes.add(string);
            }
        }
    }

    @Override
    public void calculateDiscount(DiscountContext discountContext) {
        List<ItemsDto> items = discountContext.getBillDto().getItems();
        for (ItemsDto itemsDto : items) {
            if (excludeFromDiscountItemTypes.contains(itemsDto.getItemType())) {
                itemsDto.setApplyDiscount(false);
            } else {
                itemsDto.setApplyDiscount(true);
            }
        }
    }

}
