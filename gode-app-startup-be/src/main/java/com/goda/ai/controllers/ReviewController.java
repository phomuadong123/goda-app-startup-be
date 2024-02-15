package com.goda.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.goda.ai.model.Review;
import com.goda.ai.service.ReviewService;

@Controller
@RequestMapping("/api/post")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    @GetMapping
    public ResponseEntity<Page<Review>> getAllReview(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) throws Exception{
        Pageable paging = PageRequest.of(page, size);
        Page<Review> reviews = reviewService.getAllReview(paging);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    } 

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Review> createReview(
        @RequestParam("files") MultipartFile[] file, 
        @RequestParam("textContent") String textContent
        ) throws Exception{
        Review review = reviewService.createReview(file, textContent);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

}
