package org.example.springbootdeveloper.dto;

import lombok.Data;
import org.example.springbootdeveloper.domain.Article;
import org.example.springbootdeveloper.domain.Comment;
import org.example.springbootdeveloper.domain.User;

@Data
public class CommentCreateRequest {

    private String content;

    public Comment toEntity(Article article, User user){
        return Comment.builder()
                .user(user)
                .article(article)
                .content(content)
                .build();
    }
}
