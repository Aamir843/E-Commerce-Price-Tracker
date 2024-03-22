package com.nagarro.main.service;

import com.nagarro.main.dto.DealResponse;
import reactor.core.publisher.Mono;

public interface DealService {

    Mono<DealResponse> getDeals(String categoryName);
}
