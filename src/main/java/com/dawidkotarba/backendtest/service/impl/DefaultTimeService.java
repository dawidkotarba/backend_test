package com.dawidkotarba.backendtest.service.impl;

import com.dawidkotarba.backendtest.service.TimeService;

import javax.inject.Singleton;
import java.time.LocalDateTime;

@Singleton
class DefaultTimeService implements TimeService {

    @Override
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
