package com.xapp.member.authentication.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"searchOutputMeta","status","message"})
public class BaseResponse {

    @JsonProperty("searchOutputMeta")
    @NotBlank(message = "searchOutputMeta must not be blank")
    SearchOutputMeta searchOutputMeta;

    @JsonProperty("status")
    String status;

    @JsonProperty("message")
    String message;
}
