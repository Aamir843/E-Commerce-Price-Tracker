package com.nagarro.walmart.controller;

import com.nagarro.walmart.dto.DealItemsResponse;
import com.nagarro.walmart.exception.CustomException;
import com.nagarro.walmart.service.DealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.nagarro.walmart.exception.ExceptionConstants.INVALID_CATEGORY_NAME;

@RequestMapping("backendserver3/walmart")
@RestController
public class DealController {

    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @RequestMapping("deals")
    DealItemsResponse getDeals(@RequestParam String categoryName){
        if (isValidCategoryName(categoryName)) {
            return dealService.getDeals(categoryName);
        } else {
            throw new CustomException(INVALID_CATEGORY_NAME + categoryName);
        }
    }

    private boolean isValidCategoryName(String categoryName) {
        return categoryName != null && !categoryName.trim().isEmpty();
    }

}
