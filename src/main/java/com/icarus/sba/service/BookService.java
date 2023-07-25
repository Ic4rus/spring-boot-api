package com.icarus.sba.service;

import com.icarus.sba.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Page<Book> findAll(
        String title,
        Pageable pageable
    );

}
