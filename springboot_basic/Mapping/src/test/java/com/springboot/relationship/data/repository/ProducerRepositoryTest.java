package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Producer;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.repository.support.ProductRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ProducerRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProducerRepository producerRepository;

    @Test
    @Transactional
    void relationshipTest1() {
        Product product1 = savedProduct("펜", 1000, 100);
        Product product2 = savedProduct("가방", 10000, 1000);
        Product product3 = savedProduct("가위", 5000, 500);

        Producer producer1 = savedProducer("flature");
        Producer producer2 = savedProducer("wikibooks");

        producer1.addProduct(product1);
        producer1.addProduct(product2);

        producer2.addProduct(product1);
        producer2.addProduct(product2);

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2));

        System.out.println(producerRepository.findById(1L).get().getProductList());
    }

    private Producer savedProducer(String name) {
        Producer producer = new Producer();
        producer.setName(name);

        return producerRepository.save(producer);
    }

    private Product savedProduct(String name, Integer price, Integer stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return productRepository.save(product);
    }

    @Test
    @Transactional
    void relationshipTest2() {
        Product product1 = savedProduct("펜", 1000, 100);
        Product product2 = savedProduct("가방", 10000, 1000);
        Product product3 = savedProduct("가위", 5000, 500);

        Producer producer1 = savedProducer("feature");
        Producer producer2 = savedProducer("wiki-books");

        producer1.addProduct(product1);
        producer1.addProduct(product2);
        producer2.addProduct(product2);
        producer2.addProduct(product3);

        product1.addProducer(producer1);
        product2.addProducer(producer1);
        product2.addProducer(producer2);
        product3.addProducer(producer2);

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2));
        productRepository.saveAll(Lists.newArrayList(product1, product2, product3));

        System.out.println("products : " + productRepository.findById(1L)
                .get().getProducers());

        System.out.println("products : " + producerRepository.findById(1L)
                .get().getProductList());
    }

}