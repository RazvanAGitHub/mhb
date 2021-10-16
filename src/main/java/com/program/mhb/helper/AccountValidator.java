package com.program.mhb.helper;

import com.program.mhb.dto.AccountViewDto;
import com.program.mhb.exception.AccountErrorDetails;
import com.program.mhb.exception.AccountException;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Getter
@Component
@Log4j2
public class AccountValidator {

    public void isAccountViewDtoValid(AccountViewDto accountViewDto) throws AccountException {

        if (accountViewDto.getIban().length() != 24) {
            log.error("IBAN in Romania consists of 24 characters.");
            throw AccountException.builder()
                    .message(AccountErrorDetails.ACCOUNT_IBAN_INVALID.getReasonPhrase())
                    .code(AccountErrorDetails.ACCOUNT_IBAN_INVALID.getValue())
                    .build();
        }

    }
}
