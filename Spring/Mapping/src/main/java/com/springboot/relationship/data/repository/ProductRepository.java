package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNumber(Long number);
    List<Product> findAllByName(String name);
    Product queryByNumber(Long number);

    List<Product> findByNameOrderByNumberAsc(String name);
    List<Product> findByNameOrderByNumberDesc(String name);
    List<Product> findByNameOrderByPriceAscStockDesc(String name);

    List<Product> findByName(String name, Sort sort);

    List<Product> findByName(String name, Pageable pageable);

    @Query(" select p from Product as p where p.name = ?1")
    List<Product> findByName(String name);

    @Query(" select p from Product as p where p.name = ?1")
    List<Product> findByNameParam(@Param("name") String name);

    @Query(" select p.name, p.price, p.stock from Product p where p.name = :name ")
    List<Product> findByNameParam2(@Param("name") String name);
}
