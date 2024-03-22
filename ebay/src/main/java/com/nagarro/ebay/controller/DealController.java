package com.nagarro.ebay.controller;

import com.nagarro.ebay.dto.DealItemsResponse;
import com.nagarro.ebay.exception.CustomException;
import com.nagarro.ebay.service.DealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.nagarro.ebay.exception.ExceptionConstants.INVALID_CATEGORY_NAME;

@RequestMapping("backendserver2/ebay")
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
