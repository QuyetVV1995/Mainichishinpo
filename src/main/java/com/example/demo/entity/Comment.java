package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Comment(String content) {
        this.content = content;
    }
}
