package com.bank.accounts_service.services.client;

import com.bank.accounts_service.dtos.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{
    @Override
    public ResponseEntity<CardsDto> getCardDetailsByMobileNumber(String correlationId, String mobileNumber) {
        return null;
    }
}
