package com.goda.ai.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goda.ai.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    Page<Comment> findByReviewId(Long reviewId, Pageable pageable);
}
