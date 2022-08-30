package com.valerii_omelchenko.personnel_accounting.service;

import com.valerii_omelchenko.personnel_accounting.entity.Department;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {
    /**
     Get list departments
     */
    public List<Department> getAllDepartments();

    /**
     Save department to the database
     params: department
     */
    public void saveDepartment(Department department);

    /**
     Get department by id
     params: department
     */
    public Optional<Department> getDepartmentById(int id);

    /**
     Delete department by id
     params: department
     */
    public void deleteDepartmentById(int id);

    /**
     Get Map departments
     */
    public Map<Integer,String> getMapDepartments();
}
