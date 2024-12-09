package com.xapp.member.authentication.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@JsonPropertyOrder({"correlationId","userId"})
public class SearchInputMeta {

    @JsonProperty("correlationId")
    @NotBlank(message = "correlationId must not be blank")
    @Size(min = 1, max = 50, message = "correlationId must be between 1 and 50 characters")
    String correlationId;

    @JsonProperty("userId")
    Optional<String> userId= Optional.empty(); ;
}
