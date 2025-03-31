package com.example.transfer.api.services.transfer;

import com.example.transfer.api.clients.TransferExternalAuthorizationClient;
import com.example.transfer.api.dtos.TransferDto;
import com.example.transfer.api.enums.UserType;
import com.example.transfer.api.exceptions.InsufficientBalanceException;
import com.example.transfer.api.exceptions.InvalidUserIdException;
import com.example.transfer.api.exceptions.UnauthorizedTransferException;
import com.example.transfer.api.exceptions.UserNotAllowedToMakeTransfersException;
import com.example.transfer.api.models.User;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class TransferValidationService {

    private final TransferExternalAuthorizationClient transferExternalAuthorizationClient;

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
           if(!transferExternalAuthorizationClient.authorizeTransfer().data().authorization()){
               throw new UnauthorizedTransferException("Transferência não autorizada");
           }
        } catch(FeignException e){
            log.info("Autorização externa falhou");
            throw new UnauthorizedTransferException("Transferência não autorizada");
        }
    }
}
