package com.nagarro.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarketingPrice {
    private Price originalPrice;
    private Double discountPercentage;
    private Price discountAmount;
    private String priceTreatment;
}

