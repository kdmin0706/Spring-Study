package com.springboot.jpa.data.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeProductNameDto {
    private Long number;
    private String name;
}
