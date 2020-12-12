package com.bs.eit.retail.discount;

import com.bs.eit.retail.discount.models.DiscountConfiguration;
import com.bs.eit.retail.discount.models.User;
import com.bs.eit.retail.discount.models.UserTypes;
import com.bs.eit.retail.discount.repos.DiscountConfigurationRepo;
import com.bs.eit.retail.discount.repos.UserTypesRepo;
import com.bs.eit.retail.discount.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;

@Configuration
public class DBInitalization {
    @Autowired
    DiscountConfigurationRepo discountConfigurationRepo;
    @Autowired
    UserTypesRepo userTypesRepo;
    @Autowired
    UsersRepo usersRepo;

    @PostConstruct
    public void intialize() {

        discountConfigurationRepo.save(new DiscountConfiguration(1, "LoyalUserMinimumYearCount", "2"));
        discountConfigurationRepo.save(new DiscountConfiguration(2, "LoyalUserDiscountValue", "5"));
        discountConfigurationRepo.save(new DiscountConfiguration(3, "LoyalUserDiscountType", "percentage"));
        discountConfigurationRepo.save(new DiscountConfiguration(4, "ExcludeFromDiscountItems", "grocery"));
        discountConfigurationRepo.save(new DiscountConfiguration(5, "CashAmountDiscount", "5"));
        discountConfigurationRepo.save(new DiscountConfiguration(6, "CashAmountUnit", "100"));
        userTypesRepo.save(new UserTypes(null, "REGULAR", (short) 0));
        userTypesRepo.save(new UserTypes(null, "EMPLOYEE", (short) 30));
        userTypesRepo.save(new UserTypes(null, "AFFILIATE", (short) 10));
        usersRepo.save(new User(null, "Mustafa", "Mamdouh", new Date(), "1", new UserTypes(1)));
        usersRepo.save(new User(null, "Ahmed", "Mamdouh", new Date(), "2", new UserTypes(2)));
        usersRepo.save(new User(null, "Mohamed", "Mamdouh", new Date(), "3", new UserTypes(3)));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -3);
        usersRepo.save(new User(null, "special", "master", cal.getTime(), "4", new UserTypes(3)));
        usersRepo.save(new User(null, "special1", "master", cal.getTime(), "5", new UserTypes(1)));
    }
}
