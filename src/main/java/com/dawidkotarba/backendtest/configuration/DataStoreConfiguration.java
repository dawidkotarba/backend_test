package com.dawidkotarba.backendtest.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("datastore")
public class DataStoreConfiguration {

    private boolean printOnSave = true;

    public boolean isPrintOnSave() {
        return printOnSave;
    }

    public void setPrintOnSave(final boolean printOnSave) {
        this.printOnSave = printOnSave;
    }
}
