package com.example.LMSBackend.Controller;

import com.example.LMSBackend.RequestDto.StudentRequestDto;
import com.example.LMSBackend.ResponseDto.StudentResponseDto;
import com.example.LMSBackend.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;


    // Add a student
    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@RequestBody()StudentRequestDto studentRequestDto) {
        String result = studentService.createStudent(studentRequestDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // Get student By Id
    @GetMapping("getById")
    public ResponseEntity<StudentResponseDto> getStudentById(@RequestParam("id") int id){
        StudentResponseDto student = studentService.getDetailsById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // Delete a student by ID
    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteStudent(@RequestParam("id") int id){
       String result = studentService.deleteStudent(id);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }
}
