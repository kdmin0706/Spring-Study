package com.springboot.valid_exception.data.dto;

import com.springboot.valid_exception.config.annotation.Telephone;
import lombok.*;

import javax.validation.constraints.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidatedRequestDto {
    @NotBlank
    String name;

    @Email
    String email;

    @Telephone
    String phoneNumber;

    @Min(value = 20)
    @Max(value = 40)
    int age;

    @Size(max = 40)
    String description;

    @Positive
    int count;

    @AssertTrue
    boolean booleanCheck;
}
