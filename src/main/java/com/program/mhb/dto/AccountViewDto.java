package com.program.mhb.dto;

import com.program.mhb.domain.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountViewDto {

    private int customer_id;
    private String iban;
    private Currency currency;
}
