package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "post")
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length=5000)
    private String content;
    private LocalDateTime lastUpdate;
    @PrePersist //Trước khi lưu khi khởi tạo record
    public void prePersist() {
        lastUpdate = LocalDateTime.now();
    }
    @PreUpdate //Khi cập nhật record
    public void preUpdate() {
        lastUpdate = LocalDateTime.now();
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
