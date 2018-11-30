package com.dawidkotarba.backendtest.domain;

public abstract class Identifiable {

    private Long id;

    public Long getId() {
        return id;
    }

    public synchronized void setId(final Long id) {
        if (this.id != null) {
            throw new IllegalArgumentException("ID has already been set");
        }
        this.id = id;
    }
}
