package com.clone.microsvc.sub.categories.dto;

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

public class SubCategoryDTO {

    private Long id;

    @NotNull
    @NotBlank(message = "This field can´t be empty")
    private String name;

    @NotNull
    private Boolean enable;
}
