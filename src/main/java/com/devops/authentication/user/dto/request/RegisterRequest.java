package com.devops.authentication.user.dto.request;

import com.devops.authentication.common.constant.ValidationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "First name is required.")
    @Size(
            min = ValidationConstants.NAME_MIN_LENGTH,
            max = ValidationConstants.NAME_MAX_LENGTH,
            message = "First name must be between {min} and {max} characters."
    )
    @Pattern(
            regexp = ValidationConstants.NAME_PATTERN,
            message = "First name contains invalid characters."
    )
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(
            min = ValidationConstants.NAME_MIN_LENGTH,
            max = ValidationConstants.NAME_MAX_LENGTH,
            message = "Last name must be between {min} and {max} characters."
    )
    @Pattern(
            regexp = ValidationConstants.NAME_PATTERN,
            message = "Last name contains invalid characters."
    )
    private String lastName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email address.")
    @Size(
            min = ValidationConstants.EMAIL_MIN_LENGTH,
            max = ValidationConstants.EMAIL_MAX_LENGTH,
            message = "Email must not exceed {max} characters."
    )
    private String email;

    @NotBlank(message = "Phone number is required.")
    @Pattern(
            regexp = ValidationConstants.PHONE_NUMBER_PATTERN,
            message = "Invalid phone number."
    )
    private String phoneNumber;

    @NotBlank(message = "Password is required.")
    @Size(
            min = ValidationConstants.PASSWORD_MIN_LENGTH,
            max = ValidationConstants.PASSWORD_MAX_LENGTH,
            message = "Password must be between {min} and {max} characters."
    )
    @Pattern(
            regexp = ValidationConstants.PASSWORD_PATTERN,
            message = "Password must contain uppercase, lowercase, number, and special character."
    )
    private String password;
}