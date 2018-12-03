package com.dawidkotarba.backendtest.domain.account;

import com.dawidkotarba.backendtest.domain.Identifiable;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class Account extends Identifiable {
    private AtomicReference<BigDecimal> balance;

    public Account withBalance(final BigDecimal balance) {
        Objects.requireNonNull(balance);
        this.balance = new AtomicReference<>(balance);
        return this;
    }

    public BigDecimal getBalance() {
        return balance.get();
    }

    public void add(final BigDecimal amount) {
        while (true) {
            final BigDecimal currentBalance = getBalance();
            final BigDecimal updatedBalance = currentBalance.add(amount);
            if (balance.compareAndSet(currentBalance, updatedBalance)) {
                return;
            }
        }
    }

    public boolean isAmountAvailable(final BigDecimal amount) {
        return getBalance().compareTo(amount) >= 0;
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
