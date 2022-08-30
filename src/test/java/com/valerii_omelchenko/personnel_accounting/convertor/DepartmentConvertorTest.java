package com.valerii_omelchenko.personnel_accounting.convertor;

import com.valerii_omelchenko.personnel_accounting.dto.DepartmentDto;
import com.valerii_omelchenko.personnel_accounting.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DepartmentConvertorTest {

    Department department;
    DepartmentDto departmentDto;
    DepartmentConvertor departmentConvertor;

    @BeforeEach
    void setUp() {
        department = new Department();
        department.setName("IT");
        department.setId(1);
        departmentDto = DepartmentDto.builder().name("IT").id(1).build();

        departmentConvertor = new DepartmentConvertor();
    }

    @Test
    void testDepartmentToDepartmentDto() {
        assertEquals(departmentDto,departmentConvertor.departmentToDepartmentDto(department),"DepartmentToDepartmentDto is incorrect");
    }

    @Test
    void testDepartmentDtoToDepartment() {
        assertEquals(department,departmentConvertor.departmentDtoToDepartment(departmentDto),"DepartmentDtoToDepartment is incorrect");
    }
}