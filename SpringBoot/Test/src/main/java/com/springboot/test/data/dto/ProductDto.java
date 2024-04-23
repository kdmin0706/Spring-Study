package com.springboot.test.data.dto;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String name;
    private int price;
    private int stock;
}
