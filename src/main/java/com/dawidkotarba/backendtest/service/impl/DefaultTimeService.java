package com.dawidkotarba.backendtest.service.impl;

import com.dawidkotarba.backendtest.service.TimeService;

import javax.inject.Singleton;
import java.time.LocalDateTime;

/**
 * This class provides a current time. Useful especially in testing.
 */
@Singleton
class DefaultTimeService implements TimeService {

    @Override
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
