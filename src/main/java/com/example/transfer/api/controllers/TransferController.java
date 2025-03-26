package com.example.transfer.api.controllers;

import com.example.transfer.api.controllers.doc.TransferControllerSwagger;
import com.example.transfer.api.dtos.TransferDto;
import com.example.transfer.api.services.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController implements TransferControllerSwagger {

    private final TransferService transferService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> transfer(@Valid @RequestBody TransferDto transferDto) {
        transferService.transferMoney(transferDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
