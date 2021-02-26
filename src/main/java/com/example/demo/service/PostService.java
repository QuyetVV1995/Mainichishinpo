package com.example.demo.service;

import com.example.demo.controller.request.CommentRequest;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.exception.PostException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PostService {

    public List<Post> findAll();

    public Optional<Post> findById(long id);

    public void addComment(CommentRequest commentRequest, Long user_id) throws PostException;
    public List<Post> searchPost(String term, int limit, int offset);
    public void reindexFullText();
    public Page<Post> findAllPaging(int page, int pageSize);
}
