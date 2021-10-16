package com.program.mhb.dto;

import com.program.mhb.domain.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountViewDto {

    @NotNull
    private Integer customer_id;

    @NotBlank(message = "Please enter an IBAN")
    @Size(min = 24, max = 24, message = "IBAN in Romania consists of 24 characters")
    private String iban;

    @NotNull(message = "Please enter a currency")
    private Currency currency;
}
