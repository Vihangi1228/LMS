package com.gl.lms.service;

import com.gl.lms.entity.Books;
import com.gl.lms.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private AuditLoggingService auditLoggingService;

    //  Secure Update with ID Manipulation Protection
    public Books update(Integer id, Books incomingBook, String username) {

        Books existingBook = booksRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Book not found with id: " + id));

        // Prevent updating deleted records
        if (existingBook.isDeleted()) {
            throw new RuntimeException("Cannot update a deleted book.");
        }

        //  ID Manipulation Protection
        // Never trust incomingBook.getId()
        existingBook.setTitle(incomingBook.getTitle());

        //  Track who modified
        existingBook.setModifiedBy(username);

        Books savedBook = booksRepository.save(existingBook);

        //  Audit Logging (IMPORTANT)
        auditLoggingService.log(
                username,
                "UPDATE_BOOK",
                id,
                "BOOK"
        );

        return savedBook;
    }

    //  Soft Delete Instead of Hard Delete
    public void softDelete(Integer id, String username) {

        Books book = booksRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Book not found with id: " + id));

        //  Prevent double deletion
        if (book.isDeleted()) {
            throw new RuntimeException("Book already deleted.");
        }

        // Soft delete
        book.setDeleted(true);

        // Track who deleted
        book.setModifiedBy(username);

        booksRepository.save(book);

        //  Audit Logging
        auditLoggingService.log(
                username,
                "DELETE_BOOK",
                id,
                "BOOK"
        );
    }
}
