package com.clayfin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clayfin.model.VaildEmployee;

@Repository
public interface EmployeeRepository extends JpaRepository<VaildEmployee, Long>{

}
