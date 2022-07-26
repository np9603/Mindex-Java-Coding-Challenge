package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
//import java.util.*;
/* This class defines the method implementations for reporting structure service. */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ReportingStructure read(String employeeId) {
        LOG.debug("Reading reporting structure for employee with id [{}]", employeeId);

        Employee employee = employeeService.read(employeeId);
        int totalDistinctReports = getNumberOfReports(employeeId);

        return new ReportingStructure(employee,totalDistinctReports);

    }


    /* This method calculates the total number of direct reports for the employeeId (John) that is passed at the
    controller layer with the help of recursion. First, it reads the employeeId (John) and fetches the employee details.
    The reports variable stores the immediate/direct reports for the input employeeId (John). reportTotal is used to
    keep track of the total number of reports. The for-loop iterates through the immediate/direct reports, increments
    the report counter by 1 and then recursively calls the same function again with the employeeId
    (Paul -> Ringo -> Pete -> George). */
//    HashSet<String> set = new HashSet<>();
    public int getNumberOfReports(String employeeId) {

        Employee employee = employeeService.read(employeeId);

        List<Employee> reports = employee.getDirectReports();

        if(reports == null){
            throw new RuntimeException("No direct report found for id : " + employeeId);
        }

        int reportTotal = 0;

        for (Employee emp : reports) {

//            if(!set.contains(emp.getEmployeeId())) {
                try {
//                System.out.println(emp.getEmployeeId());
//                System.out.println(reportTotal + "       " + emp.getEmployeeId() + "    " + emp.getFirstName());
//                    set.add(emp.getEmployeeId());
//                    System.out.println(set);
                    reportTotal += 1;
                    reportTotal += getNumberOfReports(emp.getEmployeeId());
//                LOG.debug("Found an employee that reports to someone with id [{}]", e.getEmployeeId());

                } catch (RuntimeException ex) {
//                LOG.debug("No direct report found for id : " + e.getEmployeeId());
                }
//            }
        }
//        System.out.println(reportTotal);
        return reportTotal;
    }

}
