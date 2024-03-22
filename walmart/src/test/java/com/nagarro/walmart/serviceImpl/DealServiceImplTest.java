package com.nagarro.walmart.serviceImpl;

import com.nagarro.walmart.dto.DealItemsResponse;
import com.nagarro.walmart.model.Category;
import com.nagarro.walmart.model.DealItem;
import com.nagarro.walmart.repository.CategoryRepository;
import com.nagarro.walmart.repository.DealItemRepository;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DealServiceImplTest {

    @Mock
    private DealItemRepository dealItemRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private DealServiceImpl dealService;

    @Test
    public void testGetDeals() {
        String categoryName = "testCategory";
        Category category = new Category();
        category.setName(categoryName);

        List<DealItem> dealItems = new ArrayList<>();
        DealItem dealItem1 = new DealItem();
        // Set properties for dealItem1
        dealItems.add(dealItem1);

        when(categoryRepository.existsByNameIgnoreCase(categoryName)).thenReturn(true);
        when(dealItemRepository.findByCategoryNameIgnoreCase(categoryName)).thenReturn(dealItems);

        DealItemsResponse response = dealService.getDeals(categoryName);

        // Asserts
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(categoryName, response.getCategoryName()),
                () -> assertEquals(dealItems, response.getDealItems())
        );
    }

    @Test
    public void testGetDeals_CategoryNotFound() {
        String categoryName = "nonexistentCategory";

        when(categoryRepository.existsByNameIgnoreCase(categoryName)).thenReturn(false);

        assertThrows(ServiceException.class, () -> dealService.getDeals(categoryName));
    }
}
