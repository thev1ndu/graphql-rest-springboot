package dev.thev1ndu.bookq.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import dev.thev1ndu.bookq.model.Book;
import dev.thev1ndu.bookq.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMutationResolver implements GraphQLMutationResolver {
    private final BookRepository bookRepository;

//    POST
    public Book addBook(String title, String author) {
        try {
            return bookRepository.save(new Book(title, author));
        } catch (Exception e) {
            System.out.println("Error saving book: " + e.getMessage());
            return null;
        }
    }

    //    PUT
    public Book updateBook(Long id, String title, String author) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(title);
            book.setAuthor(author);
            return bookRepository.save(book);
        }).orElse(null);
    }

//    DEL
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
