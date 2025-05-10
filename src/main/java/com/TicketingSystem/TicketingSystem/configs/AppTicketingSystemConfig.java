package com.TicketingSystem.TicketingSystem.configs;

import com.TicketingSystem.TicketingSystem.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class AppTicketingSystemConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public AppTicketingSystemConfig(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth").permitAll()
                .antMatchers("/employee").permitAll()
                .antMatchers("/employee/session").authenticated()
                .antMatchers("/auth/register").permitAll()

                //ses menu employee
                .antMatchers("/employee/technical-support").hasAnyAuthority("ROLE_ADMIN","ROLE_TECHNICAL_SUPPORT","ROLE_MANAGER")
                .antMatchers("/employee/technical-support/in-work").hasAnyAuthority("ROLE_ADMIN","ROLE_MANAGER")
                .antMatchers("/employee/technical-support/not-work").hasAnyAuthority("ROLE_ADMIN","ROLE_MANAGER")
                .antMatchers("/employee/input-employee").hasAuthority("ROLE_ADMIN")
                .antMatchers("/employee/update/{employeeId}").hasAuthority("ROLE_ADMIN")
                .antMatchers("/employee/delete/{employeeId}").hasAuthority("ROLE_ADMIN")
                //Akses Menu Ticket
                .antMatchers("/ticket").permitAll()
                .antMatchers("/ticket/find-by-year/{year}").hasAnyAuthority("ROLE_ADMIN","ROLE_MANAGER")
                .antMatchers("/ticket/find-by-status/{status}").hasAnyAuthority("ROLE_ADMIN","ROLE_MANAGER")
                .antMatchers("/ticket/find-by-urgency").hasAnyAuthority("ROLE_ADMIN","ROLE_MANAGER")
                .antMatchers("/ticket/input").hasAuthority("ROLE_ADMIN")
                .antMatchers("/ticket/ticket-update").hasAuthority("ROLE_ADMIN")
                //Akses Menu History
                .antMatchers("/history/input-history").hasAuthority("ROLE_TECHNICAL_SUPPORT")
                .and()
                .httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        //.authenticated() -> harus terautentikasi
        //.hasRole() -> harus ada prefix ROLE
        //.permitAll() -> boleh semua
        //.hasAuthority() -> tidak perlu prefix ROLE
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

}
