package com.mb.graphqlbasic.web.graphql;

import com.mb.graphqlbasic.dtos.ProductRequest;
import com.mb.graphqlbasic.models.Category;
import com.mb.graphqlbasic.models.Product;
import com.mb.graphqlbasic.repositories.CategoryRepository;
import com.mb.graphqlbasic.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @QueryMapping
    public List<Product> productList(){
        return productRepository.findAll();
    }

    @QueryMapping
    public Product productById(@Argument String id){
        return getProduct(id);
    }

    private Product getProduct(String id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Product %s not found", id)));
    }

    @MutationMapping
    public Product saveProduct(@Argument ProductRequest productRequest){
        Long categoryId = productRequest.categoryId();
        Category category = getCategory(categoryId);
        Product product = Product.builder()
                .id(UUID.randomUUID().toString())
                .quantity(productRequest.quantity())
                .price(productRequest.price())
                .label(productRequest.label())
                .category(category)
                .build();
        return productRepository.save(product);
    }

    private Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new RuntimeException(String.format("Category %s not found", categoryId)));
    }

    @MutationMapping
    public void deleteProduct(@Argument String id){
        getProduct(id);
        productRepository.deleteById(id);
    }

    @MutationMapping
    public Product updateProduct(@Argument String id, @Argument ProductRequest productRequest){
        if (!productRequest.isValid())
            throw new RuntimeException(String.format("Invalid object %s", productRequest));
        Product product = getProduct(id);
        product.setLabel(productRequest.label());
        product.setPrice(productRequest.price());
        product.setQuantity(productRequest.quantity());
        Category category = getCategory(productRequest.categoryId());
        product.setCategory(category);

        return productRepository.save(product);
    }
}
