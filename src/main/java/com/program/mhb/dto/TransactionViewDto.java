package com.program.mhb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TransactionViewDto {

    int accountId;

    private LocalDateTime dateTime;

    private String transactionDetails;

    private long debit;

    private long credit;

    private long balance;
}
