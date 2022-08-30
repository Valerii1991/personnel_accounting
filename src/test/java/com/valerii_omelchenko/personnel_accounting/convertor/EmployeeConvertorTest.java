package com.valerii_omelchenko.personnel_accounting.convertor;

import com.valerii_omelchenko.personnel_accounting.enums.Sex;
import com.valerii_omelchenko.personnel_accounting.dto.EmployeeDto;
import com.valerii_omelchenko.personnel_accounting.entity.Department;
import com.valerii_omelchenko.personnel_accounting.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;



class EmployeeConvertorTest {

    Employee employee;
    EmployeeDto employeeDto;
    EmployeeConvertor employeeConvertor;

    @BeforeEach
    void setUp() {
        Department department= new Department();
        department.setId(1);
        department.setName("IT");

        employee = new Employee();
        employee.setId(1);
        employee.setName("Valerii");
        employee.setSurname("Omelchenko");
        employee.setSex(Sex.MALE);
        employee.setSalary(40000.00);
        employee.setDepartment(department);
        employee.setBirthday(LocalDate.of(1991,3,3));
        employee.setEmploymentDate(LocalDate.of(2014,1,21));
        employee.setDateOfDismissal(LocalDate.of(2022,3,1));

        employeeDto = EmployeeDto.builder()
                .id(1)
                .name("Valerii")
                .surname("Omelchenko")
                .sex(Sex.MALE)
                .department(department)
                .salary(40000.00)
                .birthday("1991-03-03")
                .employmentDate("2014-01-21")
                .dateOfDismissal("2022-03-01")
                .build();

        employeeConvertor = new EmployeeConvertor();
    }

    @Test
    void testEmployeeToEmployeeDto() {
        assertEquals(employeeDto.getBirthday(),employeeConvertor.employeeToEmployeeDto(employee).getBirthday(),"EmployeeToEmployeeDtoBirthday is incorrect");
        assertEquals(employeeDto.getEmploymentDate(),employeeConvertor.employeeToEmployeeDto(employee).getEmploymentDate(),"EmployeeToEmployeeDtoEmploymentDate is incorrect");
        assertEquals(employeeDto.getDateOfDismissal(),employeeConvertor.employeeToEmployeeDto(employee).getDateOfDismissal(),"EmployeeToEmployeeDtoDateOfDismissal is incorrect");
    }
}