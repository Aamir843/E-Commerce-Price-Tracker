package com.nagarro.amazon.repository;

import com.nagarro.amazon.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Boolean existsByNameIgnoreCase(String category);
}
