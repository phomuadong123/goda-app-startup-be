package com.goda.ai.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    String firstName;
    String lastName;
    String email;
}
