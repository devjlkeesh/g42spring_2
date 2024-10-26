package dev.jlkeesh.module9.dto.employee;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;


public record EmployeeCreateDto(
        @NotBlank(message = "name can not be blank")
        String name,
        @NotBlank(message = "surname can not be blank")
        String surname,
        @Min(message = "endi yosh minus bolmaydiku amaki", value = 1)
        @Positive int age) implements Serializable {
}