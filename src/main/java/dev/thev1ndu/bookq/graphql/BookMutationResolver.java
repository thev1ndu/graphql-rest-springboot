package dev.thev1ndu.bookq.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import dev.thev1ndu.bookq.exception.BookNotFoundException;
import dev.thev1ndu.bookq.model.Book;
import dev.thev1ndu.bookq.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMutationResolver implements GraphQLMutationResolver {
    private static final Logger log = LoggerFactory.getLogger(BookMutationResolver.class);
    private final BookRepository bookRepository;

//    POST
    public Book addBook(String title, String author) {
        try {
            return bookRepository.save(new Book(title, author));
        } catch (Exception e) {
            log.error("Error saving book", e);
            throw e;
        }
    }

    //    PUT
    public Book updateBook(Long id, String title, String author) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(title);
                    book.setAuthor(author);
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new BookNotFoundException(id));
    }

//    DEL
    public boolean deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
        return true;
    }
}
