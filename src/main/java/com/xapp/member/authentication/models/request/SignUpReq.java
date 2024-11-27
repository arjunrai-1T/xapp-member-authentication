package com.xapp.member.authentication.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xapp.member.authentication.models.common.SearchInputMeta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonPropertyOrder({"searchInputMeta","loginId","firstName","lastName","dob","gender","password"})
public class SignUpReq {

    @JsonProperty("searchInputMeta")
    @NotBlank(message = "searchInputMeta must not be blank")
    SearchInputMeta searchInputMeta;

    @JsonProperty("loginId")
    @NotBlank(message = "Login ID must not be blank")
    @Size(min = 1, max = 50, message = "Login ID must be between 1 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_\\-.@]+$", message = "User ID can only contain letters, numbers, underscores, hyphens, periods, and @")
    String loginId;

    @JsonProperty("firstName")
    @NotBlank(message = "First Name must not be blank")
    @Size(min = 1, max = 50, message = "First Name must be between 1 and 50 characters")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "First Name can only contain alphanumeric characters")
    String firstName;

    @JsonProperty("lastName")
    @NotBlank(message = "Last Name must not be blank")
    @Size(min = 1, max = 50, message = "Last Name must be between 1 and 50 characters")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Last Name can only contain alphanumeric characters")
    String lastName;

    @JsonProperty("dob")
    @NotBlank(message = "dob must not be blank")
    @Size(min = 1, max = 50, message = "Date of Birth must be between 1 and 50 characters")
    @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "Invalid Date of Birth format. Please use yyyy-MM-dd.")
    String dob;

    @JsonProperty("gender")
    @NotBlank(message = "Gender must not be blank")
    @Size(min = 1, max = 50, message = "Gender must be between 1 and 50 characters")
    @Pattern(regexp = "^(?i)(Male|Female|Other)$", message = "Gender must be 'Male', 'Female', or 'Other'.")
    String gender;

    @JsonProperty("password")
    @NotBlank(message = "password must not be blank")
    @Size(min = 8, max = 50, message = "password must be between 1 and 50 characters")
    String password;
}
