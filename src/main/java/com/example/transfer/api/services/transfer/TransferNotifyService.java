package com.example.transfer.api.services.transfer;

import com.example.transfer.api.clients.TransferNotifyClient;
import com.example.transfer.api.models.Transfer;
import com.example.transfer.api.models.User;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferNotifyService {

    private final TransferNotifyClient transferNotifyClient;

    public void notifyPayee(User user, Transfer transfer) {
        try {
            transferNotifyClient.sendNotification();

        } catch(FeignException e){
            log.info("Notificação externa falhou");
        }
    }
}
