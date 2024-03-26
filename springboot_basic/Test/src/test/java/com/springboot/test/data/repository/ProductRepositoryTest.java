package com.springboot.test.data.repository;

import com.springboot.test.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void save() {
        //given
        Product product = Product.builder()
                .name("pen")
                .price(1000)
                .stock(1000)
                .build();

        //when
        Product savedProduct = this.productRepository.save(product);

        //then
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getStock(), savedProduct.getStock());
    }

    @Test
    void selectTest() {
        //given
        Product product = Product.builder()
                .name("pen")
                .price(1000)
                .stock(1000)
                .build();

        Product savedProduct = this.productRepository.saveAndFlush(product);

        //when
        Product foundProduct
                = this.productRepository.findById(savedProduct.getNumber()).get();

        //then
        assertEquals(foundProduct.getName(), savedProduct.getName());
        assertEquals(foundProduct.getPrice(), savedProduct.getPrice());
        assertEquals(foundProduct.getStock(), savedProduct.getStock());

    }
}