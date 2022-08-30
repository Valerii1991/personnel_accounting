package com.valerii_omelchenko.personnel_accounting.dao;

import com.valerii_omelchenko.personnel_accounting.entity.Department;
import com.valerii_omelchenko.personnel_accounting.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 EmployeeRepository
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
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
}
