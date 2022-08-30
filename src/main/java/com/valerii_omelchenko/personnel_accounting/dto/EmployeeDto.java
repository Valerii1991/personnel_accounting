package com.valerii_omelchenko.personnel_accounting.dto;

import com.valerii_omelchenko.personnel_accounting.enums.Sex;
import com.valerii_omelchenko.personnel_accounting.entity.Department;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
/**
 DTO for Employee
 */
public class EmployeeDto  implements Serializable{

    private Integer id;

    @Size(min = 2, message = "name must be min 2 symbol")
    private String name;

    @Size(min = 2, message = "surname must be min 2 symbol")
    private String surname;

    private Sex sex;

    private Double salary;

    private Department department;

    private String birthday;

    @NotEmpty(message = "employment date should not be empty")
    private String employmentDate;

    private String dateOfDismissal;

    private Map<Integer, Sex> sexTypes;

    private Map<Integer, String> mapDepartments;

    private int keySexType;

    private int keyMapDepartment;

    public Integer getId() {
        return id;
    }

    public void setMapDepartments(Map<Integer, String> mapDepartments) {
        this.mapDepartments = mapDepartments;
    }

    public int getKeySexType() {
        return keySexType;
    }

    public void setKeySexType(int keySexType) {
        this.keySexType = keySexType;
    }

    public int getKeyMapDepartment() {
        return keyMapDepartment;
    }

    public void setKeyMapDepartment(int keyMapDepartment) {
        this.keyMapDepartment = keyMapDepartment;
    }

    public Map<Integer, String> getMapDepartments() {
        return mapDepartments;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getDateOfDismissal() {
        return dateOfDismissal;
    }

    public void setDateOfDismissal(String dateOfDismissal) {
        this.dateOfDismissal = dateOfDismissal;
    }

    /**
     Get Map Sex types
     1- MALE
     2- FEMALE
     */
    public Map<Integer, Sex> getSexTypes() {
        Map<Integer, Sex> sexTypes = new HashMap<>();
        sexTypes.put(1,Sex.MALE);
        sexTypes.put(2,Sex.FEMALE);
        return sexTypes;
    }

    public void setSexTypes(Map<Integer, Sex> sexTypes) {
        this.sexTypes = sexTypes;
    }

}
