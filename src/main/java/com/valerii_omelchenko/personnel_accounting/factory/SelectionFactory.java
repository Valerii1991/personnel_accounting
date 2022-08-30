package com.valerii_omelchenko.personnel_accounting.factory;

import com.valerii_omelchenko.personnel_accounting.entity.Employee;
import com.valerii_omelchenko.personnel_accounting.service.EmployeeService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 Factory for defining filtering by field and operation type
 */
@Component
public class SelectionFactory {
    private EmployeeService employeeService;
    private String message;
    private Double salary;
    private LocalDate employmentDate;

    /**
     Gets a list of employees
     params: field selection, operator selection (1 >, 2 <, 3 =)
     */
    public  List<Employee> getListEmployee(String field, int operator){
        List<Employee> employeeList = new ArrayList<>();
        if(field.equals("salary")){
             employeeList =  handlerSelectionSalary(operator);
        }
        else if(field.equals("employmentDate")){
            employeeList =  handlerSelectionEmploymentDate(operator);
        }
        return employeeList;
    }

    /**
     Gets a list of employees for field selection EmploymentDate
     params: operator selection (1 >, 2 <, 3 =)
     */
    public List<Employee> handlerSelectionEmploymentDate(int operator) {
        List<Employee> employeeList = new ArrayList<>();
        if (operator ==1) {
            employeeList = employeeService.getAllByEmploymentDateIsAfter(employmentDate);
            message += "\n employment Date > " + employmentDate.format(DateTimeFormatter.ISO_DATE);
        }

        else if (operator ==2){
            employeeList = employeeService.getAllByEmploymentDateIsBefore(employmentDate);
            message += "\n employment Date < " + employmentDate.format(DateTimeFormatter.ISO_DATE);
        }

        else if (operator ==3){
            employeeList = employeeService.getAllByEmploymentDate(employmentDate);
            message += "\n employment Date = " + employmentDate.format(DateTimeFormatter.ISO_DATE);
        }

        return employeeList;
    }

    /**
     Gets a list of employees for field selection Salary
     params: operator selection (1 >, 2 <, 3 =)
     */
    public  List<Employee> handlerSelectionSalary(int operator){
        List<Employee> employeeList = new ArrayList<>();
        if (operator ==1) {
            employeeList = employeeService.getAllBySalaryIsAfter(salary);
            message += "\n salary > " + salary;
        }

        else if (operator ==2){
            employeeList = employeeService.getAllBySalaryIsBefore(salary);
            message += "\n salary < " + salary;
        }

        else if (operator ==3){
            employeeList = employeeService.getAllBySalary(salary);
            message += "\n salary = " + salary;
        }
        return employeeList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
