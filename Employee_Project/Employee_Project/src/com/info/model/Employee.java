package com.info.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Employee {

	private int employeeId;
	private String employeeName;
	private LocalDate date;
	private LocalTime checkInTime;
	private LocalTime checkOutTime;
	private AttendanceStatus status;

	public Employee(int employeeId, String employeeName, LocalDate date, LocalTime checkInTime, LocalTime checkOutTime,
			AttendanceStatus status) {

		if (employeeId <= 0)
			throw new IllegalArgumentException("Employee ID must be positive");

		if (employeeName == null || employeeName.isBlank())
			throw new IllegalArgumentException("Employee name cannot be empty");

		if (date == null || date.isAfter(LocalDate.now()))
			throw new IllegalArgumentException("Date cannot be future");

		if (checkInTime == null || checkOutTime == null)
			throw new IllegalArgumentException("Check-in/out cannot be null");

		if (!checkInTime.isBefore(checkOutTime))
			throw new IllegalArgumentException("Check-out must be after check-in");

		if (status == null)
			throw new IllegalArgumentException("Invalid attendance status");

		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.date = date;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.status = status;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getCheckInTime() {
		return checkInTime;
	}

	public LocalTime getCheckOutTime() {
		return checkOutTime;
	}

	public AttendanceStatus getStatus() {
		return status;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public void setCheckInTime(LocalTime checkInTime) {
		this.checkInTime = checkInTime;
	}

	public void setCheckOutTime(LocalTime checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public void setStatus(AttendanceStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return employeeId + " | " + employeeName + " | " + date + " | " + checkInTime + "-" + checkOutTime + " | "
				+ status;
	}
}
