package com.TicketingSystem.TicketingSystem.services;

import com.TicketingSystem.TicketingSystem.models.MyUserDetails;
import com.TicketingSystem.TicketingSystem.models.User;
import com.TicketingSystem.TicketingSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServices implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public MyUserDetailsServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username tidak ditemukan"));

        return new MyUserDetails(user);
    }
}
