package com.goda.ai.payload.request;

import java.sql.Date;

import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequest {

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Gender cannot be empty")
    private Boolean gender;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotEmpty(message = "Birthday cannot be empty")
    private Date birthday;
}
