package com.clone.microsvc.file.posts.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection = "fileposts")
public class FilePost {

    @Id
    private String id;

    private String file;

    @Field("post_id")
    private Long postId;
}
