package com.valerii_omelchenko.personnel_accounting.convertor;

import com.valerii_omelchenko.personnel_accounting.dto.DepartmentDto;
import com.valerii_omelchenko.personnel_accounting.entity.Department;
import org.springframework.stereotype.Component;

/**
 For converting department to departmentDto and departmentDto to Department
 */
@Component
public class DepartmentConvertor {
    /**
     Converting department to departmentDto
     params: department
     */
    public DepartmentDto departmentToDepartmentDto(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }

    /**
     Converting departmentDto to Department
     params: departmentDto
     */
    public Department departmentDtoToDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());

        return department;
    }
}
