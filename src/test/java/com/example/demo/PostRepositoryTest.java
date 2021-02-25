package com.example.demo;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PostRepositoryTest {
    @Autowired PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Tao post moi")
    void addNewPost(){
        Optional<User> optionalUser = userRepository.findByEmail("bob@gmail.com");
        assertThat(optionalUser).isPresent();
        User user = optionalUser.get();
        Post post = new Post("I love Spring Boot", "I love Spring Boot so much");
        user.addPost(post);

        assertThat(post.getId()).isNull(); //Chưa được persist vào Persistence Context
        userRepository.flush();  //Cách này đúng: đẩy thay đổi xuống database
        //userRepo.save(user); //Cách này không đúng user đã tồn tại rồi
        //postRepo.save(post);
        assertThat(post.getUser()).isEqualTo(user);
        User bob = userRepository.findById(1L).get(); //Lấy user Bob qua id
        assertThat(postRepository.existsById(post.getId())).isTrue(); //Kiểm tra post với id = 1L đã có trong CSDL chưa?
        assertThat(bob.getPosts().get(0)).isEqualTo(post); //Kiểm tra xem thực sự user Bob ở CSDL đã thực sự có post chưa
    }
    @Test
    @DisplayName("Tạo một post mới dùng PostRepository để lưu")
    void persistNewPost() {
        User bob = userRepository.findByEmail("bob@gmail.com").get();

        Post post = new Post("I love Spring Boot", "I love Spring Boot so much");
        bob.addPost(post);

        assertThat(post.getId()).isNull(); //Chưa được persist vào Persistence Context
        postRepository.save(post);  //Dùng postRepo để lưu đối tượng post mới
        postRepository.flush();

        assertThat(post.getUser()).isEqualTo(bob);
        assertThat(bob.getPosts().get(0)).isEqualTo(post);
    }

    @Test
    @DisplayName("User removes post thì post sẽ phải bị xoá hoàn toàn")
    void addAndRemovePost() {
        User bob = userRepository.findByEmail("bob@gmail.com").get();

        Post post = new Post("I love Spring Boot", "I love Spring Boot so much");
        bob.addPost(post);

        postRepository.save(post);  //Dùng postRepo để lưu đối tượng post mới
        postRepository.flush();
        long postId = post.getId();
        bob.removePost(post);
        assertThat(post.getUser()).isNull();  //Đầu tiên thuộc tính Author set null
        userRepository.flush();

        Optional<Post> optionalPost = postRepository.findById(postId);
        assertThat(optionalPost).isNotPresent(); //Post cũng không còn tồn tại trong CSDL
        assertThat(bob.getPosts().size()).isZero();

    }

    @Test
    @DisplayName("Xoá user thì các post user tạo ra cũng bị xoá")
    void removeUserCascadeRemovePostsBelongToUser() {
        User bob = userRepository.findByEmail("bob@gmail.com").get();

        Post post = new Post("I love Spring Boot", "I love Spring Boot so much");
        bob.addPost(post);

        postRepository.save(post);  //Dùng postRepo để lưu đối tượng post mới
        postRepository.flush();
        long postId = post.getId();

        userRepository.delete(bob); //Xoá bob thì tất cả các post do bob viết cũng sẽ bị xoá

        postRepository.flush();
        Optional<Post> optionalPost = postRepository.findById(postId);
        assertThat(post).isNotNull(); //đối tượng này chỉ tồn tại trong hàm đang chạy chứ trong CSDL post đã bị xoá
        assertThat(optionalPost).isNotPresent(); //Post cũng không còn tồn tại trong CSDL
    }
}
