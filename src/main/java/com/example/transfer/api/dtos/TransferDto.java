package com.example.transfer.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferDto(
        @NotNull @Positive @JsonProperty(required = true) BigDecimal value,
        @NotNull @Positive @JsonProperty(value = "payer", required = true) Long payerId,
        @NotNull @Positive @JsonProperty(value = "payee", required = true) Long payeeId
) {
}
