package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;

/* This interface includes the methods for employee. (Service-layer) */
public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);

}
