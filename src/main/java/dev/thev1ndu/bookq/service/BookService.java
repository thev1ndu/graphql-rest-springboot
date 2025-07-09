package dev.thev1ndu.bookq.service;

import dev.thev1ndu.bookq.exception.BookNotFoundException;
import dev.thev1ndu.bookq.model.Book;
import dev.thev1ndu.bookq.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        if (updatedBook == null) {
            log.warn("Attempted to update a book with null payload");
            throw new IllegalArgumentException("Book body must not be null");
        }

        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(updatedBook.getTitle());
                    existingBook.setAuthor(updatedBook.getAuthor());
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }
}
