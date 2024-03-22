package com.nagarro.main.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DealItem {
    private String itemId;
    
    private String productTitle;
    private String size;
    private String brand;
    private String imageUrl;
    private Double originalPriceValue;
    
    private String originalPriceCurrency;
    
    private Double discountPercentage;

    private Double discountAmountValue;
    
    private String discountAmountCurrency;
    
    private Double priceValue;
    
    private String priceCurrency;
    
    private Integer stock;
    
    private LocalDateTime dealStartDate;

    private LocalDateTime dealEndDate;

}

