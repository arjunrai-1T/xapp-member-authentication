package com.xapp.member.authentication.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xapp.member.authentication.models.common.SearchInputMeta;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonPropertyOrder({"searchInputMeta","userid,password"})
public class SignInReq {

    @JsonProperty("searchInputMeta")
    @NotBlank(message = "searchInputMeta must not be blank")
    SearchInputMeta searchInputMeta;

    @JsonProperty("userid")
    @NotBlank(message = "User ID must not be blank")
    @Size(min = 1, max = 50, message = "User ID must be between 1 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_\\-.@]+$", message = "User ID can only contain letters, numbers, underscores, hyphens, periods, and @")
    String userid;

    @JsonProperty("password")
    @NotBlank(message = "User ID must not be blank")
    @Size(min = 8, max = 20, message = "User ID must be between 1 and 50 characters")
    String password;
}
