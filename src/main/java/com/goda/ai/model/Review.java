package com.goda.ai.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String textContent;
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> medias;

    @OneToMany(mappedBy = "review")
    private List<Comment> comments;

    @ManyToOne
    private User user;
    
    @ManyToMany
    @JoinTable(name = "review_users")
    private List<User> referUsers;
    // private Place place;
}
