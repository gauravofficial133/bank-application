package com.bank.accounts_service.services.client;

import com.bank.accounts_service.dtos.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient{
    @Override
    public ResponseEntity<LoansDto> getLoanDetailsByMobileNumber(String correlationId, String mobileNumber) {
        return null;
    }
}
