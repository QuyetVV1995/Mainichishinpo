package com.example.demo.user;

import com.example.demo.repository.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("find user by email")
    void findByEmail(){
        var user = userRepository.findByEmail("bob@gmail.com");
        assertThat(user).isPresent();
        assertThat(user.get().getEmail()).isEqualTo("bob@gmail.com");
    }
}
