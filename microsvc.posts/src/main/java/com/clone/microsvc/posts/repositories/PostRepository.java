package com.clone.microsvc.posts.repositories;

import com.clone.microsvc.posts.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    //Voy a traer una lista de todos los posts al acceder por medio de subcategory_id

    @Query("{sub_category_id: ?0}")
    List<Post> findBySubCategoryId (Long subCategoryId);
}
