package org.example.springbootdeveloper.repository;

import org.example.springbootdeveloper.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticleId(Long articleId);
    List<Comment> findAllByUserLoginId(String loginId);
}
