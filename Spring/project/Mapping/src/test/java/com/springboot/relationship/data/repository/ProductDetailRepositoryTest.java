package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.ProductDetail;
import com.springboot.relationship.data.repository.support.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductDetailRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Test
    void saveAndReadTest1() {
        Product product = new Product();
        product.setName("pen");
        product.setPrice(5000);
        product.setStock(500);

        productRepository.save(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setDescription("볼펜 입니다");

        productDetailRepository.save(productDetail);

        //생성 데이터 조회
        System.out.println("product : " + productDetailRepository.findById(
                productDetail.getId()).get().getProduct());

        System.out.println("productDetail : " + productDetailRepository.findById(
                productDetail.getId()).get());
    }
}