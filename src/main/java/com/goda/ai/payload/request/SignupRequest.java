package com.goda.ai.payload.request;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import com.goda.ai.model.User;
  
import jakarta.validation.constraints.*;

public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  @NotBlank
  @Size(max = 100)
  private String address;

  private Date birthday;

  private boolean gender;
  @NotBlank
  @Size(max = 20)
  private String nick_name;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public boolean isGender() {
    return gender;
  }

  public void setGender(Boolean gender) {
    this.gender = gender;
  }

  public String getNick_name() {
    return nick_name;
  }

  public void setNick_name(String nick_name) {
    this.nick_name = nick_name;
  }

  public boolean getGender() {
    return gender;
  }

  public User toUser() {
    User user = new User();
    user.setUsername(this.getUsername());
    user.setEmail(this.getEmail());
    user.setPassword(this.getPassword());
    user.setAddress(this.getAddress());
    user.setBirthday(this.getBirthday());
    user.setGender(this.getGender());
    user.setNick_name(this.getNick_name());

    return user;
  }
}