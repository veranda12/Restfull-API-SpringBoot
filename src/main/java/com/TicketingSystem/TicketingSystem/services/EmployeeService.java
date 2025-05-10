package com.TicketingSystem.TicketingSystem.services;

import com.TicketingSystem.TicketingSystem.dtos.employee.EmployeeHeaderDto;
import com.TicketingSystem.TicketingSystem.dtos.employee.EmployeeInsertDto;
import com.TicketingSystem.TicketingSystem.dtos.employee.EmployeeUpdateDto;
import com.TicketingSystem.TicketingSystem.models.Employee;
import com.TicketingSystem.TicketingSystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeHeaderDto> findAllEmployee(){
        return EmployeeHeaderDto.toList(employeeRepository.FindAllCandidate());
    }

    public EmployeeHeaderDto InsertEmployee(EmployeeInsertDto employeeInsertDto){
        Employee employee = employeeInsertDto.Convert();
        employeeRepository.save(employee);
        return EmployeeHeaderDto.set(employee);
    }

    public EmployeeHeaderDto updateEmployeeById(String employeeId, EmployeeUpdateDto employee){
        Employee oldEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Karyawan tidak ditemukan"));

        oldEmployee.setFirstname(employee.getFirstname());
        oldEmployee.setLastname(employee.getLastname());
        oldEmployee.setPhone(employee.getPhone());

        employeeRepository.save(oldEmployee);
        return EmployeeHeaderDto.set(oldEmployee);
    }

    public boolean DeleteEmployeeById(String employeeId){
        employeeRepository.deleteById(employeeId);
        return true;
    }


    public List<EmployeeHeaderDto> findEmployeeTechSupport(){
        return EmployeeHeaderDto.toList(employeeRepository.FindEmployeTechSupport());
    }

    public List<EmployeeHeaderDto> findEmployeeTechSupportNotWork(){
        return EmployeeHeaderDto.toList(employeeRepository.FindEmployeNotWork());
    }

    public List<EmployeeHeaderDto> findEmployeeTechSupportInWork(){
        return EmployeeHeaderDto.toList(employeeRepository.FindEmployeInWork());
    }

    public String sessionUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return employeeRepository.findEmployeeIdBySession(auth.getName());
    }

    public EmployeeHeaderDto updateBiodata(EmployeeUpdateDto updateBio){
        Employee oldEmployee = employeeRepository.getById(sessionUser());

        oldEmployee.setFirstname(updateBio.getFirstname());
        oldEmployee.setLastname(updateBio.getLastname());
        oldEmployee.setPhone(updateBio.getPhone());
        employeeRepository.save(oldEmployee);
        return EmployeeHeaderDto.set(oldEmployee);
    }
}
