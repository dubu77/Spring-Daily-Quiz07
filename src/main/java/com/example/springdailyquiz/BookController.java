package com.example.springdailyquiz;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private List<Book> books = new ArrayList<>();
    private long nextId = 1;

    // 모든 도서 조회
    @GetMapping
    public List<BookDTO> getAllBooks() {
        // 엔티티 리스트를 DTO 리스트로 변환하여 반환
        return books.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 특정 도서 조회
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable long id) {
        Book book = findBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDTO(book));
    }

    // 새 도서 등록

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        book.setId(nextId++);
        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(book));
    }

    // 도서 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Book book = findBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        updateBookFields(book, bookDTO);
        return ResponseEntity.ok(convertToDTO(book));
    }

    // 도서 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (books.removeIf(book -> book.getId().equals(id))) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ID로 도서 찾기 (내부 사용 메서드)
    private Book findBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // 엔티티를 DTO로 변환
    private BookDTO convertToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setPrice(book.getPrice());
        dto.setPublishedYear(book.getPublishedYear());
        return dto;
    }

    // DTO를 엔티티로 변환
    private Book convertToEntity(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPrice(dto.getPrice());
        book.setPublishedYear(dto.getPublishedYear());
        return book;
    }

    // 도서 정보 업데이트
    private void updateBookFields(Book book, BookDTO dto) {
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPrice(dto.getPrice());
        book.setPublishedYear(dto.getPublishedYear());
    }
}
