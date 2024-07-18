package com.example.springdailyquiz;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String title;
    private String author;
    private String isbn;
    private double price;
    private int publishedYear;
}
