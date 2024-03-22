package com.nagarro.walmart.service;

import com.nagarro.walmart.dto.DealItemsResponse;

public interface DealService {

        DealItemsResponse getDeals(String categoryName);

}
