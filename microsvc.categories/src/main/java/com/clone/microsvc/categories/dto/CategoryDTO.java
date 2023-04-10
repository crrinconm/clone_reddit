package com.clone.microsvc.categories.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CategoryDTO {
    private Long id;


    @NotNull
    @NotBlank(message = "This field can´t empty")
    private String name;

    private String icon;

    private Boolean enable;
}
