package com.clayfin.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.clayfin.model.VaildEmployee;
import com.clayfin.model.Employee;
import com.clayfin.repository.EmployeeRepository;
import com.clayfin.repository.MasterEmployeeRespository;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class CsvDataTransferService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private MasterEmployeeRespository masterEmployeeRespository;

	@Autowired
	private Validator validator;

	private final int THREAD_POOL_SIZE = 5; // Number of threads in the thread pool

	private ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

	public void transferCsvDataToDatabase() {
		for (int i = 1; i <= 10; i++) {
			String csvFileName = "C:\\Users\\Soham\\Desktop\\Clyfin\\output\\Employee_" + i + ".csv";
			executorService.submit(() -> {
				List<VaildEmployee> employees;
				try {
					employees = parseCsvData(csvFileName);
					//employeeRepository.saveAll(employees);
				} catch (IllegalStateException | FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		executorService.shutdown();
		try {
			executorService.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public List<VaildEmployee> parseCsvData(String csvFileName) throws IllegalStateException, FileNotFoundException {
		List<Employee> employees = new ArrayList<>();
		List<VaildEmployee> allEmployees = new CsvToBeanBuilder(new FileReader(csvFileName))
				.withType(VaildEmployee.class)
				.build()
				.parse();
		
		// Validate the Employee object using Spring's validation framework
		for (VaildEmployee employee : allEmployees) {
			BindingResult result = new BeanPropertyBindingResult(employee, "Employee");
			ValidationUtils.invokeValidator(validator, employee, result);

			if (result.hasErrors()) {
				// Handle validation errors (e.g., log, skip, or reject invalid records)
				// System.out.println("Validation errors for record: " + Arrays.toString(row));
				employee.setStatus("F");
				Employee tempEmployee = new Employee();
				tempEmployee.setName(employee.getName());
				tempEmployee.setEmail(employee.getEmail());
				tempEmployee.setMobileNumber(employee.getMobileNumber());
				tempEmployee.setPanNumber(employee.getPanNumber());
				tempEmployee.setStatus(employee.getStatus());

				employees.add(tempEmployee);
				
			} else {
				employee.setStatus("T");

				Employee masterEmployee = new Employee();
				masterEmployee.setName(employee.getName());
				masterEmployee.setEmail(employee.getEmail());
				masterEmployee.setMobileNumber(employee.getMobileNumber());
				masterEmployee.setPanNumber(employee.getPanNumber());
				masterEmployee.setStatus(employee.getStatus());

				employees.add(masterEmployee);

			}
		}
		
		masterEmployeeRespository.saveAll(employees);
		return allEmployees;
	}

	public void dataTranferToMaster () {
		List<Employee> vaildEmployees = masterEmployeeRespository.findByStatus("T");
		List<VaildEmployee> employees = new ArrayList<>();
		for(Employee me : vaildEmployees) {
			VaildEmployee employee = new VaildEmployee();
			employee.setName(me.getName());
			employee.setEmail(me.getEmail());
			employee.setMobileNumber(me.getMobileNumber());
			employee.setPanNumber(me.getPanNumber());
			employee.setStatus(me.getStatus());
			employees.add(employee);
		}
		employeeRepository.saveAll(employees);
	}
}
