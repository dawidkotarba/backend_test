package com.dawidkotarba.backendtest.service.validator;

import com.dawidkotarba.backendtest.domain.Identifiable;

public interface Validator<T extends Identifiable> {

    void validate(T entity);
}
