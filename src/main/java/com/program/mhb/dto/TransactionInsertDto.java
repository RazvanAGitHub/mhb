package com.program.mhb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TransactionInsertDto {

    @NotNull
    Integer accountId;

    private String transactionDetails;

    @NotNull(message = "Debit must not be null (0 or positive)")
    private Long debit;

    @NotNull(message = "Credit must not be null (0 or negative)")
    private Long credit;
}
