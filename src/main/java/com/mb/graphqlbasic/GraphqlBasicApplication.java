package com.mb.graphqlbasic;

import com.mb.graphqlbasic.models.Category;
import com.mb.graphqlbasic.models.Product;
import com.mb.graphqlbasic.repositories.CategoryRepository;
import com.mb.graphqlbasic.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class GraphqlBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlBasicApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CategoryRepository categoryRepository,
                                               ProductRepository productRepository){
        return args -> {
            Random random = new Random();
            List.of("Computer", "Phones", "Camera").forEach( catLabel-> {
                Category category = Category.builder()
                        .label(catLabel)
                        .build();
                category = categoryRepository.save(category);
                for (int i = 0; i < 10; i++) {
                    Product product = Product.builder()
                            .id(UUID.randomUUID().toString())
                            .label(String.format("%s-%s", catLabel,i))
                            .price(100+Math.random()*5000)
                            .quantity(random.nextInt(100))
                            .category(category)
                            .build();
                    productRepository.save(product);
                   }

            });

        };
    }

}
