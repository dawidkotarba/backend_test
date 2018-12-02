package com.dawidkotarba.backendtest.domain.account;

import com.dawidkotarba.backendtest.domain.Identifiable;

import java.math.BigDecimal;
import java.util.Objects;

public class Account extends Identifiable {
    private BigDecimal balance;

    public Account withBalance(final BigDecimal balance) {
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
        if (!(o instanceof Account)) {
            return false;
        }
        final Account that = (Account) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                "} " + super.toString();
    }
}
