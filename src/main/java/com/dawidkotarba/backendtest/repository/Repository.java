package com.dawidkotarba.backendtest.repository;

import com.dawidkotarba.backendtest.domain.Identifiable;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends Identifiable> {

    T save(T entity);

    Optional<T> find(long id);

    List<T> findAll();

    void remove(long id);

    void removeAll();

}
