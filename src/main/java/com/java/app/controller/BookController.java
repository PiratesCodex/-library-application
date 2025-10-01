package com.java.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java.app.Service.BookService;
import com.java.app.dto.ApiResponse;
import com.java.app.dto.BookRequest;
import com.java.app.dto.BorrowRequest;
import com.java.app.dto.CategoryPopularityResponse;
import com.java.app.entity.Book;
import com.java.app.entity.LibraryEnums.BookStatus;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

 
    @GetMapping
    public ResponseEntity<ApiResponse<List<Book>>> getBooks(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BookStatus status,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String name) {

        return ResponseEntity.ok(new ApiResponse<>(
                "success",
                "Books fetched",
                bookService.getBooks(category, status, author, name)
        ));
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(new ApiResponse<>(
                "success",
                "Book fetched successfully",
                book
        ));
    }

 
    @PostMapping("/addBooks")
    public ResponseEntity<ApiResponse<Book>> addBook(@RequestBody BookRequest req) {
        Book book = new Book();
        book.setName(req.getName());
        book.setAuthor(req.getAuthor());
        book.setCategory(req.getCategory());
        book.setStatus(BookStatus.AVAILABLE);

        return ResponseEntity.ok(new ApiResponse<>(
                "success",
                "Book added successfully",
                bookService.addBook(book)
        ));
    }

 
    @PutMapping("/status")
    public ResponseEntity<ApiResponse<String>> updateStatus(
            @RequestParam Long bookId,
            @RequestParam BookStatus status) {

        return ResponseEntity.ok(new ApiResponse<>(
                "success",
                bookService.updateBookStatus(bookId, status),
                null
        ));
    }

    @PostMapping("/take")
    public ResponseEntity<ApiResponse<String>> borrowBook(@RequestBody BorrowRequest req) {
        return ResponseEntity.ok(new ApiResponse<>(
                "success",
                bookService.borrowBook(req.getUserId(), req.getBookId(), req.getDays()),
                null
        ));
    }

    @GetMapping("/category/popularity")
    public ResponseEntity<ApiResponse<List<CategoryPopularityResponse>>> getPopularity() {
        return ResponseEntity.ok(new ApiResponse<>(
                "success",
                "Popularity fetched",
                bookService.getCategoryPopularity()
        ));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(new ApiResponse<>(
                "success",
                "Book deleted successfully",
                null
        ));
    }
}
