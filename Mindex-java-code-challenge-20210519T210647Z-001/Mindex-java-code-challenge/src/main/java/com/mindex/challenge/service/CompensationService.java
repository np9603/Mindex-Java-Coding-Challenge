package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/* This interface includes the methods for compensation. (Service-layer) */
public interface CompensationService {
    Compensation create(Compensation compensation);
    Compensation read(String employeeId);
}
