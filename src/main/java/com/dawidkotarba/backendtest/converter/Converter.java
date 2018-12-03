package com.dawidkotarba.backendtest.converter;

import com.dawidkotarba.backendtest.domain.Identifiable;

public interface Converter<X, Y extends Identifiable> {
    Y convert(X dto);
}
