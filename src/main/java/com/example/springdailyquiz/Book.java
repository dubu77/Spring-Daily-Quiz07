package com.example.springdailyquiz;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Book {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private double price;
    private int publishedYear;
}