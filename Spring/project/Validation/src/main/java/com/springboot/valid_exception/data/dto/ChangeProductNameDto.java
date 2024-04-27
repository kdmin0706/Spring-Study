package com.springboot.valid_exception.data.dto;

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
