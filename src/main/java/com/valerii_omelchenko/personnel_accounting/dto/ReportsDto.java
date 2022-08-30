package com.valerii_omelchenko.personnel_accounting.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 DTO for Reports
 */
@Data
@Builder
public class ReportsDto implements Serializable {
    private Map<Integer, String> mapDepartments;

    private int keyMapDepartment;

    private boolean useSelectionForDepartment;

    private boolean useSelectionForSalary;

    private double salary;

    private int keyOperatorForSalary;

    private Map<Integer, String> mapOperators;

    private boolean useSelectionForEmploymentDate;

    private int keyOperatorForEmploymentDate;

    private String EmploymentDate;

    public boolean isUseSelectionForEmploymentDate() {
        return useSelectionForEmploymentDate;
    }

    public void setUseSelectionForEmploymentDate(boolean useSelectionForEmploymentDate) {
        this.useSelectionForEmploymentDate = useSelectionForEmploymentDate;
    }

    public int getKeyOperatorForEmploymentDate() {
        return keyOperatorForEmploymentDate;
    }

    public void setKeyOperatorForEmploymentDate(int keyOperatorForEmploymentDate) {
        this.keyOperatorForEmploymentDate = keyOperatorForEmploymentDate;
    }

    public String getEmploymentDate() {
        return EmploymentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        EmploymentDate = employmentDate;
    }

    public void setMapOperators(Map<Integer, String> mapOperators) {
        this.mapOperators = mapOperators;
    }

    public boolean isUseSelectionForSalary() {
        return useSelectionForSalary;
    }

    public void setUseSelectionForSalary(boolean useSelectionForSalary) {
        this.useSelectionForSalary = useSelectionForSalary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getOperatorForSalary() {
        return operatorForSalary;
    }

    public void setOperatorForSalary(String operatorForSalary) {
        this.operatorForSalary = operatorForSalary;
    }

    private String operatorForSalary;

    public Map<Integer, String> getMapDepartments() {
        return mapDepartments;
    }

    public boolean isUseSelectionForDepartment() {
        return useSelectionForDepartment;
    }

    public void setUseSelectionForDepartment(boolean useSelectionForDepartment) {
        this.useSelectionForDepartment = useSelectionForDepartment;
    }

    public void setMapDepartments(Map<Integer, String> mapDepartments) {
        this.mapDepartments = mapDepartments;
    }

    public int getKeyMapDepartment() {
        return keyMapDepartment;
    }

    public void setKeyMapDepartment(int keyMapDepartment) {
        this.keyMapDepartment = keyMapDepartment;
    }

    /**
     Get Map  Operators
     1- >
     2- <
     3- =
     */
    public Map<Integer, String> getMapOperators(){
        Map<Integer, String> mapOperators = new HashMap<>();
        mapOperators.put(1,">");
        mapOperators.put(2,"<");
        mapOperators.put(3,"=");
        return mapOperators;
    }

    public int getKeyOperatorForSalary() {
        return keyOperatorForSalary;
    }

    public void setKeyOperatorForSalary(int keyOperatorForSalary) {
        this.keyOperatorForSalary = keyOperatorForSalary;
    }
}
