package com.example.transfer.api.services.transfer;

import com.example.transfer.api.dtos.TransferDto;
import com.example.transfer.api.enums.TransferStatus;
import com.example.transfer.api.models.Transfer;
import com.example.transfer.api.models.User;
import com.example.transfer.api.repositories.TransferRepository;
import com.example.transfer.api.services.UserService;
import com.example.transfer.api.services.transfer.TransferNotifyService;
import com.example.transfer.api.services.transfer.TransferValidationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class TransferService {

    private final UserService userService;

    private final TransferValidationService transferValidationService;

    private final TransferNotifyService transferNotifyService;

    private final TransferRepository transferRepository;

    @Transactional
    public void transferMoney(TransferDto transferDto) {
        log.info("Validando ID do pagador e recebedor...");
        transferValidationService.validateTransferUsersId(transferDto);

        log.info("Buscando pagador e recebedor...");
        var payer = userService.findById(transferDto.payerId());
        var payee = userService.findById(transferDto.payeeId());

        log.info("Validando transferência...");
        transferValidationService.validatePayerType(payer);
        transferValidationService.validatePayerBalance(payer, transferDto);
        transferValidationService.validateTransferExternalAuthorization(payer, transferDto);

        var newTransfer = Transfer.builder()
                .value(transferDto.value())
                .payer(payer)
                .payee(payee)
                .status(TransferStatus.PENDING)
                .build();

        this.processTransfer(newTransfer, payer, payee);
    }

    @Transactional
    private void processTransfer(Transfer transfer, User payer, User payee) {
        log.info("Processando transferência...");
        payer.getWallet().setBalance(payer.getWallet().getBalance().subtract(transfer.getValue()));
        payee.getWallet().setBalance(payee.getWallet().getBalance().add(transfer.getValue()));
        transfer.setStatus(TransferStatus.COMPLETED);

        log.info("Salvando transferência...");
        transferRepository.save(transfer);
        userService.updateUser(payer, transfer);
        userService.updateUser(payee, transfer);

        log.info("Notificando recebedor...");
        transferNotifyService.notifyPayee(payee, transfer);
    }

}
