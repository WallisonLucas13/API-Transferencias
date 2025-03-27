package com.example.transfer.api.controllers.doc;

import com.example.transfer.api.dtos.TransferDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Transferências", description = "API para transferências financeiras")
public interface TransferControllerSwagger {

    @Operation(summary = "Realizar transferência", description = "Realiza a transferência de um valor entre dois usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transferência realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Entrada inválida", content = @Content),
            @ApiResponse(responseCode = "403", description = "Transferência não autorizada", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    ResponseEntity<Void> transfer(
            @RequestBody(description = "Detalhes da transferência", required = true, content = @Content(schema = @Schema(implementation = TransferDto.class)))
            TransferDto transferDto
    );
}
