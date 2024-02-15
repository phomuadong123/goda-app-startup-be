package com.goda.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goda.ai.model.Comment;
import com.goda.ai.service.CommentService;

@Controller
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity createComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.createComment(comment), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity test(
            @RequestParam( defaultValue = "10") int size,
            @RequestParam Long reviewId) {
        return new ResponseEntity<>(commentService.getCommentByReviewId(reviewId, size), HttpStatus.OK);
    }
}
