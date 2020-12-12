package com.bs.eit.retail.discount;

import com.bs.eit.retail.discount.dtos.BillDto;
import com.bs.eit.retail.discount.dtos.ItemsDto;
import com.bs.eit.retail.discount.dtos.ServiceResponseDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtils {
    public static BillDto getBillDto(String userId, List<ItemsDto> items) {
        BillDto bill = new BillDto();
        bill.setUserId(userId);
        bill.setItems(new ArrayList<>());
        for (ItemsDto item : items) {
            bill.getItems().add(item);
        }
        return bill;
    }
    public static void validateValidServiceResponse(ServiceResponseDto serviceResponseDto, double percentageDiscountAmount, double cashDiscountAmount, double finalBill){
        assertNotNull(serviceResponseDto);
        assertNull(serviceResponseDto.getErrorMessage());
        assertTrue(serviceResponseDto.getPercentageDiscountAmount() == percentageDiscountAmount);
        assertTrue(serviceResponseDto.getCashDiscountAmount() == cashDiscountAmount);
        assertTrue(serviceResponseDto.getFinalBill() == finalBill);
    }
}
