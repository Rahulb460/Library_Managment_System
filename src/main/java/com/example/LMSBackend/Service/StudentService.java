package com.example.LMSBackend.Service;

import com.example.LMSBackend.Converters.StudentConvertor;
import com.example.LMSBackend.Enums.CardStatus;
import com.example.LMSBackend.Models.Card;
import com.example.LMSBackend.Models.Student;
import com.example.LMSBackend.Repository.StudentRepository;
import com.example.LMSBackend.RequestDto.StudentRequestDto;
import com.example.LMSBackend.ResponseDto.StudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CardService cardService;

    public String createStudent(StudentRequestDto studentRequestDto) {
        // convert the dto to entity

        Student student = StudentConvertor.convertDtoToEntity(studentRequestDto);

        Card newCard = new Card();
        newCard.setCardStatus(CardStatus.ACTIVATED);
        newCard.setStudent(student);  // for that new foreign key column

        // for that bidirectional relation
        student.setCard(newCard);

        studentRepository.save(student);
        // cardRepository.save() will automatically be taken of
        return "Successfully added student and card";
    }

    public StudentResponseDto getDetailsById(int id) {
        Student student = studentRepository.findById(id).get();

        StudentResponseDto studentResponse = StudentConvertor.convertEntityToDTO(student);
        return studentResponse;
    }

    public String deleteStudent(int id) {
        studentRepository.deleteById(id);
        return "Student removed successfully";
    }
}
