package com.mb.graphqlbasic.repositories;

import com.mb.graphqlbasic.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
