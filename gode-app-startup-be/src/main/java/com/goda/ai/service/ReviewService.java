package com.goda.ai.service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Principal;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goda.ai.config.services.UserDetailsImpl;
import com.goda.ai.model.Media;
import com.goda.ai.model.Review;
import com.goda.ai.model.User;
import com.goda.ai.repository.ReviewRepository;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private S3Client s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;
    
    // @Autowired
    // private UserRepository userRepository;

    public Page<Review> getAllReview(Pageable pageable) {
        Page<Review> page = reviewRepository.findAll(pageable);
        List<Review> reviews = page.getContent();
        for(Review review: reviews){
            List<Media> medias = review.getMedias();
            for(Media media: medias){
                media.setReview(null);
            }
            User user = review.getUser();
            User userexpose = new User();
            userexpose.setUsername(user.getUsername());
            userexpose.setId(user.getId());
            review.setUser(userexpose);
        }
        Page<Review> response = new PageImpl<>(reviews, pageable, page.getTotalElements());
        return response;
    }

    public Review createReview(MultipartFile[] files, String textContent) throws Exception{
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Review review = new Review();
        review.setTextContent(textContent);
        User user = new User();
        user.setId(userDetailsImpl.getId());
        review.setUser(user);
        review.setCreatedAt(new Date(0));
        review.setUpdatedAt(new Date(0));
        Review result = reviewRepository.save(review);
        mediaService.saveAllFile(uploadFile(files, userDetailsImpl.getId(), result));
        return result;
    }

    private List<Media> uploadFile(MultipartFile[] files, Long userId, Review review) {
        return Arrays.stream(files).map(file -> uploadFile(file, userId, review)).collect(Collectors.toList());
    }

    private Media uploadFile(MultipartFile file, Long userId, Review review) {
        String fileUrl = "";
        Media media = new Media();
        try {
            // Generate unique file name
            String fileName = userId + "/" + generateFileName(file.getOriginalFilename());
            String type = file.getContentType();
            

            // Upload file
            s3Client.putObject(PutObjectRequest.builder()
                                              .bucket(bucketName)
                                              .key(fileName)
                                              .build(),
                               RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            // Get file URL
            fileUrl = s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(fileName)).toExternalForm();
            media = new Media(null, 1, fileUrl, review);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return media;
    }

    private String generateFileName(String fileName) {
        return new Date(0) + "_" + fileName;
    }
}
