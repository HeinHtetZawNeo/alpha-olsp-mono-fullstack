package com.alpha.olsp.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductRegisterDto(
        @NotBlank(message = "Name is required.")
        @Size(max = 100, message = "Name must be less than or equal to 100 characters.")
        String name,
        @NotBlank(message = "Description is required.")
        @Size(max = 500, message = "Description must be less than or equal to 500 characters.")
        String description,

        @NotBlank(message = "Catalog ID is required.")
        String catalogId,

        @NotNull(message = "Price is required.")
        @Positive(message = "Price must be a positive value.")
        Double price,

        @NotNull(message = "Stock is required.")
        @Positive(message = "Stock must be a positive integer.")
        Integer stock
) {
}
