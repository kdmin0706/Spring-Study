package com.springboot.relationship.data.dto;

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
