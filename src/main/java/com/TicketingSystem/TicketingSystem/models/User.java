package com.TicketingSystem.TicketingSystem.models;

import com.TicketingSystem.TicketingSystem.dtos.enumerasi.E_Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "[User]")
public class User {
    @Id
    @Column(name = "UserID", nullable = false, length = 3)
    private String id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private Employee employee;

    @Column(name = "Username", nullable = false, length = 100)
    private String username;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Enabled", nullable = false)
    private boolean enabled;

    @Column(name = "Role", nullable = false, length = 100)
    private String role;

    public User(Employee employee,String username, String password, String role) {
        this.employee = employee;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = true;

    }
}
