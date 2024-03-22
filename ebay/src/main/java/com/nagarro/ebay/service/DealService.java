package com.nagarro.ebay.service;

import com.nagarro.ebay.dto.DealItemsResponse;

public interface DealService {

        DealItemsResponse getDeals(String categoryName);

}
