package com.goda.ai.service;



import com.goda.ai.model.Media;
import com.goda.ai.model.Review;
import com.goda.ai.model.User;
import com.goda.ai.model.VerificationToken;
import com.goda.ai.payload.request.RegistrationRequest;
import com.goda.ai.repository.PasswordResetTokenRepository;
import com.goda.ai.repository.VerificationTokenRepository;

import jakarta.mail.MessagingException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goda.ai.repository.UserRepository;

import java.io.IOException;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository repository;


    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationTokenRepository tokenRepository;
    @Autowired
    private  PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private S3Client s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;



    public List<User> getUsers() {
        return repository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }


    public User registerUser(RegistrationRequest request) {
        Optional<User> user = this.findByEmail(request.getEmail());
        return user.get();
    }

    public void saveUserVerificationToken(User theUser, String token) {
        var verificationToken = new VerificationToken(token, theUser);
        tokenRepository.deleteAll();
        tokenRepository.save(verificationToken);
    }

    public String validateToken(String theToken) {
        VerificationToken token = tokenRepository.findByToken(theToken);
        if(token == null){
            return "Invalid verification token";
        }
        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime()-calendar.getTime().getTime())<= 0){
            return "Verification link already expired," +
                    " Please, click the link below to receive a new verification link";
        }
        user.setEnabled(true);
        repository.save(user);
        return "valid";
    }


    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = tokenRepository.findByToken(oldToken);
        var verificationTokenTime = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setExpirationTime(verificationTokenTime.getTokenExpirationTime());
        return tokenRepository.save(verificationToken);
    }

    public void changePassword(User theUser, String newPassword) {
        theUser.setPassword(passwordEncoder.encode(newPassword));
        repository.save(theUser);
    }


    public String validatePasswordResetToken(String token) {
        return passwordResetTokenService.validatePasswordResetToken(token);
    }


    public User findUserByPasswordToken(String token) {
        return passwordResetTokenService.findUserByPasswordToken(token).get();
    }


    public void createPasswordResetTokenForUser(User user, String passwordResetToken) {

        passwordResetTokenService.createPasswordResetTokenForUser(user, passwordResetToken);
    }

    public boolean oldPasswordIsValid(User user, String oldPassword){
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    public boolean changeAvatar(MultipartFile[] avatar, Long userId) {
        List<String> getAvatar = uploadFile(avatar, userId);
        User user = repository.findById(userId).get();
        user.setAvatar(getAvatar.get(0));
        repository.save(user);
        return true;
    }

    private List<String> uploadFile(MultipartFile[] files, Long userId) {
        return Arrays.stream(files).map(file -> uploadFile(file, userId)).collect(Collectors.toList());
    }

    private String uploadFile(MultipartFile file, Long userId) {
        String fileUrl = "";
        User user = new User();
        try {
            // Generate unique file name
            String fileName = "avatar/" + userId + "/" + generateFileName(file.getOriginalFilename());

            // Upload file
            s3Client.putObject(PutObjectRequest.builder()
                                              .bucket(bucketName)
                                              .key(fileName)
                                              .build(),
                               RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            // Get file URL
            fileUrl = s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(fileName)).toExternalForm();
            user.setAvatar(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    private String generateFileName(String fileName) {
        return new Date(0) + "_" + fileName;
    }

    public String getAvatar(Long userId) {
        User user = repository.findById(userId).get();
        // TODO Auto-generated method stub
        return user.getAvatar();
    }
}



