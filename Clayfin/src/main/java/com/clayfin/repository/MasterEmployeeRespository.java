package com.clayfin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clayfin.model.Employee;
@Repository
public interface MasterEmployeeRespository extends JpaRepository<Employee, Long>{

	List<Employee> findByStatus(String status);
	
}
