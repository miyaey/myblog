package org.example.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.Article;
import org.example.springbootdeveloper.domain.Comment;
import org.example.springbootdeveloper.domain.User;
import org.example.springbootdeveloper.dto.CommentCreateRequest;
import org.example.springbootdeveloper.repository.BlogRepository;
import org.example.springbootdeveloper.repository.CommentRepository;
import org.example.springbootdeveloper.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public void writeComment(Long articleId, CommentCreateRequest req, String email){
        Article article = blogRepository.findById(articleId).get();
        User user = userRepository.findByEmail(email).get();
        article.commentChange(article.getCommentCnt() + 1);
        commentRepository.save(req.toEntity(article, user));

    }

    public List<Comment> findAll(Long articleId){
        return commentRepository.findAllByArticleId(articleId);
    }

    @Transactional
    public Long editComment(Long commentId, String newBody, String email){
        Optional<Comment> optComment = commentRepository.findById(commentId);
        Optional<User> optUser = userRepository.findByEmail(email);
        if (optComment.isEmpty() || optUser.isEmpty() || !optComment.get().getUser().equals(optUser.get())){
            return null;
        }
        Comment comment = optComment.get();
        comment.update(newBody);

        return comment.getArticle().getId();
    }

    public Long deleteComment(Long commentId, String email){
        Optional<Comment> optComment = commentRepository.findById(commentId);
        Optional<User> optUser = userRepository.findByEmail(email);
        if (optComment.isEmpty() || optUser.isEmpty() || !optComment.get().getUser().equals(optUser.get())){
            return null;
        }
        Article article = optComment.get().getArticle();
        article.commentChange(article.getCommentCnt() + 1);

        commentRepository.delete(optComment.get());
        return article.getId();
    }

}
