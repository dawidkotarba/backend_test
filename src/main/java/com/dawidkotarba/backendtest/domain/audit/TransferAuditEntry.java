package com.dawidkotarba.backendtest.domain.audit;

import com.dawidkotarba.backendtest.domain.Identifiable;

import java.math.BigDecimal;
import java.util.Objects;

public class TransferAuditEntry extends Identifiable {
    private final Long senderAccountId;
    private final Long receiverAccountId;
    private final BigDecimal amount;
    private final String title;
    private TransferStatus status;

    public TransferAuditEntry(final Long senderAccountId, final Long receiverAccountId, final BigDecimal amount, final String title) {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
        this.title = title;
    }

    public Long getSenderAccountId() {
        return senderAccountId;
    }

    public Long getReceiverAccountId() {
        return receiverAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getTitle() {
        return title;
    }

    public TransferAuditEntry withStatus(final TransferStatus status) {
        this.status = status;
        return this;
    }

    public TransferStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransferAuditEntry)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final TransferAuditEntry that = (TransferAuditEntry) o;
        return Objects.equals(senderAccountId, that.senderAccountId) &&
                Objects.equals(receiverAccountId, that.receiverAccountId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        return "TransferAuditEntry{" +
                "senderAccountId=" + senderAccountId +
                ", receiverAccountId=" + receiverAccountId +
                ", amount=" + amount +
                ", title='" + title + '\'' +
                "} " + super.toString();
    }
}
