package edu.eci.arsw.digitalqueue.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.digitalqueue.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {





}