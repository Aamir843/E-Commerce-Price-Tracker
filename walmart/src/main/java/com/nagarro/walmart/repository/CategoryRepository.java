package com.nagarro.walmart.repository;

import com.nagarro.walmart.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Boolean existsByNameIgnoreCase(String category);
}
