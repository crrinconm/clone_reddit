package com.clone.microsvc.file.posts.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FilePostDTO {

    private String id;
    private String file;
    private Long postId;

}
