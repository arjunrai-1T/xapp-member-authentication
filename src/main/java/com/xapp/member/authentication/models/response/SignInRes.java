package com.xapp.member.authentication.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xapp.member.authentication.models.common.BaseResponse;
import com.xapp.member.authentication.models.common.SearchOutputMeta;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"searchOutputMeta","status","message","sessionToken","loginDateTime","logoutDateTime"})
public class SignInRes  {

    @JsonProperty("searchOutputMeta")
    @NotBlank(message = "searchOutputMeta must not be blank")
    SearchOutputMeta searchOutputMeta;

    @JsonProperty("status")
    String status;

    @JsonProperty("message")
    String message;

    @JsonProperty("sessionToken")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String sessionToken="";

    @JsonProperty("loginDateTime")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String loginDateTime="";

    @JsonProperty("logoutDateTime")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String logoutDateTime="";
}
