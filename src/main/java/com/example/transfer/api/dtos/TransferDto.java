package com.example.transfer.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record TransferDto(
        @NotBlank BigDecimal value,
        @NotBlank @JsonProperty("payer") Long payerId,
        @NotBlank @JsonProperty("payee") Long payeeId
) {
}
