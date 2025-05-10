package com.TicketingSystem.TicketingSystem.repositories;

import com.TicketingSystem.TicketingSystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {


    @Query(value = """
            select * from Employee
            order by JobID asc
            """, nativeQuery = true)
    List<Employee> FindAllCandidate();

    @Query(value = """
            select e.EmployeeID from Employee as e
            join [User] as u on e.EmployeeID = u.UserID
            where u.Username = :username
            """, nativeQuery = true)
    String findEmployeeIdBySession(@Param("username") String username);

    @Query(value = """
            select * from Employee where JobID = 2
            """, nativeQuery = true)
    List<Employee> FindEmployeTechSupport();


    @Query(value = """
            select Distinct emp.EmployeeID,emp.FirstName, emp.LastName, emp.BirthDate,emp.Phone,emp.JobID,\s
            iif(t.AppointedTo is null,'tidak bekerja', 'bekerja')   from Ticket as t
            right join Employee as emp on t.AppointedTo = emp.EmployeeID
            where emp.JobID = 2 and t.AppointedTo is null
            """, nativeQuery = true)
    List<Employee> FindEmployeNotWork();


   @Query(value = """
            select Distinct emp.EmployeeID,emp.FirstName, emp.LastName, emp.BirthDate,emp.Phone,emp.JobID,\s
            iif(t.AppointedTo is null,'tidak bekerja', 'bekerja')   from Ticket as t
            right join Employee as emp on t.AppointedTo = emp.EmployeeID
            where emp.JobID = 2 and t.AppointedTo is not null
            """, nativeQuery = true)
    List<Employee> FindEmployeInWork();



}
