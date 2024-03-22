package com.nagarro.walmart.repository;

import com.nagarro.walmart.model.DealItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealItemRepository extends JpaRepository<DealItem,Long> {

    List<DealItem> findByCategoryNameIgnoreCase(String categoryName);
}
