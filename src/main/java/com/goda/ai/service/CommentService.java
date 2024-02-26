package com.goda.ai.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.goda.ai.model.Comment;
import com.goda.ai.model.Review;
import com.goda.ai.repository.CommentRepository;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;

    public Comment createComment(Comment comment) {
        comment.setCreatedAt(new Date(0));
        comment.setUpdatedAt(new Date(0));
        return commentRepository.save(comment);
    }

    public Page<Comment> getCommentByReviewId(Long reviewId, int size) {
        Pageable paging = PageRequest.of(0, size);
        Page<Comment> result = commentRepository.findByReviewId(reviewId, paging);
        List<Comment> comments = result.getContent();
        for (Comment comment : comments) {
            Review review = comment.getReview();
            review.setComments(null);
            comment.setReview(review);
        }
        Page<Comment> response = new PageImpl<>(comments, paging, result.getTotalElements());
        return response;
    }
}
