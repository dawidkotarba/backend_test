package com.dawidkotarba.backendtest.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountEntity extends Identifiable {
    private BigDecimal balance;

    public static AccountEntity create(final Long id) {
        return new AccountEntity(id);
    }

    private AccountEntity(final Long id) {
        super(id);
        balance = BigDecimal.ZERO;
    }

    public AccountEntity withBalance(final BigDecimal balance) {
        Objects.requireNonNull(balance);
        this.balance = balance;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void add(final BigDecimal amount) {
        balance.add(amount);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountEntity)) {
            return false;
        }
        final AccountEntity that = (AccountEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + getId() +
                "balance=" + balance +
                '}';
    }
}
