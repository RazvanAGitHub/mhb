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
public class AccountShortViewDto {

    private String iban;
    private Currency currency;
}
