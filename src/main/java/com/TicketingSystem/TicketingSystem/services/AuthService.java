package com.TicketingSystem.TicketingSystem.services;

import com.TicketingSystem.TicketingSystem.dtos.UserDtoSecurity.RegistrationDto;
import com.TicketingSystem.TicketingSystem.models.Employee;
import com.TicketingSystem.TicketingSystem.models.User;
import com.TicketingSystem.TicketingSystem.repositories.EmployeeRepository;
import com.TicketingSystem.TicketingSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AuthService {
    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(EmployeeRepository employeeRepository,UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registration(String userId,RegistrationDto registrant){
        Employee employee = employeeRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("tidak dapat mendaftar"));

        Employee idEmp = employeeRepository.getById(employee.getId());
        User user = registrant.Convert(idEmp);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        user.getPassword();
        return true;
    }
}
