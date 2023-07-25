package com.icarus.sba.service.impl;

import com.icarus.sba.domain.Book;
import com.icarus.sba.repository.BookRepository;
import com.icarus.sba.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private static Specification<Book> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("title"), title);
    }

    @Override
    public Page<Book> findAll(
        String title,
        Pageable pageable
    ) {
        Specification<Book> spec = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        if (title != null && title.length() > 0) {
            spec.and(hasTitle(title));
        }
        return bookRepository.findAll(spec, pageable);
    }
}
