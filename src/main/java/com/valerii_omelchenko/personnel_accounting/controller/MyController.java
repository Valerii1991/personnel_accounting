package com.valerii_omelchenko.personnel_accounting.controller;

import com.valerii_omelchenko.personnel_accounting.factory.SelectionFactory;
import com.valerii_omelchenko.personnel_accounting.convertor.DepartmentConvertor;
import com.valerii_omelchenko.personnel_accounting.convertor.EmployeeConvertor;
import com.valerii_omelchenko.personnel_accounting.dto.DepartmentDto;
import com.valerii_omelchenko.personnel_accounting.dto.EmployeeDto;
import com.valerii_omelchenko.personnel_accounting.dto.ReportsDto;
import com.valerii_omelchenko.personnel_accounting.entity.Department;
import com.valerii_omelchenko.personnel_accounting.entity.Employee;
import com.valerii_omelchenko.personnel_accounting.service.DepartmentService;
import com.valerii_omelchenko.personnel_accounting.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 Controller
 */
@Controller
@AllArgsConstructor
public class MyController {
    private DepartmentService departmentService;

    private EmployeeService employeeService;

    private EmployeeConvertor employeeConvertor;

    private DepartmentConvertor departmentConvertor;

    @GetMapping("/")
    public String mainPage() {
        return "index.html";
    }

    @RequestMapping("/departments")
    public String showAllDepartments(Model model) {
        List<DepartmentDto> allDepartments = departmentService.getAllDepartments().stream().map(departmentConvertor::departmentToDepartmentDto).toList();
        model.addAttribute("allDepartments",allDepartments);
        return "department/departments.html";
    }

    @GetMapping("/addNewDepartment")
    public String addNewDepartment( Model model){
        DepartmentDto departmentDto = DepartmentDto.builder().build();
        model.addAttribute("department",departmentDto);
        return "department/newDepartment.html";
    }

