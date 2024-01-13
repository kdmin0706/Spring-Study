package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Category;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.repository.support.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CategoryRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void relationshipTest() {
        Product product = new Product();
        product.setName("가위");
        product.setPrice(5000);
        product.setStock(500);

        productRepository.save(product);

        Category category = new Category();
        category.setCode("S1");
        category.setName("도서");
        category.getProductList().add(product);

        categoryRepository.save(category);

        //테스트 코드
        List<Product> productList = categoryRepository.findById(1L)
                .get().getProductList();

        for (Product products : productList) {
            System.out.println("product = " + products);
        }
    }
}