package com.clone.microsvc.likes.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LikeDTO {
    private Long id;

    @NotNull(message= "This field must not optional")
    @Min(0)
    private Integer like;
}
