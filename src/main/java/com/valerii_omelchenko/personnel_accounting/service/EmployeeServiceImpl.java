package com.valerii_omelchenko.personnel_accounting.service;

import com.valerii_omelchenko.personnel_accounting.dao.EmployeeRepository;
import com.valerii_omelchenko.personnel_accounting.dto.ReportsDto;
import com.valerii_omelchenko.personnel_accounting.entity.Department;
import com.valerii_omelchenko.personnel_accounting.entity.Employee;
import com.valerii_omelchenko.personnel_accounting.factory.SelectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    /**
     Get list Employee
     params department
     */
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     Save Employee to database
     params employee
     */
    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    /**
     Get Employee by id
     params id
     */
    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    /**
     Delete Employee by id
     params id
     */
    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    /**
     Get list Employee by department
     params department
     */
    @Override
    public List<Employee> getAllByDepartment(Department department) {
        return employeeRepository.getAllByDepartment(department);
    }

    /**
     Get list Employee by salary after selected value
     params salary
     */
    @Override
    public List<Employee> getAllBySalaryIsAfter(double salary) {
        return employeeRepository.getAllBySalaryIsAfter(salary);
    }

    /**
     Get list Employee by salary before selected value
     params salary
     */
    @Override
    public List<Employee> getAllBySalaryIsBefore(double salary) {
         return employeeRepository.getAllBySalaryIsBefore(salary);
    }

    /**
     Get list Employee by salary
     params salary
     */
    @Override
    public List<Employee> getAllBySalary(double salary) {
        return employeeRepository.getAllBySalary(salary);
    }

    /**
     Get list Employee by EmploymentDate after selected value
     params employmentDate
     */
    @Override
    public List<Employee> getAllByEmploymentDateIsAfter(LocalDate employmentDate) {
        return employeeRepository.getAllByEmploymentDateIsAfter(employmentDate);
    }

    /**
     Get list Employee by EmploymentDate before selected value
     params employmentDate
     */
    @Override
    public List<Employee> getAllByEmploymentDateIsBefore(LocalDate employmentDate) {
        return employeeRepository.getAllByEmploymentDateIsBefore(employmentDate);
    }

    /**
     Get list Employee by EmploymentDate
     params employmentDate
     */
    @Override
    public List<Employee> getAllByEmploymentDate(LocalDate employmentDate) {
        return employeeRepository.getAllByEmploymentDate(employmentDate);
    }

    /**
     Get list Employee by ReportsDto
     params reportsDto, selectionFactory
     */
    @Override
    public List<Employee> getAllByReportsDto(ReportsDto reportsDto, SelectionFactory selectionFactory) {
        List<Employee> employeeListByDepartment = new ArrayList<>();
        List<Employee> employeeListBySalary = new ArrayList<>();
        List<Employee> employeeListByEmploymentDate = new ArrayList<>();
        List<Employee> allEmployees = getAllEmployees();
        selectionFactory.setMessage("Selection: ");
        selectionFactory.setEmployeeService(this);

        if(reportsDto.isUseSelectionForDepartment()){
            Department department = departmentService.getDepartmentById(reportsDto.getKeyMapDepartment()).get();
            employeeListByDepartment = getAllByDepartment(department);
            allEmployees.retainAll(employeeListByDepartment);
            selectionFactory.setMessage(selectionFactory.getMessage() + "\n department = " + department.getName());
        }

        if(reportsDto.isUseSelectionForSalary()){
            double salary = reportsDto.getSalary();
            selectionFactory.setSalary(salary);
            employeeListBySalary = selectionFactory.getListEmployee("salary", reportsDto.getKeyOperatorForSalary());
            allEmployees.retainAll(employeeListBySalary);
        }

        if(reportsDto.isUseSelectionForEmploymentDate()){
            LocalDate employmentDate = LocalDate.parse(reportsDto.getEmploymentDate());
            selectionFactory.setEmploymentDate(employmentDate);
            employeeListByEmploymentDate = selectionFactory.getListEmployee("employmentDate", reportsDto.getKeyOperatorForEmploymentDate());
            allEmployees.retainAll(employeeListByEmploymentDate);
        }

        return allEmployees;
    }
}
