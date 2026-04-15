package com.springboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.Student;
import com.springboot.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	private StudentService service;
	public StudentController(StudentService service) {
		this.service = service;
	}
	
	
	@PostMapping("/")
	public Student addStudent(@RequestBody Student student) {
		return service.saveStudent(student);
	}
	
	
	@GetMapping("/")
	public List<Student> getAllStudent(){
		return service.getAllStudent();
	}
	
	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable Integer id) {
		service.deleteStudent(id);
		return "data deleted successfully";
	}
	
	
	
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        student.setId(id);
        return service.saveStudent(student);
    }
	
}
