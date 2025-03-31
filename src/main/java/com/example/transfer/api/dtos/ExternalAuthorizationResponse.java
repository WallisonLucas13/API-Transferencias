package com.example.transfer.api.dtos;

public record ExternalAuthorizationResponse(String status, ExternalAuthorizationData data) {

    public record ExternalAuthorizationData(boolean authorization) {
    }
}
