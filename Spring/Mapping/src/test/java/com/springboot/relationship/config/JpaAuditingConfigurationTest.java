package com.springboot.relationship.config;

import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaAuditingConfigurationTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void auditingTest() {
        Product product = new Product();
        product.setName("pen");
        product.setPrice(1000);
        product.setStock(100);

        Product saved = this.productRepository.save(product);

        System.out.println("productName : " + saved.getName());
        System.out.println("CreatedAt : " + saved.getCreatedAt());
    }
}