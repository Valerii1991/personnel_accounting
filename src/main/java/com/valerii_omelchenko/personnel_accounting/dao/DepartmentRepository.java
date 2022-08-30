package com.valerii_omelchenko.personnel_accounting.dao;

import com.valerii_omelchenko.personnel_accounting.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 DepartmentRepository
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}