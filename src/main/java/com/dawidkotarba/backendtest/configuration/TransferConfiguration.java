package com.dawidkotarba.backendtest.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties("transfer")
public class TransferConfiguration {
    private final BigDecimal minTransferAmount = new BigDecimal("0.01");
    private final BigDecimal maxTransferAmount = new BigDecimal("100000000");
    private final int maxTitleLength = 200;
    private final int scale = 2;

    public BigDecimal getMinTransferAmount() {
        return minTransferAmount;
    }

    public BigDecimal getMaxTransferAmount() {
        return maxTransferAmount;
    }

    public int getMaxTitleLength() {
        return maxTitleLength;
    }

    public int getScale() {
        return scale;
    }
}
