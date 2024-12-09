package com.xapp.member.authentication.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xapp.member.authentication.models.common.SearchInputMeta;
import com.xapp.member.authentication.validator.ValidSearchInputMeta;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@JsonPropertyOrder({"searchInputMeta","userid,password"})
public class SignInReq {

    @JsonProperty("searchInputMeta")
    @ValidSearchInputMeta(message = "searchInputMeta must not be blank")
    SearchInputMeta searchInputMeta;

    @JsonProperty("userid")
    @NotBlank(message = "User ID must not be blank")
    @Size(min = 1, max = 50, message = "User ID must be between 1 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_\\-.@]+$", message = "User ID can only contain letters, numbers, underscores, hyphens, periods, and @")
    String userid;

    @JsonProperty("password")
    @NotBlank(message = "User ID must not be blank")
    @Size(min = 1, max = 50, message = "User ID must be between 1 and 50 characters")
    String password;
}
