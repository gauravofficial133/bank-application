package com.bank.accounts_service.services.client;

import com.bank.accounts_service.dtos.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "card-service", fallback = CardsFallback.class)
public interface CardsFeignClient {

    @GetMapping(value = "/cards/get-card-by-mobile-number",consumes = "application/json")
    public ResponseEntity<CardsDto> getCardDetailsByMobileNumber(@RequestHeader("bank-correlation-id") String correlationId, @RequestParam String mobileNumber);
}
