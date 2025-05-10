package com.TicketingSystem.TicketingSystem.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Employee")
@Entity
public class Employee {

    @Id
    @Column(name = "EmployeeID")
    private String id;

    @Column(name = "FirstName")
    private String firstname;

    @Column(name = "LastName")
    private String lastname;

    @Column(name = "BirthDate")
    private LocalDate birthdate;

    @Column(name = "Phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "JobID")
    private Job job;

    @OneToMany(mappedBy = "appointedTo")
    private Set<Ticket> ticketsAppointed = new LinkedHashSet<>();

    @OneToMany(mappedBy = "createdBy")
    private Set<Ticket> ticketsCreated = new LinkedHashSet<>();


    public Employee(String id, String firstname, String lastname, LocalDate birthdate, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.phone = phone;
    }

    public Employee(String id, String firstname, String lastname, LocalDate birthdate, String phone, Integer job) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.phone = phone;
        this.job = new Job(job);
    }

    public Employee(String id) {
        this.id = id;
    }

    public Employee(Integer job) {
        this.job = new Job(job);
    }

    public String fetchFullName(){
        return String.format("%s %s", this.firstname, this.lastname);
    }


}
