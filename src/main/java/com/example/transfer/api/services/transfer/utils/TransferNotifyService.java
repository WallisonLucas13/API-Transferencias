package com.example.transfer.api.services.transfer.utils;

import com.example.transfer.api.models.Person;
import com.example.transfer.api.models.Transfer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class TransferNotifyService {

    private final RestClient restClient;

    public TransferNotifyService(@Value("${external.notify.api.url}") String notifyUrl) {
        this.restClient = RestClient.create(notifyUrl);
    }

    public void notifyPayee(Person person, Transfer transfer) {
        try {
            restClient
                    .post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toBodilessEntity();

        } catch(HttpClientErrorException | HttpServerErrorException e){
            log.info("Notificação externa falhou, status -> {}", e.getStatusCode());
        }
    }
}
