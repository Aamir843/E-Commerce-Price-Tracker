package com.nagarro.main.controller;

import com.nagarro.main.dto.DealResponse;
import com.nagarro.main.exception.CustomException;
import com.nagarro.main.service.DealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.nagarro.main.exception.ExceptionConstants.INVALID_CATEGORY_NAME;

@RestController
public class DealController {

    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @RequestMapping("deals")
    public Mono<ResponseEntity<DealResponse>> getDeals(@RequestParam String categoryName) {
        if (isValidCategoryName(categoryName)) {
            return dealService.getDeals(categoryName)
                    .map(ResponseEntity::ok)
                    .defaultIfEmpty(ResponseEntity.notFound().build());
        } else {
            throw new CustomException(INVALID_CATEGORY_NAME + categoryName);
        }

    }

    private boolean isValidCategoryName(String categoryName) {
        return categoryName != null && !categoryName.trim().isEmpty();
    }
}

