/*package com.gl.lms.controller;

import com.gl.lms.entity.Books;
import com.gl.lms.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BooksService booksService;

    // Only ADMIN can update books
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Integer id,
                                        @RequestBody Books book) {
        return ResponseEntity.ok(booksService.update(id, book));
    }

    // Only ADMIN can delete books
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
        booksService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
*/

package com.gl.lms.controller;

import com.gl.lms.entity.Books;
import com.gl.lms.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BooksService booksService;

    //  Only ADMIN can update books
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Integer id,
                                        @RequestBody Books book,
                                        Authentication authentication) {

        Books updatedBook = booksService.update(id, book, authentication.getName());

        return ResponseEntity.ok(updatedBook);
    }

    // Only ADMIN can delete books (Soft Delete)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id,
                                        Authentication authentication) {

        booksService.softDelete(id, authentication.getName());

        return ResponseEntity.ok("Book soft deleted successfully");
    }
}
