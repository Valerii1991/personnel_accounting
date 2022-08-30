package com.valerii_omelchenko.personnel_accounting.service;

import com.valerii_omelchenko.personnel_accounting.dao.DepartmentRepository;
import com.valerii_omelchenko.personnel_accounting.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     Get list departments
     */
    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    /**
     Get Map departments
     */
    @Override
    public Map<Integer, String> getMapDepartments() {
        Map<Integer, String> mapDepartments = departmentRepository.findAll().stream()
                .collect(Collectors.toMap(Department::getId, Department::getName));
        return mapDepartments;
    }

    /**
     Save department to the database
     params: department
     */
    @Override
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    /**
     Get department by id
     params: department
     */
    @Override
    public Optional<Department> getDepartmentById(int id) {
        return departmentRepository.findById(id);
    }

    /**
     Delete department by id
     params: department
     */
    @Override
    public void deleteDepartmentById(int id) {
        departmentRepository.deleteById(id);
    }
}
