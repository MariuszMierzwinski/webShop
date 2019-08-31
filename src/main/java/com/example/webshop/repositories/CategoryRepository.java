package com.example.webshop.repositories;

import com.example.webshop.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    Page<Category> findAllByNameNotNull(Pageable pageable);
    Category findByName(String name);
}
