package com.info.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.info.model.Employee;

public class EmployeeRepo {

    private List<Employee> list = new ArrayList<>();

    public void save(Employee emp) {
        list.add(emp);
    }

    public List<Employee> findAll() {
        return list;
    }

    public Employee findByEmpIdAndDate(int empId, LocalDate date) {
        for (Employee a : list) {
            if (a.getEmployeeId() == empId && a.getDate().equals(date)) {
                return a;
            }
        }
        return null;
    }

    public List<Employee> findByEmployee(int empId) {
        List<Employee> result = new ArrayList<>();
        for (Employee a : list) {
            if (a.getEmployeeId() == empId) {
                result.add(a);
            }
        }
        return result;
    }

    public void delete(Employee emp) {
        list.remove(emp);
    }
}
