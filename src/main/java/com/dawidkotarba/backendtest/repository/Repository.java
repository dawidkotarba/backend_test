package com.dawidkotarba.backendtest.repository;

import com.dawidkotarba.backendtest.domain.Identifiable;

import java.util.Optional;

public interface Repository<T extends Identifiable> {

    T save(T entity);

    Optional<T> find(long id);
}
