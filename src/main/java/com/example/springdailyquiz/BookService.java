package com.example.springdailyquiz;


import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    // 도서 정보를 저장할 리스트
    private List<Book> books = new ArrayList<>();
    private long nextId = 1;

    // 모든 도서 조회
    public List<BookDTO> getAllBooks() {
        return books.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 특정 도서 조회
    public BookDTO getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .map(this::convertToDTO)
                .orElse(null);
    }

    // 새 도서 등록
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        book.setId(nextId++);
        books.add(book);
        return convertToDTO(book);
    }

    // 도서 정보 수정
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                // 기존 책 정보 업데이트
                book.setTitle(bookDTO.getTitle());
                book.setAuthor(bookDTO.getAuthor());
                book.setIsbn(bookDTO.getIsbn());
                book.setPrice(bookDTO.getPrice());
                book.setPublishedYear(bookDTO.getPublishedYear());
                return convertToDTO(book);
            }
        }
        return null; // 책을 찾지 못한 경우
    }

    // 도서 삭제
    public boolean deleteBook(Long id) {
        return books.removeIf(book -> book.getId().equals(id));
    }

    // Book 엔티티를 BookDTO로 변환
    private BookDTO convertToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setPrice(book.getPrice());
        dto.setPublishedYear(book.getPublishedYear());
        return dto;
    }

    // BookDTO를 Book 엔티티로 변환
    private Book convertToEntity(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPrice(dto.getPrice());
        book.setPublishedYear(dto.getPublishedYear());
        return book;
    }
}
