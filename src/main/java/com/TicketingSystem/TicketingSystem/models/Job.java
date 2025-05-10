package com.TicketingSystem.TicketingSystem.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Job")
public class Job {

    @Id
    @Column(name = "JobID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Tittle")
    private String tittle;

    @OneToMany(mappedBy = "job")
    private List<Employee> Employee;

    public Job(Integer id, String tittle) {
        this.id = id;
        this.tittle = tittle;
    }

    public Job(Integer id) {
        this.id = id;
    }
}
