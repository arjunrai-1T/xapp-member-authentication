package com.xapp.member.authentication.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xapp.member.authentication.models.common.BaseResponse;
import com.xapp.member.authentication.models.common.SearchOutputMeta;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@JsonPropertyOrder({"searchOutputMeta","status","message"})
public class SignUpRes  extends BaseResponse {
//    @JsonProperty("searchOutputMeta")
//    @NotBlank(message = "searchOutputMeta must not be blank")
//    SearchOutputMeta searchOutputMeta;
//
//    @JsonProperty("status")
//    String status;
//
//    @JsonProperty("message")
//    String message;
}
