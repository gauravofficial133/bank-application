package com.bank.accounts_service.services.client;

import com.bank.accounts_service.dtos.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loan-service", fallback = LoansFallback.class)
public interface LoansFeignClient {

    @GetMapping(value = "/loans/get-loan-by-mobile-number",consumes = "application/json")
    public ResponseEntity<LoansDto> getLoanDetailsByMobileNumber(@RequestHeader("bank-correlation-id") String correlationId, @RequestParam String mobileNumber);
}
