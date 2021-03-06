package com.program.mhb.service.impl;

import com.program.mhb.domain.Account;
import com.program.mhb.domain.Customer;
import com.program.mhb.domain.Status;
import com.program.mhb.dto.AccountViewDto;
import com.program.mhb.exception.NotFoundErrorDetails;
import com.program.mhb.exception.NotFoundException;
import com.program.mhb.repository.AccountRepository;
import com.program.mhb.repository.CustomerRepository;
import com.program.mhb.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Account> getAll() {
        return accountRepository.getAllByStatus(Status.ACTIVE);//findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountViewDto getById(int id) {
        Account account;
        Optional<Account> optionalAccount = accountRepository.getByStatusAndId(Status.ACTIVE, id);
        if (optionalAccount.isPresent()) {
            account = optionalAccount.get();
        } else {
            throw new NotFoundException(NotFoundErrorDetails.NOT_FOUND_ID_INVALID.getReasonPhrase(),
                    NotFoundErrorDetails.NOT_FOUND_ID_INVALID.getValue());
        }

        return new AccountViewDto(account.getCustomer().getId(), account.getIban(), account.getCurrency());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Account> getAccountsByCustomer_Id(int id) {
        return accountRepository.getAllByStatusAndAndCustomer_Id(Status.ACTIVE, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(@Valid AccountViewDto accountViewDto) {
        Account account = new Account();

        Customer customer;
        Optional<Customer> customerOptional = customerRepository.findById(accountViewDto.getCustomer_id());

        if (customerOptional.isPresent()) {
            customer = customerOptional.get();
        } else {
            throw new NotFoundException(NotFoundErrorDetails.NOT_FOUND_ID_INVALID.getReasonPhrase(),
                    NotFoundErrorDetails.NOT_FOUND_ID_INVALID.getValue());
        }

        account.setCustomer(customer);
        account.setIban(accountViewDto.getIban());
        account.setCurrency(accountViewDto.getCurrency());
        account.setStatus(Status.ACTIVE);

        accountRepository.save(account);
        log.info("#################### Account should be created");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeAccountById(@Valid int id) {
        Account account;
        Optional<Account> optionalAccount = accountRepository.getByStatusAndId(Status.ACTIVE, id);
        if (optionalAccount.isPresent()) {
            account = optionalAccount.get();
            account.setStatus(Status.INACTIVE);
        } else {
            throw new NotFoundException(NotFoundErrorDetails.NOT_FOUND_ID_INVALID.getReasonPhrase(),
                    NotFoundErrorDetails.NOT_FOUND_ID_INVALID.getValue());
        }
        accountRepository.save(account);
    }
}
