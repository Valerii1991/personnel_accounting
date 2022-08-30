package com.valerii_omelchenko.personnel_accounting.convertor;

import com.valerii_omelchenko.personnel_accounting.enums.Sex;
import com.valerii_omelchenko.personnel_accounting.dto.EmployeeDto;
import com.valerii_omelchenko.personnel_accounting.entity.Department;
import com.valerii_omelchenko.personnel_accounting.entity.Employee;
import com.valerii_omelchenko.personnel_accounting.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 For converting employee to EmployeeDto and EmployeeDto to employee
 */
@Component
public class EmployeeConvertor {
    @Autowired
    private DepartmentService departmentService;

    /**
     For converting employee to EmployeeDto
     params: employee
     */
    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .salary(employee.getSalary())
                .birthday(employee.getBirthday()==null ? "": employee.getBirthday().format(DateTimeFormatter.ISO_DATE))
                .employmentDate(employee.getEmploymentDate()==null ? "": employee.getEmploymentDate().format(DateTimeFormatter.ISO_DATE))
                .dateOfDismissal(employee.getDateOfDismissal() ==null ? "":employee.getDateOfDismissal().format(DateTimeFormatter.ISO_DATE))
                .keySexType(employee.getSex()== Sex.MALE ? 1:2)
                .sex(employee.getSex())
                .keyMapDepartment(employee.getDepartment()==null?0 : employee.getDepartment().getId())
                .department(employee.getDepartment()==null? new Department():employee.getDepartment())
                .build();
    }

    /**
     For converting EmployeeDto to employee
     params: employeeDto
     */
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setBirthday(employeeDto.getBirthday().isEmpty()? null:LocalDate.parse(employeeDto.getBirthday()));
        employee.setEmploymentDate(employeeDto.getEmploymentDate().isEmpty() ? null:LocalDate.parse(employeeDto.getEmploymentDate()));
        employee.setDateOfDismissal(employeeDto.getDateOfDismissal().isEmpty() ? null:LocalDate.parse(employeeDto.getDateOfDismissal()));
        employee.setSalary(employeeDto.getSalary());
        employee.setDepartment(departmentService.getDepartmentById(employeeDto.getKeyMapDepartment()).isPresent() ? departmentService.getDepartmentById(employeeDto.getKeyMapDepartment()).get():null);
        employee.setSex(employeeDto.getKeySexType() ==1 ? Sex.MALE : Sex.FEMALE);
        return employee;
    }
}
