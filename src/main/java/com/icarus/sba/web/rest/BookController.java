package com.icarus.sba.web.rest;

import com.icarus.sba.domain.Book;
import com.icarus.sba.enums.ErrorCode;
import com.icarus.sba.service.BookService;
import com.icarus.sba.service.dto.StandardBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<StandardBody<List<Book>>> listBook(
        @RequestParam(defaultValue = "") String title,
        Pageable pageable
    ) {
        Page<Book> books = bookService.findAll(title, pageable);
        StandardBody<List<Book>> body = StandardBody.success(books);
        return ResponseEntity.ok(body);
    }

}
