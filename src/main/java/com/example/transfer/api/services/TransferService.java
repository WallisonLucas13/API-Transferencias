package com.example.transfer.api.services;

import com.example.transfer.api.dtos.TransferDto;
import com.example.transfer.api.enums.TransferStatus;
import com.example.transfer.api.models.Person;
import com.example.transfer.api.models.Transfer;
import com.example.transfer.api.repositories.TransferRepository;
import com.example.transfer.api.services.transfer.utils.TransferNotifyService;
import com.example.transfer.api.services.transfer.utils.TransferValidationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class TransferService {

    private final PersonService personService;

    private final TransferValidationService transferValidationService;

    private final TransferNotifyService transferNotifyService;

    private final TransferRepository transferRepository;

    @Transactional
    public void transferMoney(TransferDto transferDto) {
        log.info("Validando ID do pagador e recebedor...");
        transferValidationService.validateTransferPersonsId(transferDto);

        log.info("Buscando pagador e recebedor...");
        var payer = personService.findById(transferDto.payerId());
        var payee = personService.findById(transferDto.payeeId());

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
    private void processTransfer(Transfer transfer, Person payer, Person payee) {
        log.info("Processando transferência...");
        payer.getWallet().setBalance(payer.getWallet().getBalance().subtract(transfer.getValue()));
        payee.getWallet().setBalance(payee.getWallet().getBalance().add(transfer.getValue()));
        transfer.setStatus(TransferStatus.COMPLETED);

        log.info("Salvando transferência...");
        transferRepository.save(transfer);
        personService.updatePerson(payer, transfer);
        personService.updatePerson(payee, transfer);

        log.info("Notificando recebedor...");
        transferNotifyService.notifyPayee(payee, transfer);
    }

}
