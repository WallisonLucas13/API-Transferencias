package com.example.transfer.api.services.transfer.utils;

import com.example.transfer.api.dtos.TransferDto;
import com.example.transfer.api.enums.PersonType;
import com.example.transfer.api.exceptions.InsufficientBalanceException;
import com.example.transfer.api.exceptions.InvalidPersonIdException;
import com.example.transfer.api.exceptions.PersonNotAllowedToMakeTransfersException;
import com.example.transfer.api.exceptions.UnauthorizedTransferException;
import com.example.transfer.api.models.Person;
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

    public void validateTransferPersonsId(TransferDto transferDto) {
        if(transferDto.payerId().compareTo(transferDto.payeeId()) == 0) {
            throw new InvalidPersonIdException("Pagador e recebedor devem ser diferentes");
        }
    }

    public void validatePayerType(Person person) {
        if(person.getType().equals(PersonType.SHOPKEEPER)) {
            throw new PersonNotAllowedToMakeTransfersException("Lojistas não podem fazer transferências");
        }
    }

    public void validatePayerBalance(Person person, TransferDto transferDto) {
        if(person.getWallet().getBalance().compareTo(transferDto.value()) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente");
        }
    }

    public void validateTransferExternalAuthorization(Person person, TransferDto transferDto) {
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
