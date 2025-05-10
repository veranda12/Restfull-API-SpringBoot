package com.TicketingSystem.TicketingSystem.dtos.employee;

import com.TicketingSystem.TicketingSystem.models.Employee;
import com.TicketingSystem.TicketingSystem.models.Job;
import lombok.Data;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeInsertDto {
    private final String id;
    private final String firstname;
    private final String lastname;
    private final String birthdate;
    private final String phone;
    private final Integer jobID;


    public Employee Convert(){
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Employee employee;


            if (id.isEmpty() || id.isBlank()){
                throw new EntityNotFoundException("id tidak boleh kosong");
            }
            if (id.substring(0, 1).equals("A")) {
                employee = new Employee(id, firstname, lastname, LocalDate.parse(birthdate, formatDate), phone, 1);
            } else if (id.substring(0, 1).equals("T")) {
                 employee = new Employee(id, firstname, lastname, LocalDate.parse(birthdate, formatDate), phone, 2);
            } else {
                 employee = new Employee(id, firstname, lastname, LocalDate.parse(birthdate, formatDate), phone, 3);
            }




            return employee;
    }
}
