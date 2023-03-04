package com.mb.graphqlbasic.repositories;

import com.mb.graphqlbasic.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}