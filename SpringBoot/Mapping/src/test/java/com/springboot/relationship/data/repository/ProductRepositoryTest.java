package com.springboot.relationship.data.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    void sortingAndPagingTest() {
        Product product1 = new Product();
        product1.setName("pen");
        product1.setPrice(1000);
        product1.setStock(100);

        Product product2 = new Product();
        product2.setName("pen");
        product2.setPrice(4000);
        product2.setStock(400);

        Product product3 = new Product();
        product3.setName("pen");
        product3.setPrice(5000);
        product3.setStock(600);

        this.productRepository.save(product1);
        this.productRepository.save(product2);
        this.productRepository.save(product3);

        System.out.println(productRepository.findByName("pen", getSort()));
    }

    private Sort getSort() {
        return Sort.by(
                Order.asc("price"),
                Order.desc("stock")
        );
    }

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void queryDslTest() {
        JPAQuery<Product> query = new JPAQuery<>(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query.from(qProduct)
                .where(qProduct.name.eq("pen"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList) {
            System.out.println("====================");
            System.out.println();

            System.out.println("Product Number : " + product.getNumber());
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price : " + product.getPrice());
            System.out.println("Product Stock : " + product.getStock());

            System.out.println();
            System.out.println("====================");
        }
    }

    @Test
    void queryDslTest2() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = jpaQueryFactory.selectFrom(qProduct)
                .where(qProduct.name.eq("pen"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList) {
            System.out.println("====================");
            System.out.println();

            System.out.println("Product Number : " + product.getNumber());
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price : " + product.getPrice());
            System.out.println("Product Stock : " + product.getStock());

            System.out.println();
            System.out.println("====================");
        }
    }

    @Test
    void queryDslTest3() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("pen"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String product : productList) {
            System.out.println("====================");
            System.out.println("Product Name : " + product);
            System.out.println("====================");
        }

        List<Tuple> tupleList = jpaQueryFactory
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("pen"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Tuple product : tupleList) {
            System.out.println("====================");
            System.out.println("Product Name : " + product.get(qProduct.name));
            System.out.println("Product Price : " + product.get(qProduct.price));
            System.out.println("====================");
        }
    }

    @Test
    void queryDslTest4() {
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("pen"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String product : productList) {
            System.out.println("====================");
            System.out.println("Product Name : " + product);
            System.out.println("====================");
        }
    }
}