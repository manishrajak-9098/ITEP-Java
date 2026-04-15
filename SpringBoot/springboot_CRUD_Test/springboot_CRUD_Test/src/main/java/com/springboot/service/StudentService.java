package com.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.entity.Student;
import com.springboot.repository.StudentRepository;

@Service
public class StudentService {

 private StudentRepository studentRepository;
 
	StudentService(StudentRepository studentRepository){
		this.studentRepository = studentRepository;
				
	}
	
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}
	
	
	public List<Student> getAllStudent(){
		return studentRepository.findAll();
		
	}
	
	
	public void deleteStudent(Integer id) {
	 studentRepository.deleteById(id);
	}
	
	
	public Student updateStudent(Integer id, Student studentDetails) {
	    Student existingStudent = studentRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Student not found with id " + id));

	    existingStudent.setName(studentDetails.getName());
	    existingStudent.setEmail(studentDetails.getEmail());
	 
	    return studentRepository.save(existingStudent);
	}
}

