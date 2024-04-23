package com.springboot.relationship.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provider")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Producer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    @ManyToMany
    @ToString.Exclude
    private List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        productList.add(product);
    }
}
