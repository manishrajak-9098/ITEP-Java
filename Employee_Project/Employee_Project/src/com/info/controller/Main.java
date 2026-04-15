package com.info.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import com.info.exception.DuplicateAttendanceException;
import com.info.model.AttendanceStatus;
import com.info.model.Employee;
import com.info.service.EmployeeService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        while (true) {
            System.out.println("\n===== Employee Attendance Management =====");
            System.out.println("1. Mark Attendance");
            System.out.println("2. View All Records");
            System.out.println("3. View Attendance by Employee ID");
            System.out.println("4. Update Attendance");
            System.out.println("5. Delete Attendance");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            try {
                switch (choice) {

                    case 1:
                  
                        System.out.print("Enter Employee ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Employee Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Date (YYYY-MM-DD): ");
                        LocalDate date = LocalDate.parse(sc.nextLine());

                        System.out.print("Enter Check-in Time (HH:MM): ");
                        LocalTime inTime = LocalTime.parse(sc.nextLine());

                        System.out.print("Enter Check-out Time (HH:MM): ");
                        LocalTime outTime = LocalTime.parse(sc.nextLine());

                        System.out.print("Enter Status (PRESENT/ABSENT/HALF_DAY): ");
                        AttendanceStatus status =
                                AttendanceStatus.valueOf(sc.nextLine().toUpperCase());

                        Employee emp = new Employee(id, name, date, inTime, outTime, status);

                        service.markAttendance(emp);
                        System.out.println("Attendance marked successfully");
                        break;

                    case 2:
                       
                        List<Employee> all = service.getAllAttendance();
                        all.forEach(System.out::println);
                        break;

                    case 3:
                     
                        System.out.print("Enter Employee ID: ");
                        int empId = sc.nextInt();

                        List<Employee> records = service.getAttendanceByEmployee(empId);
                        records.forEach(System.out::println);
                        break;

                    case 4:
                       
                        System.out.print("Enter Employee ID: ");
                        int uid = sc.nextInt();
                        sc.nextLine(); 

                        System.out.print("Enter Date (YYYY-MM-DD): ");
                        LocalDate udate = LocalDate.parse(sc.nextLine());

                        System.out.print("Enter Employee Name: ");
                        String uname = sc.nextLine();

                        System.out.print("Enter New Check-in Time (HH:MM): ");
                        LocalTime newIn = LocalTime.parse(sc.nextLine());

                        System.out.print("Enter New Check-out Time (HH:MM): ");
                        LocalTime newOut = LocalTime.parse(sc.nextLine());

                        System.out.print("Enter New Status: ");
                        AttendanceStatus newStatus = AttendanceStatus.valueOf(sc.nextLine().toUpperCase());

                      
                        Employee updatedEmp =
                                new Employee(uid, uname, udate, newIn, newOut, newStatus);

                        service.updateAttendance(uid, udate, updatedEmp);
                        System.out.println(" Attendance updated");
                        break;

                    case 5:
                     
                        System.out.print("Enter Employee ID: ");
                        int did = sc.nextInt();

                        System.out.print("Enter Date (YYYY-MM-DD): ");
                        LocalDate ddate = LocalDate.parse(sc.nextLine());

                        service.deleteAttendance(did, ddate);
                        System.out.println(" Attendance deleted");
                        break;

                    case 6:
                        System.out.println(" Exiting...");
                        return;

                    default:
                        System.out.println(" Invalid choice");
                }

            } catch (DuplicateAttendanceException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(" Validation Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(" Unexpected Error: " + e.getMessage());
            }
        }
    }
}
