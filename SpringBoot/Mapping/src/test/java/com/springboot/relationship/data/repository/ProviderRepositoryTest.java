package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.Provider;
import com.springboot.relationship.data.repository.support.ProductRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProviderRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Test
    void relationshipTest1() {
        Provider provider = new Provider();
        provider.setName("ㅇㅇ 물산");

        providerRepository.save(provider);

        Product product = new Product();
        product.setName("가위");
        product.setPrice(5000);
        product.setStock(500);
        product.setProvider(provider);

        //테스트
        System.out.println("product : " + productRepository.findById(1L)
                .orElseThrow(RuntimeException::new));

        System.out.println("provider : " + productRepository.findById(1L)
                .orElseThrow(RuntimeException::new).getProvider());
    }

    @Test
    void relationshipTest2() {
        Provider provider = new Provider();
        provider.setName("ㅇㅇ 물산");

        providerRepository.save(provider);

        Product product1 = new Product();
        product1.setName("가위");
        product1.setPrice(5000);
        product1.setStock(500);
        product1.setProvider(provider);

        Product product2 = new Product();
        product2.setName("가방");
        product2.setPrice(3000);
        product2.setStock(300);
        product2.setProvider(provider);

        Product product3 = new Product();
        product3.setName("볼펜");
        product3.setPrice(1000);
        product3.setStock(100);
        product3.setProvider(provider);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> productList = providerRepository.findById(
                provider.getId()).get().getProductList();

        for (Product product : productList) {
            System.out.println("product = " + product);
        }
    }
    
    
    @Test
    void cascadeTest() {
        Provider provider = savedProvider("새로운 공급 업체");

        Product product1 = savedProduct("펜", 1000, 100);
        Product product2 = savedProduct("가방", 10000, 1000);
        Product product3 = savedProduct("가위", 5000, 500);

        //연관관계 설정
        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.save(provider);
    }

    @Test
    void orphanRemovalTest() {
        Provider provider = savedProvider("새로운 공급 업체");

        Product product1 = savedProduct("펜", 1000, 100);
        Product product2 = savedProduct("가방", 10000, 1000);
        Product product3 = savedProduct("가위", 5000, 500);

        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.saveAndFlush(provider);

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);

        Provider foundProvider = providerRepository.findById(1L).get();
        foundProvider.getProductList().remove(0);   //첫번째 인덱스 임의 삭제

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);
    }

    private Provider savedProvider(String name) {
        Provider provider = new Provider();
        provider.setName(name);

        return providerRepository.save(provider);
    }

    private Product savedProduct(String name, Integer price, Integer stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return productRepository.save(product);
    }
}