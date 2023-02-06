package com.example.LMSBackend.RequestDto;

import com.example.LMSBackend.Enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {
    private String name;
    private Genre genre;
    private int authorId;
}
