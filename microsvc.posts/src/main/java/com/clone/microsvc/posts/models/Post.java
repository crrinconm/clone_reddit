package com.clone.microsvc.posts.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection= "posts")
public class Post {

    @Id
    private String id;

    private String title;

    private String description;

    //Aquí se van a relacionar, pero le voy a cambiar el nombre como especifiqué en la anotación
    @Field("sub_category_id")
    private Long subCategoryId;


}
