package com.bank.accounts_service.services;

import com.bank.accounts_service.dtos.CustomerDetailsDto;

public interface ICustomerService {
    CustomerDetailsDto getCustomerDetails(String mobileNumber, String correlationId);
}
