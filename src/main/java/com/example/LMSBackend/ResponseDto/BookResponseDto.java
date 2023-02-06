package com.example.LMSBackend.ResponseDto;

import com.example.LMSBackend.Enums.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {

    String name;
    @Enumerated(value = EnumType.STRING)
    Genre genre;
    String author;
}
