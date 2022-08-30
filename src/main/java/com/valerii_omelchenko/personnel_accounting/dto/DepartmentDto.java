package com.valerii_omelchenko.personnel_accounting.dto;

import lombok.Builder;
import lombok.Data;


import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 DTO for Department
 */
@Data
@Builder
public class DepartmentDto implements Serializable {
    private Integer id;

    @Size(min = 2, message = "name must be min 2 symbol")
    private String name;

    public Integer getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentDto that = (DepartmentDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
