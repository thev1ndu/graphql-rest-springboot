package dev.thev1ndu.bookq.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import dev.thev1ndu.bookq.model.Book;
import dev.thev1ndu.bookq.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookQueryResolver implements GraphQLQueryResolver {
    private BookRepository bookRepository;

    public List<Book> books() {
        return bookRepository.findAll();
    }

    public Book book(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
