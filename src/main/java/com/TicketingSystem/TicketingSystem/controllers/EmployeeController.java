package com.TicketingSystem.TicketingSystem.controllers;

import com.TicketingSystem.TicketingSystem.dtos.employee.EmployeeHeaderDto;
import com.TicketingSystem.TicketingSystem.dtos.employee.EmployeeInsertDto;
import com.TicketingSystem.TicketingSystem.dtos.employee.EmployeeUpdateDto;
import com.TicketingSystem.TicketingSystem.models.Employee;
import com.TicketingSystem.TicketingSystem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmployeeHeaderDto> findAllEmployee(){
        return service.findAllEmployee();
    }

    @GetMapping("session")
    public String asd(){
        return service.sessionUser();

    }

    @GetMapping("technical-support")
    public List<EmployeeHeaderDto> findEmployeeTechSupport(){
        return service.findEmployeeTechSupport();
    }

    @PutMapping("update-biodata")
    public EmployeeHeaderDto updateBiodata (@RequestBody EmployeeUpdateDto updateBio){
        return service.updateBiodata(updateBio);
    }

    @GetMapping("technical-support/not-work")
    public List<EmployeeHeaderDto> findEmployeeTechSupportNotWork(){
        return service.findEmployeeTechSupportNotWork();
    }

   @GetMapping("technical-support/in-work")
    public List<EmployeeHeaderDto> findEmployeeTechSupportInWork(){
        return service.findEmployeeTechSupportInWork();
    }

    @PostMapping("input-employee")
    public EmployeeHeaderDto InsertEmployee(@RequestBody EmployeeInsertDto employeeInsertDto) {
        return service.InsertEmployee(employeeInsertDto);
    }

    @PutMapping("update/{employeeId}")
    public EmployeeHeaderDto UpdateEmployeeById(@PathVariable String employeeId, @RequestBody EmployeeUpdateDto employee) {
        return service.updateEmployeeById(employeeId, employee);
    }

    @DeleteMapping("delete/{employeeId}")
    public Boolean DeleteEmployeeById(@PathVariable String employeeId){
        return service.DeleteEmployeeById(employeeId);
    }
}
