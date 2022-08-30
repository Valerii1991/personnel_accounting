package com.valerii_omelchenko.personnel_accounting.service;

import com.valerii_omelchenko.personnel_accounting.dto.ReportsDto;
import com.valerii_omelchenko.personnel_accounting.entity.Department;
import com.valerii_omelchenko.personnel_accounting.entity.Employee;
import com.valerii_omelchenko.personnel_accounting.factory.SelectionFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    /**
     Get list Employee
     params department
     */
    public List<Employee> getAllEmployees();

    /**
     Save Employee to database
     params employee
     */
    public void saveEmployee(Employee employee);

    /**
     Get Employee by id
     params id
     */
    public Optional<Employee> getEmployeeById(int id);

    /**
     Delete Employee by id
     params id
     */
    public void deleteEmployeeById(int id);
    /**
     Get list Employee by department
     params department
     */
    public List<Employee> getAllByDepartment(Department department);
    /**
     Get list Employee by salary after selected value
     params salary
     */
    public List<Employee> getAllBySalaryIsAfter(double salary);
    /**
     Get list Employee by salary before selected value
     params salary
     */
    public List<Employee> getAllBySalaryIsBefore(double salary);
    /**
     Get list Employee by salary
     params salary
     */
    public List<Employee> getAllBySalary(double salary);
    /**
     Get list Employee by EmploymentDate after selected value
     params employmentDate
     */
    public List<Employee> getAllByEmploymentDateIsAfter(LocalDate employmentDate);
    /**
     Get list Employee by EmploymentDate before selected value
     params employmentDate
     */
    public List<Employee> getAllByEmploymentDateIsBefore(LocalDate employmentDate);
    /**
     Get list Employee by EmploymentDate
     params employmentDate
     */
    public List<Employee> getAllByEmploymentDate(LocalDate employmentDate);

    /**
     Get list Employee by ReportsDto
     params reportsDto, selectionFactory
     */
    public List<Employee> getAllByReportsDto(ReportsDto reportsDto,  SelectionFactory selectionFactory);
}
