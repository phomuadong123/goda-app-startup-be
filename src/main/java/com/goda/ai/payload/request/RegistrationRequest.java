package com.goda.ai.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class RegistrationRequest {
    String firstName;
    String lastName;
    String email;
}
