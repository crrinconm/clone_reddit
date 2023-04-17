package com.clone.microsvc.posts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PostDTO {

    private String id;

    @NotNull
    @NotBlank(message = "This field (title) can´t be empty")
    private String title;

    @NotNull
    @NotBlank(message = "This field (description) can´t be empty")
    private String description;

    private Long subCategoryId;
}
