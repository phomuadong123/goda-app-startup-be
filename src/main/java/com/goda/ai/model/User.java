package com.goda.ai.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;
@Data
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  @NaturalId(mutable = true)
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;


  @Size(max = 100)
  private String address;

  @Temporal(TemporalType.DATE)

  private Date birthday;

  private Boolean gender;


  @Size(max = 20)
  private String nick_name;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  private boolean active;
  private LocalDateTime otpGeneratedTime;
  private boolean isEnabled = false;

  public User() {
  }

  public User(String username, String email, String password,String address,Date birthday,boolean gender,String nick_name) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.address = address;
    this.birthday = birthday;
    this.gender = gender;
    this.nick_name = nick_name;
  }
  
  
}