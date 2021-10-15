package com.program.mhb.helper;

import com.program.mhb.dto.AccountViewDto;
import com.program.mhb.exception.AccountErrorDetails;
import com.program.mhb.exception.AccountException;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Getter
@Component
public class AccountValidator {

    public void isAccountViewDtoValid(AccountViewDto accountViewDto) throws AccountException {
        if (accountViewDto.getCustomer_id() == null) {
            throw AccountException.builder()
                    .message(AccountErrorDetails.CUSTOMER_ID_INVALID.getReasonPhrase())
                    .code(AccountErrorDetails.CUSTOMER_ID_INVALID.getValue())
                    .build();
        }

        if (accountViewDto.getIban().isEmpty() || accountViewDto.getIban().isBlank()) {
            throw AccountException.builder()
                    .message(AccountErrorDetails.ACCOUNT_IBAN_INVALID.getReasonPhrase())
                    .code(AccountErrorDetails.ACCOUNT_IBAN_INVALID.getValue())
                    .build();
        }

        if (accountViewDto.getCurrency() == null) {
            throw AccountException.builder()
                    .message(AccountErrorDetails.ACCOUNT_CURRENCY_INVALID.getReasonPhrase())
                    .code(AccountErrorDetails.ACCOUNT_CURRENCY_INVALID.getValue())
                    .build();
        }
    }
}
