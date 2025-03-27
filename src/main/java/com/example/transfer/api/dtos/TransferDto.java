package com.example.transfer.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferDto(
        @DecimalMin(value = "0.01", message = "O valor minimo para transferência é de 0.01")
        @JsonProperty(required = true)
        @Schema(description = "Valor da transferência", example = "100.00")
        BigDecimal value,

        @Positive(message = "ID do pagador deve ser um número inteiro positivo")
        @JsonProperty(value = "payer", required = true)
        @Schema(description = "ID do pagador", example = "1")
        Long payerId,

        @Positive(message = "ID do recebedor deve ser um número inteiro positivo")
        @JsonProperty(value = "payee", required = true)
        @Schema(description = "ID do recebedor", example = "2")
        Long payeeId
) {
}
