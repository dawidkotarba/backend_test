package com.dawidkotarba.backendtest.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;

import java.math.BigDecimal;

/**
 * This class configures parameters of a proper transfer like minimal and maximal amount or its scale.
 */
@ConfigurationProperties("transfer")
public class TransferConfiguration {
    private final BigDecimal minTransferAmount = new BigDecimal("0.01");
    private final BigDecimal maxTransferAmount = new BigDecimal("100000000");
    private final int scale = 2;
    private final int maxTitleLength = 200;

    public BigDecimal getMinTransferAmount() {
        return minTransferAmount;
    }

    public BigDecimal getMaxTransferAmount() {
        return maxTransferAmount;
    }

    public int getScale() {
        return scale;
    }

    public int getMaxTitleLength() {
        return maxTitleLength;
    }
}
