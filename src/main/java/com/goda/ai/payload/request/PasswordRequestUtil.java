package com.goda.ai.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PasswordRequestUtil {
    private String email;
    private String oldPassword;
    private String newPassword;
}
