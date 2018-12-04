package com.dawidkotarba.backendtest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class TransferRequestDto {

    @NotNull(message = "Sender account ID cannot be null")
    private Long senderAccountId;

    @NotNull(message = "Receiver account ID cannot be null")
    private Long receiverAccountId;

    @NotNull(message = "Amount to transfer cannot be null")
    private BigDecimal amount;

    @NotBlank(message = "Transfer title cannot be blank")
    private String title;

    public Long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(final Long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public Long getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(final Long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransferRequestDto)) {
            return false;
        }
        final TransferRequestDto that = (TransferRequestDto) o;
        return Objects.equals(senderAccountId, that.senderAccountId)
                && Objects.equals(receiverAccountId, that.receiverAccountId)
                && Objects.equals(amount, that.amount)
                && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderAccountId, receiverAccountId, amount, title);
    }

    @Override
    public String toString() {
        return "TransferRequestDto{"
                + "senderAccountId=" + senderAccountId
                + ", receiverAccountId=" + receiverAccountId
                + ", amount=" + amount
                + ", title='" + title + '\''
                + '}';
    }
}
