package com.nagarro.walmart.serviceImpl;

import com.nagarro.walmart.dto.DealItemsResponse;
import com.nagarro.walmart.exception.CustomException;
import com.nagarro.walmart.model.DealItem;
import com.nagarro.walmart.repository.CategoryRepository;
import com.nagarro.walmart.repository.DealItemRepository;
import com.nagarro.walmart.service.DealService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nagarro.walmart.exception.ExceptionConstants.NO_SUCH_CATEGORY_EXISTS;

@Service
public class DealServiceImpl implements DealService {


    private final DealItemRepository dealItemRepository;

    private final CategoryRepository categoryRepository;

    public DealServiceImpl(DealItemRepository dealItemRepository, CategoryRepository categoryRepository) {
        this.dealItemRepository = dealItemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public DealItemsResponse getDeals(String categoryName) {
        DealItemsResponse dealItemsResponse = new DealItemsResponse();

        Boolean category = categoryRepository.existsByNameIgnoreCase(categoryName);

        if (!category) {
            throw new CustomException(NO_SUCH_CATEGORY_EXISTS);
        }

        List<DealItem> dealItems = dealItemRepository.findByCategoryNameIgnoreCase(categoryName);

        dealItemsResponse.setCategoryName(categoryName);

        dealItemsResponse.setDealItems(dealItems);


        return dealItemsResponse;

    }
}
