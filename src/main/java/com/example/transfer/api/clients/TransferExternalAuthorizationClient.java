package com.example.transfer.api.clients;

import com.example.transfer.api.dtos.ExternalAuthorizationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "transfer-external-authorization", url = "${external.authorization.api.url}")
public interface TransferExternalAuthorizationClient {

    @GetMapping("/authorize")
    ExternalAuthorizationResponse authorizeTransfer();
}
