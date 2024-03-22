package com.nagarro.amazon.repository;

import com.nagarro.amazon.model.DealItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealItemRepository extends JpaRepository<DealItem,Long> {
    List<DealItem> findByCategoryNameIgnoreCase(String categoryName);
}
