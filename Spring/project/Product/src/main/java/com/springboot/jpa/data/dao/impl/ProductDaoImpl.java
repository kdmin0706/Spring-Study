package com.springboot.jpa.data.dao.impl;

import com.springboot.jpa.data.dao.ProductDao;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDaoImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product selectProduct(Long number) {
        return this.productRepository.getById(number);
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> productId = this.productRepository.findById(number);

        Product updatedProduct;
        if (productId.isPresent()) {
            Product product = productId.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = this.productRepository.save(product);
        } else {
            throw new Exception();
        }
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = this.productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            this.productRepository.delete(product);
        } else {
            throw new Exception();
        }
    }
}