    @PostMapping("/saveDepartment")
    public String saveDepartment(@ModelAttribute("department") @Valid DepartmentDto departmentDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "department/newDepartment.html";
        }
        else{
            Department department = departmentConvertor.departmentDtoToDepartment(departmentDto);
            departmentService.saveDepartment(department);
            return "redirect:/departments";
        }
    }

    @GetMapping ("/department/{id}/edit")
    public String editDepartment(@PathVariable("id") int id, Model model){
        Optional<Department> optionalDepartment = departmentService.getDepartmentById(id);
        if(optionalDepartment.isPresent()) {
            DepartmentDto departmentDto = departmentConvertor.departmentToDepartmentDto(optionalDepartment.get());
            model.addAttribute("department", departmentDto);
            return "department/editDepartment.html";
        }
        else {
                model.addAttribute("message","Can't edit department, because the department with id " + id + " was not found ");
                return "error.html";
        }
    }

    @PostMapping("updateDepartment")
    public String updateDepartment(@ModelAttribute("department")  @Valid DepartmentDto departmentDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "department/editDepartment.html";
        }
        else {
            Department department = departmentConvertor.departmentDtoToDepartment(departmentDto);
            departmentService.saveDepartment(department);
            return "redirect:/departments";
        }
    }

    @RequestMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable("id") int id, Model model){
        Optional<Department> optionalDepartment = departmentService.getDepartmentById(id);
        if(optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            List<Employee> employeeListByDepartment = employeeService.getAllByDepartment(department);
            if (employeeListByDepartment.isEmpty()){
                    departmentService.deleteDepartmentById(id);
                    return "redirect:/departments";
            }
            else {
                model.addAttribute("message","Can't delete department: " + department.getName()
                        + " because there are employees in this department");
                return "error.html";
            }
        }
        else {
            model.addAttribute("message","Can't delete department, because the department with id " + id + " was not found ");
            return "error.html";
        }
    }

    @RequestMapping("/employees")
    public String showAllEmployees(Model model) {
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees().stream().map(employeeConvertor::employeeToEmployeeDto).toList();
        model.addAttribute("allEmployees",allEmployees);
        return "employee/employees.html";
    }

    @GetMapping("/addNewEmployee")
    public String addNewEmployee(Model model){
        EmployeeDto employeeDto = EmployeeDto.builder().build();

        Map<Integer, String> mapDepartments = departmentService.getMapDepartments();
        if(mapDepartments.isEmpty()){
            model.addAttribute("message","There are no departments in the application. You must enter at least one department");
            return "error.html";
        }
        else {
            employeeDto.setMapDepartments(mapDepartments);
            model.addAttribute("employee", employeeDto);
            model.addAttribute("keySexType", "1");

            return "employee/newEmployee.html";
        }
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee (@ModelAttribute("employee") @Valid EmployeeDto employeeDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            Map<Integer, String> mapDepartments = departmentService.getMapDepartments();
            employeeDto.setMapDepartments(mapDepartments);
            return "employee/newEmployee.html";
        }

        else {
            return saveEmployeeToDatabase(employeeDto, model);
        }
    }
    /**
     Save Employee to database
     params employeeDto, model
     If the check by date of employment is passed, then we save  Employee to database, otherwise we return the page with an error
     */
    private String saveEmployeeToDatabase(@ModelAttribute("employee") @Valid EmployeeDto employeeDto, Model model) {
        Employee employee = employeeConvertor.employeeDtoToEmployee(employeeDto);
        if ( employee.getDateOfDismissal() != null && employee.getEmploymentDate().isAfter(employee.getDateOfDismissal())){
            model.addAttribute("message","Employment date should not be less date Of dismissal");
            return "error.html";
        }
        else {
            employeeService.saveEmployee(employee);
            return "redirect:/employees";
        }
    }

    @GetMapping ("/employee/{id}/edit")
    public String editEmployee(@PathVariable("id") int id, Model model){
        Optional<Employee> optionalEmployee = employeeService.getEmployeeById(id);
        if(optionalEmployee.isPresent()) {
            EmployeeDto employeeDto = employeeConvertor.employeeToEmployeeDto(optionalEmployee.get());
            Map<Integer, String> mapDepartments = departmentService.getMapDepartments();
            employeeDto.setMapDepartments(mapDepartments);
            model.addAttribute("employee", employeeDto);
            return "employee/editEmployee.html";
        }
        else {
                model.addAttribute("message","Can't edit employee, because the employee with id " + id + " was not found ");
                return "error.html";
        }
    }

    @PostMapping("updateEmployee")
    public String updateEmployee(@ModelAttribute("employee") @Valid EmployeeDto employeeDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            Map<Integer, String> mapDepartments = departmentService.getMapDepartments();
            employeeDto.setMapDepartments(mapDepartments);
            return "employee/editEmployee.html";
        }
        else {
            return saveEmployeeToDatabase(employeeDto, model);
        }
    }

    @RequestMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") int id, Model model){
        Optional<Employee> optionalEmployee = employeeService.getEmployeeById(id);
        if(optionalEmployee.isPresent()) {
            employeeService.deleteEmployeeById(id);
            return "redirect:/employees";
        }
        else{
            model.addAttribute("message","Can't delete employee, because the employee with id " + id + " was not found ");
            return "error.html";
        }
    }

    @RequestMapping("/reports")
    public String showReports(Model model) {
        Map<Integer, String> mapDepartments = departmentService.getMapDepartments();
        ReportsDto reportsDto = ReportsDto.builder().mapDepartments(mapDepartments).build();
        model.addAttribute("reports",reportsDto);
        return "report/reports.html";
    }

    @PostMapping("/showResult")
    public String showResult (@ModelAttribute("reports") ReportsDto reportsDto, Model model){
        SelectionFactory selectionFactory = new SelectionFactory();
        List<Employee> allEmployees = employeeService.getAllByReportsDto(reportsDto, selectionFactory);

        List<EmployeeDto> allEmployeesDto = allEmployees.stream().map(employeeConvertor::employeeToEmployeeDto).toList();

        model.addAttribute("allEmployees",allEmployeesDto);
        model.addAttribute("message",selectionFactory.getMessage());

        return "report/report_result.html";
    }
}
