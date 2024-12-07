package com.xapp.member.authentication.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"correlationId"})
public class SearchOutputMeta {

    @JsonProperty("correlationId")
    String correlationId;

}
