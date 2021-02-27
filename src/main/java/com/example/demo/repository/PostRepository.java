package com.example.demo.repository;

import com.example.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM post AS p WHERE p.user_id = :user_id", nativeQuery = true)
    List<Post> findByUserId(@Param("user_id") long user_id);

    Optional<Post> findById(Long id);

    @Query("SELECT p AS user_id FROM post AS p WHERE p.id = :id")
    Optional<Post> findPostWithUserById(@Param("id") long id);

    @Query(value = "SELECT * FROM post INNER JOIN post_tag ON post_tag.post_id = :n where post.id = post_tag.post_id and post_tag.tag_id = :category", nativeQuery = true)
    List<Post> getAllPostsByCategory(@Param("n") long n, @Param("category") long category);


    @Query(value = "SELECT * FROM post, post_tag, tag WHERE tag.id = 5 AND post_tag.tag_id = tag.id AND post_tag.post_id = post.id;", nativeQuery = true)
    List<Post> getAllPostN5ByTagId();

    @Query(value = "SELECT * FROM post, post_tag, tag WHERE tag.id = 4 AND post_tag.tag_id = tag.id AND post_tag.post_id = post.id;", nativeQuery = true)
    List<Post> getAllPostN4ByTagId();

    @Query(value = "SELECT * FROM post, post_tag, tag WHERE tag.id = 3 AND post_tag.tag_id = tag.id AND post_tag.post_id = post.id;", nativeQuery = true)
    List<Post> getAllPostN3ByTagId();

    @Query(value = "SELECT * FROM post, post_tag, tag WHERE tag.id = 2 AND post_tag.tag_id = tag.id AND post_tag.post_id = post.id;", nativeQuery = true)
    List<Post> getAllPostN2ByTagId();

    @Query(value = "SELECT * FROM post, post_tag, tag WHERE tag.id = 1 AND post_tag.tag_id = tag.id AND post_tag.post_id = post.id;", nativeQuery = true)
    List<Post> getAllPostN1ByTagId();


}
