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
}
