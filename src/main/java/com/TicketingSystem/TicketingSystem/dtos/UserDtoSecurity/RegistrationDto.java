package com.TicketingSystem.TicketingSystem.dtos.UserDtoSecurity;

import com.TicketingSystem.TicketingSystem.dtos.enumerasi.E_Role;
import com.TicketingSystem.TicketingSystem.models.Employee;
import com.TicketingSystem.TicketingSystem.models.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegistrationDto implements Serializable {
    private final String username;
    private final String password;
    private final String role;

    public User Convert(Employee userId){
        return new User(
                userId,
                username,
                password,
                role
        );
    }

}
