package com.xapp.member.authentication.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"correlationId"})
public class SearchOutputMeta {

    @JsonProperty("correlationId")
    String correlationId;

}
