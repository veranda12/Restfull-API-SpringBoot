package com.TicketingSystem.TicketingSystem.dtos.enumerasi;

import com.TicketingSystem.TicketingSystem.models.Employee;

public enum E_Role {
    Admin("ADMIN"),
    Technical_support("TECH_SUPPORT"),
    Manager("MANAGER");

    private String userRole;

    E_Role(String userRole) {this.userRole = userRole;}

    public String getUserRole() {return userRole;}
}
