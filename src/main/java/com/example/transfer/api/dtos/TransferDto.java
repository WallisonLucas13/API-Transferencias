package com.example.transfer.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferDto(
        @NotNull
        @Positive
        @JsonProperty(required = true)
        @Schema(description = "Valor da transferência", example = "100.00")
        BigDecimal value,
        @NotNull
        @Positive
        @JsonProperty(value = "payer", required = true)
        @Schema(description = "ID do pagador", example = "1")
        Long payerId,
        @NotNull
        @Positive
        @JsonProperty(value = "payee", required = true)
        @Schema(description = "ID do recebedor", example = "2")
        Long payeeId
) {
}
