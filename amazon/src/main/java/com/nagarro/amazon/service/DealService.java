package com.nagarro.amazon.service;

import com.nagarro.amazon.dto.DealItemsResponse;

public interface DealService {

        DealItemsResponse getDeals(String categoryName);

}
