package com.springboot.advanced_jpa.config;

import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaAuditingConfigurationTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void auditingTest() {
        Product product = Product.builder()
                .name("pen")
                .price(1000)
                .stock(100)
                .build();

        Product saved = this.productRepository.save(product);

        System.out.println("productName : " + saved.getName());
        System.out.println("CreatedAt : " + saved.getCreatedAt());
    }
}