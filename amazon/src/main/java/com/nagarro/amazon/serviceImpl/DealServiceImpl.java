package com.nagarro.amazon.serviceImpl;

import com.nagarro.amazon.dto.DealItemsResponse;
import com.nagarro.amazon.exception.CustomException;
import com.nagarro.amazon.model.DealItem;
import com.nagarro.amazon.repository.CategoryRepository;
import com.nagarro.amazon.repository.DealItemRepository;
import com.nagarro.amazon.service.DealService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nagarro.amazon.exception.ExceptionConstants.NO_SUCH_CATEGORY_EXISTS;

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
        DealItemsResponse dealItemsResponse=new DealItemsResponse();

        Boolean category=categoryRepository.existsByNameIgnoreCase(categoryName);

        if(!category){
            throw new CustomException(NO_SUCH_CATEGORY_EXISTS);
        }

        List<DealItem> dealItems = dealItemRepository.findByCategoryNameIgnoreCase(categoryName);

        dealItemsResponse.setCategoryName(categoryName);

        dealItemsResponse.setDealItems(dealItems);

        return dealItemsResponse;

    }
}
