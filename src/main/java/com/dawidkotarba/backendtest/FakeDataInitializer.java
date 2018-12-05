package com.dawidkotarba.backendtest;

import com.dawidkotarba.backendtest.domain.account.Account;
import com.dawidkotarba.backendtest.repository.Repository;
import io.micronaut.context.annotation.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;

@Context
public class FakeDataInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(FakeDataInitializer.class);
    private static final int FAKE_ACCOUNTS_COUNT = 5;
    private static final BigDecimal FAKE_ACCOUNTS_BALANCE = BigDecimal.valueOf(100);

    private final Repository<Account> accountRepository;

    @Inject
    public FakeDataInitializer(@Named("accountRepository") final Repository<Account> accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < FAKE_ACCOUNTS_COUNT; i++) {
            final Account fakeAccount = new Account().withBalance(FAKE_ACCOUNTS_BALANCE);
            accountRepository.save(fakeAccount);
            LOG.info("Added fake account: {}", fakeAccount);
        }
    }
}
