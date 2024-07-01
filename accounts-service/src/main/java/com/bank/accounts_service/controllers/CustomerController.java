package com.bank.accounts_service.controllers;

import com.bank.accounts_service.dtos.CustomerDetailsDto;
import com.bank.accounts_service.dtos.ErrorResponseDto;
import com.bank.accounts_service.services.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "REST APIs for Customer in the Bank",
        description = "CRUD REST APIs in the Bank to FETCH customer details"
)
@RestController
@RequestMapping(path = "/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Slf4j
public class CustomerController {

    private final ICustomerService iCustomerService;

    public CustomerController(ICustomerService iCustomerService){
        this.iCustomerService = iCustomerService;
    }

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/get-customer-details")
    public ResponseEntity<CustomerDetailsDto> getCustomersDetails(@RequestHeader("bank-correlation-id") String correlationId, @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber){
        log.info("bank-correlation-id found: {}", correlationId);
        CustomerDetailsDto customerDetails = iCustomerService.getCustomerDetails(mobileNumber, correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetails);
    }
}
