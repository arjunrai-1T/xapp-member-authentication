package com.xapp.member.authentication.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xapp.member.authentication.models.common.SearchInputMeta;
import com.xapp.member.authentication.models.common.SearchOutputMeta;
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
@JsonPropertyOrder({"searchOutputMeta","status","message"})
public class SignInRes {

    @JsonProperty("searchOutputMeta")
    @NotBlank(message = "searchOutputMeta must not be blank")
    SearchOutputMeta searchOutputMeta;

    @JsonProperty("status")
    String status;

    @JsonProperty("message")
    String message;
}
