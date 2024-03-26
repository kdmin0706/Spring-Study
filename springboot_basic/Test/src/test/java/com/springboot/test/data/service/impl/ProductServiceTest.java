package com.springboot.test.data.service.impl;

import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class ProductServiceTest {
    private final ProductRepository productRepository
            = Mockito.mock(ProductRepository.class);
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUpTest() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void getProductTest() {
        //given
        Product product = Product.builder()
                .number(123L)
                .name("pen")
                .price(1000)
                .stock(1234)
                .build();

        Mockito.when(productRepository.findById(123L))
                .thenReturn(Optional.of(product));

        //when
        ProductResponseDto responseDto
                = this.productService.getProduct(123L);

        //then
        Assertions.assertEquals(responseDto.getNumber(), product.getNumber());
        Assertions.assertEquals(responseDto.getName(), product.getName());
        Assertions.assertEquals(responseDto.getPrice(), product.getPrice());
        Assertions.assertEquals(responseDto.getStock(), product.getStock());

        verify(productRepository).findById(123L);
    }



    @Test
    void saveProductTest() {
        //given
        Mockito.when(productRepository.save(any(Product.class)))
                .then(returnsFirstArg());

        //when
        ProductResponseDto responseDto
                = productService.saveProduct(new ProductDto("pen", 1000, 1234));

        //then
        Assertions.assertEquals(responseDto.getName(), "pen");
        Assertions.assertEquals(responseDto.getPrice(), 1000);
        Assertions.assertEquals(responseDto.getStock(), 1234);

        verify(productRepository).save(any());
    }
}