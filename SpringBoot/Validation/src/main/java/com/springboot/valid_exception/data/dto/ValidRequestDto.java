package com.springboot.valid_exception.data.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidRequestDto {
    @NotBlank
    String name;

    @Email
    String email;

    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
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
