package com.TicketingSystem.TicketingSystem.dtos.employee;

import com.TicketingSystem.TicketingSystem.models.Employee;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeHeaderDto {

    private final String id;
    private final String fullName;
    private final LocalDate birthDate;
    private final String phone;
    private final String jobID;
    private final String tittle;

    public static EmployeeHeaderDto set(Employee employee){
        return new EmployeeHeaderDto(employee.getId(), employee.fetchFullName(), employee.getBirthdate(),
                employee.getPhone(),  employee.getJob() == null ? null : employee.getJob().getId().toString(),
                employee.getJob().getId() == 1 ? "Admin" :
                        employee.getJob().getId() == 2 ? "Technical Support" : "Manager");
    }

    public static List<EmployeeHeaderDto> toList(List<Employee> employees){
        List<EmployeeHeaderDto> result = new ArrayList<>();
        for (Employee emp : employees) {
            result.add(set(emp));
        }
        return result;
    }
}
