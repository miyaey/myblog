package org.example.springbootdeveloper.domain;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //좋아요, 좋아요 수
    @OneToMany(mappedBy = "article", orphanRemoval = true)
    private List<Like> likes;
    private Integer likeCnt;

    //댓글, 댓글 수
    @OneToMany(mappedBy = "article", orphanRemoval = true)
    private List<Comment> comments;
    private Integer commentCnt;

    public void likeChange(Integer likeCnt){
        this.likeCnt = likeCnt;
    }

    public void commentChange(Integer commentCnt){
        this.commentCnt = commentCnt;
    }
}
