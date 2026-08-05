package com.devops.authentication.auth.dto.request;

import com.devops.authentication.common.constant.ValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResetPasswordRequest {

    @NotBlank(message = "Reset token is required.")
    private String token;

    @NotBlank(message = "New password is required.")
    @Size(
            min = ValidationConstants.PASSWORD_MIN_LENGTH,
            max = ValidationConstants.PASSWORD_MAX_LENGTH,
            message = "Password must be between {min} and {max} characters."
    )
    @Pattern(
            regexp = ValidationConstants.PASSWORD_PATTERN,
            message = "Password must contain uppercase, lowercase, number, and special character."
    )
    private String newPassword;

}