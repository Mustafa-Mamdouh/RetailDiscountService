package com.bs.eit.retail.discount;

import com.bs.eit.retail.discount.dtos.BillDto;
import com.bs.eit.retail.discount.dtos.ItemsDtoBuilder;
import com.bs.eit.retail.discount.dtos.ServiceResponseDto;
import com.bs.eit.retail.discount.exceptions.ApplicationException;
import com.bs.eit.retail.discount.services.interfaces.CalculateDiscountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.bs.eit.retail.discount.TestUtils.getBillDto;
import static com.bs.eit.retail.discount.TestUtils.validateValidServiceResponse;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculateDiscountServiceImplTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    ItemsDtoBuilder itemBuilder = new ItemsDtoBuilder();
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
     void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Autowired
    CalculateDiscountService calculateDiscountService;



    @Test
    void GivenOneValidItemCalculateBillForRegularUser() {
        ServiceResponseDto serviceResponseDto = calculateDiscountService.discountValue(
                getBillDto("1", asList(itemBuilder.itemName("ie").unitPrice(55d).itemType("Electronics").quantity(10d).build())));
        validateValidServiceResponse(serviceResponseDto, 0, 25, 525);
    }

    @Test
    void GivenOneValidItemCalculateBillForEmployeeUser() {
        ServiceResponseDto serviceResponseDto = calculateDiscountService.discountValue(
                getBillDto("2", asList(itemBuilder.itemName("ie").unitPrice(55d).itemType("Electronics").quantity(10d).build())));
        validateValidServiceResponse(serviceResponseDto, 165, 15, 370);
    }

    @Test
    void GivenOneValidItemCalculateBillForAffliateUser() {
        ServiceResponseDto serviceResponseDto = calculateDiscountService.discountValue(
                getBillDto("3", asList(itemBuilder.itemName("ie").unitPrice(55d).itemType("Electronics").quantity(10d).build())));
        validateValidServiceResponse(serviceResponseDto, 55, 20, 475);
    }

    @Test
    void GivenOneValidItemCalculateBillForLoyalAffliateUser() {
        ServiceResponseDto serviceResponseDto = calculateDiscountService.discountValue(
                getBillDto("4", asList(itemBuilder.itemName("ie").unitPrice(55d).itemType("Electronics").quantity(10d).build())));
        validateValidServiceResponse(serviceResponseDto, 55, 20, 475);
    }

    @Test
    void GivenOneValidItemCalculateBillForLoyalRegularUser() {
        ServiceResponseDto serviceResponseDto = calculateDiscountService.discountValue(
                getBillDto("5", asList(itemBuilder.itemName("ie").unitPrice(55d).itemType("Electronics").quantity(10d).build())));
        validateValidServiceResponse(serviceResponseDto, 27.5, 25, 497.5);
    }

    @Test
    void GivenWrongUserId() {
        Exception exception = assertThrows(ApplicationException.class, () ->
                calculateDiscountService.discountValue(
                        getBillDto("999", asList(itemBuilder.itemName("ie").unitPrice(55d).itemType("Electronics").quantity(10d).build()))));
    }

    @Test
    void GivenOneValidItemsWithExcludedOneCalculateBillForAffliateUser() {
        ServiceResponseDto serviceResponseDto = calculateDiscountService.discountValue(
                getBillDto("3", asList(itemBuilder.itemName("ie").unitPrice(55d).itemType("Electronics").quantity(10d).build(),
                        itemBuilder.itemName("tomato").unitPrice(10d).itemType("grocery").quantity(10d).build())));
        validateValidServiceResponse(serviceResponseDto, 55, 25, 570);

    }

    @Test
    public void givenValidRequestCalculateBill() throws Exception {
        BillDto bill = getBillDto("1", asList(itemBuilder.itemName("ie").unitPrice(55d).itemType("Electronics").quantity(10d).build()));
        MvcResult mvcResult = this.mockMvc.perform(post("/discount")
                .content(objectMapper.writeValueAsString(bill))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        ServiceResponseDto serviceResponseDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ServiceResponseDto.class);
        validateValidServiceResponse(serviceResponseDto,0,25,525);
    }


    @Test
    public void givenInvalidRequestCalculateBill() throws Exception {
        BillDto bill = getBillDto(null, asList(itemBuilder.itemName("ie").unitPrice(55d).itemType("Electronics").quantity(10d).build()));
        MvcResult mvcResult = this.mockMvc.perform(post("/discount")
                .content(objectMapper.writeValueAsString(bill))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        ServiceResponseDto serviceResponseDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ServiceResponseDto.class);
        assert("missing parameter /userId".equals(serviceResponseDto.getErrorMessage()));
    }

}