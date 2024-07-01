package com.bank.accounts_service.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
@Data
@AllArgsConstructor
public class ResponseDto {
    private String statusCode;

    @Schema(
            name = "Status message in the response",
            example = "Request processed successfully"
    )
    private String statusMessage;
}
