package org.example.springbootdeveloper.repository;

import org.example.springbootdeveloper.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    void deleteByUserEmailAndArticleId(String email, Long articleId);
    Boolean existsByUserLoginEmailAndArticleId(String eamil, Long articleId);
    List<Like> findAllByUserEmail(String email);
}
