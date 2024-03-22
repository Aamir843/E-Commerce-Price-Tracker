package com.nagarro.ebay.repository;

import com.nagarro.ebay.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Boolean existsByNameIgnoreCase(String category);
}
