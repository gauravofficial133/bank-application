package com.bank.accounts_service.services.impl;

import com.bank.accounts_service.dtos.*;
import com.bank.accounts_service.entities.Accounts;
import com.bank.accounts_service.entities.Customer;
import com.bank.accounts_service.exception.ResourceNotFoundException;
import com.bank.accounts_service.mapper.AccountsMapper;
import com.bank.accounts_service.mapper.CustomerMapper;
import com.bank.accounts_service.repositories.AccountsRepository;
import com.bank.accounts_service.repositories.CustomerRepository;
import com.bank.accounts_service.services.ICustomerService;
import com.bank.accounts_service.services.client.CardsFeignClient;
import com.bank.accounts_service.services.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;

    private CustomerRepository customerRepository;

    private CardsFeignClient cardsFeignClient;

    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto getCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer,new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.getLoanDetailsByMobileNumber(correlationId,mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.getCardDetailsByMobileNumber(correlationId,mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
