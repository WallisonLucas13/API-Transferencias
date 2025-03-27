package com.example.transfer.api.services.transfer.utils;

import com.example.transfer.api.dtos.TransferDto;
import com.example.transfer.api.enums.UserType;
import com.example.transfer.api.exceptions.InsufficientBalanceException;
import com.example.transfer.api.exceptions.InvalidUserIdException;
import com.example.transfer.api.exceptions.UnauthorizedTransferException;
import com.example.transfer.api.exceptions.UserNotAllowedToMakeTransfersException;
import com.example.transfer.api.models.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Log4j2
@Service
public class TransferValidationService {

    private final RestClient restClient;

    public TransferValidationService(@Value("${external.validation.api.url}") String validationUrl) {
        this.restClient = RestClient.create(validationUrl);
    }

    public void validateTransferUsersId(TransferDto transferDto) {
        if(transferDto.payerId().compareTo(transferDto.payeeId()) == 0) {
            throw new InvalidUserIdException("Pagador e recebedor devem ser diferentes");
        }
    }

    public void validatePayerType(User user) {
        if(user.getType().equals(UserType.SHOPKEEPER)) {
            throw new UserNotAllowedToMakeTransfersException("Lojistas não podem fazer transferências");
        }
    }

    public void validatePayerBalance(User user, TransferDto transferDto) {
        if(user.getWallet().getBalance().compareTo(transferDto.value()) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente");
        }
    }

    public void validateTransferExternalAuthorization(User user, TransferDto transferDto) {
        try {
            restClient
                    .get()
                    .retrieve()
                    .toBodilessEntity();

        } catch(HttpClientErrorException e){
            log.info("Autorização externa falhou, status -> {}", e.getStatusCode());
            throw new UnauthorizedTransferException("Transferência não autorizada");
        }
    }
}
