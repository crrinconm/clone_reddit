package com.clone.microsvc.posts.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", unique = true)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    /*@Column(name = "sub_category_id")
    private Long sub_category;

    @Column(name = "user_id")
    private Long user_id;*/
}
