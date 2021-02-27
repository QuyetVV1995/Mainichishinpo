package com.example.demo.service.impl;

import com.example.demo.controller.request.CommentRequest;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.exception.PostException;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.TagRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PostService;
import org.hibernate.CacheMode;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TagRepository tagRepository;
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    @Override
    public void addComment(CommentRequest commentRequest, Long user_id) throws PostException {
        Optional<Post> optionalPost = postRepository.findById(commentRequest.getPost_id());
        Optional<User> optionalUser =userRepository.findById(user_id);
        if(optionalPost.isPresent() && optionalUser.isPresent()){
            Post post = optionalPost.get();

            Comment comment = new Comment();
            comment.setContent(commentRequest.getContent());
            comment.setUser(optionalUser.get());
            comment.setPost(post);
            commentRepository.save(comment);
            post.addComment(comment);
            postRepository.saveAndFlush(post);
        }else{
            throw new PostException("Post or User is missing");
        }
    }

    @Override
    public List<Post> searchPost(String terms, int limit, int offset) {
        return Search.session(em).search(Post.class).where(f -> f.match().fields("title", "content").
                matching(terms)).fetchHits(offset, limit);
    }

    @Override
    public void reindexFullText() {
        SearchSession searchSession = Search.session(em);

        MassIndexer indexer = searchSession.massIndexer(Post.class).dropAndCreateSchemaOnStart(true)
                .typesToIndexInParallel( 2 )
                .batchSizeToLoadObjects(10)
                .idFetchSize(200)
                .threadsToLoadObjects(5)
                .cacheMode(CacheMode.IGNORE);
        indexer.start();
    }

    @Override
    public Page<Post> findAllPaging(int page, int pageSize) {
        return postRepository.findAll(PageRequest.of(page, pageSize)); // Bổ xung pagination vào đây !
    }

    @Override
    public List<Post> getAllPostsByUserID(long userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public List<Post> getAllPostN5ByTagId(long tag_id) {
        List<Post> list = postRepository.getAllPostN5ByTagId();
        List<Post> postList = new ArrayList<>();
        Tag tag = tagRepository.findById(tag_id).get();
        System.out.println(tag.getName());

        // Loai bo cac phan tu trung nhau
        for (Post post : list){
            if(!postList.contains(post)){
                for(Tag tag_element : post.getTags()){
                    if(tag == tag_element){
                        postList.add(post);
                    }
                }
            }
        }
        return postList;
    }

    @Override
    public List<Post> getAllPostN4ByTagId(long tag_id) {
        List<Post> list = postRepository.getAllPostN4ByTagId();
        List<Post> postList = new ArrayList<>();
        Tag tag = tagRepository.findById(tag_id).get();
        System.out.println(tag.getName());

        // Loai bo cac phan tu trung nhau
        for (Post post : list){
            if(!postList.contains(post)){
                for(Tag tag_element : post.getTags()){
                    if(tag == tag_element){
                        postList.add(post);
                    }
                }
            }
        }
        return postList;
    }

    @Override
    public List<Post> getAllPostN3ByTagId(long tag_id){
        List<Post> list = postRepository.getAllPostN3ByTagId();
        List<Post> postList = new ArrayList<>();
        Tag tag = tagRepository.findById(tag_id).get();
        System.out.println(tag.getName());

        // Loai bo cac phan tu trung nhau
        for (Post post : list){
            if(!postList.contains(post)){
                for(Tag tag_element : post.getTags()){
                    if(tag == tag_element){
                        postList.add(post);
                    }
                }
            }
        }
        return postList;
    }

    @Override
    public List<Post> getAllPostN2ByTagId(long tag_id) {
        List<Post> list = postRepository.getAllPostN2ByTagId();
        List<Post> postList = new ArrayList<>();
        Tag tag = tagRepository.findById(tag_id).get();
        System.out.println(tag.getName());

        // Loai bo cac phan tu trung nhau
        for (Post post : list){
            if(!postList.contains(post)){
                for(Tag tag_element : post.getTags()){
                    if(tag == tag_element){
                        postList.add(post);
                    }
                }
            }
        }
        return postList;
    }

    @Override
    public List<Post> getAllPostN1ByTagId(long tag_id) {
        List<Post> list = postRepository.getAllPostN1ByTagId();
        List<Post> postList = new ArrayList<>();
        Tag tag = tagRepository.findById(tag_id).get();
        System.out.println(tag.getName());

        // Loai bo cac phan tu trung nhau
        for (Post post : list){
            if(!postList.contains(post)){
                for(Tag tag_element : post.getTags()){
                    if(tag == tag_element){
                        postList.add(post);
                    }
                }
            }
        }
        return postList;
    }

}
