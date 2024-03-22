package com.nagarro.main.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DealItemDTO {

        private String itemId;
        private String productTitle;
        private String size;
        private String brand;
        private Image image;
        private MarketingPrice marketingPrice;
        private Price price;
        private int stock;
        private LocalDateTime dealStartDate;
        private LocalDateTime dealEndDate;

}
