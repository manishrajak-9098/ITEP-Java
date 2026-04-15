package com.info.service;

import java.time.LocalDate;
import java.util.List;

import com.info.exception.DuplicateAttendanceException;
import com.info.model.Employee;
import com.info.repo.EmployeeRepo;

public class EmployeeService {

	private EmployeeRepo repo = new EmployeeRepo();

	public void markAttendance(Employee emp) {

		Employee existing = repo.findByEmpIdAndDate(emp.getEmployeeId(), emp.getDate());

		if (existing != null) {
			throw new DuplicateAttendanceException("Attendance already marked for this employee and date");
		}

		repo.save(emp);
	}

	public List<Employee> getAllAttendance() {
		return repo.findAll();
	}

	public List<Employee> getAttendanceByEmployee(int empId) {
		return repo.findByEmployee(empId);
	}

	public void updateAttendance(int empId, LocalDate date, Employee updated) {

		Employee existing = repo.findByEmpIdAndDate(empId, date);

		if (existing == null) {
			throw new IllegalArgumentException("Attendance not found");
		}

		existing.setEmployeeName(updated.getEmployeeName());
		existing.setCheckInTime(updated.getCheckInTime());
		existing.setCheckOutTime(updated.getCheckOutTime());
		existing.setStatus(updated.getStatus());
	}

	public void deleteAttendance(int empId, LocalDate date) {

		Employee existing = repo.findByEmpIdAndDate(empId, date);

		if (existing == null) {
			throw new IllegalArgumentException("Attendance not found");
		}

		repo.delete(existing);
	}
}
