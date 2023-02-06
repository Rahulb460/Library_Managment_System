package com.example.LMSBackend.Converters;

import com.example.LMSBackend.Models.Student;
import com.example.LMSBackend.RequestDto.StudentRequestDto;
import com.example.LMSBackend.ResponseDto.StudentResponseDto;

public class StudentConvertor {
    public static Student convertDtoToEntity(StudentRequestDto studentRequest){
        Student student = Student.builder().
                name(studentRequest.getName())
                .age(studentRequest.getAge())
                .country(studentRequest.getCountry())
                .email(studentRequest.getEmail()).build();

        return student;
    }

    public static StudentResponseDto convertEntityToDTO(Student student){
        StudentResponseDto studentResponse = StudentResponseDto.builder()
                .name(student.getName())
                .age(student.getAge())
                .country(student.getCountry())
                .email(student.getEmail()).build();

        return studentResponse;
    }
}
