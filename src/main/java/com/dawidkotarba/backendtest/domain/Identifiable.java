package com.dawidkotarba.backendtest.domain;

import java.util.Objects;

public abstract class Identifiable {

    private final Long id;

    Identifiable(final Long id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
