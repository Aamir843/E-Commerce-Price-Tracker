package com.nagarro.walmart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "deal_items")
@Getter
@Setter
public class DealItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "product_title")
    private String productTitle;

    @Column(name = "size")
    private String size;

    @Column(name = "brand")
    private String brand;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "original_price_value")
    private Double originalPriceValue;

    @Column(name = "original_price_currency")
    private String originalPriceCurrency;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @Column(name = "discount_amount_value")
    private Double discountAmountValue;

    @Column(name = "discount_amount_currency")
    private String discountAmountCurrency;

    @Column(name = "price_value")
    private Double priceValue;

    @Column(name = "price_currency")
    private String priceCurrency;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "deal_start_date")
    private LocalDateTime dealStartDate;

    @Column(name = "deal_end_date")
    private LocalDateTime dealEndDate;
}

