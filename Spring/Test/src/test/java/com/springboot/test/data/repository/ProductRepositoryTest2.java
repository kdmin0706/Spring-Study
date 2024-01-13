package com.springboot.test.data.repository;

import com.springboot.test.data.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ProductRepositoryTest2 {

    @Autowired
    ProductRepository productRepository;

    @Test
    void basicCRUDTest() {

        /* create */
        //given
        Product product = Product.builder()
                .name("pen")
                .price(1000)
                .stock(1000)
                .build();

        //when
        Product savedProduct = this.productRepository.save(product);

        //then
        Assertions.assertThat(savedProduct.getNumber())
                .isEqualTo(product.getNumber());
        Assertions.assertThat(savedProduct.getName())
                .isEqualTo(product.getName());
        Assertions.assertThat(savedProduct.getPrice())
                .isEqualTo(product.getPrice());
        Assertions.assertThat(savedProduct.getStock())
                .isEqualTo(product.getStock());

        /* read */
        //when
        Product selectedProduct
                = this.productRepository.findById(savedProduct.getNumber()).get();
        //then
        Assertions.assertThat(savedProduct.getNumber())
                .isEqualTo(product.getNumber());
        Assertions.assertThat(savedProduct.getName())
                .isEqualTo(product.getName());
        Assertions.assertThat(savedProduct.getPrice())
                .isEqualTo(product.getPrice());
        Assertions.assertThat(savedProduct.getStock())
                .isEqualTo(product.getStock());

        /* update */
        //when
        Product foundProduct
                = this.productRepository.findById(selectedProduct.getNumber())
                        .orElseThrow(RuntimeException::new);

        foundProduct.setName("장난감");

        Product updatedProduct = this.productRepository.save(foundProduct);

        //then
        assertEquals(updatedProduct.getName(), "장난감");


        /* delete */
        //when
        this.productRepository.delete(updatedProduct);

        //then
        assertFalse(this.productRepository
                .findById(selectedProduct.getNumber()).isPresent());

    }
}
