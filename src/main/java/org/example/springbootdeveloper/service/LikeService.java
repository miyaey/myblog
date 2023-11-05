package org.example.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.Article;
import org.example.springbootdeveloper.domain.User;
import org.example.springbootdeveloper.repository.BlogRepository;
import org.example.springbootdeveloper.repository.CommentRepository;
import org.example.springbootdeveloper.repository.LikeRepository;
import org.example.springbootdeveloper.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addLike(String email, Long articleId){
        Article article = blogRepository.findById(articleId).get();
        User loginUser = userRepository.findByEmail(email).get();
//        User articleUser =
    }
}
