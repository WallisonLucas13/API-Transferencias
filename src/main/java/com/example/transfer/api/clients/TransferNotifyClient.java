package com.example.transfer.api.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "transfer-notify", url = "${external.notify.api.url}")
public interface TransferNotifyClient {

    @PostMapping("/notify")
    void sendNotification();
}
