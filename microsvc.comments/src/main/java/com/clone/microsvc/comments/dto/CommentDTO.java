package com.clone.microsvc.comments.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CommentDTO {

    private Long id;

    @Size(max= 300, message = "Your comment shouldn´t be long")
    private String comment;
}
