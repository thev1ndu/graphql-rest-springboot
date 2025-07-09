package dev.thev1ndu.bookq.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import dev.thev1ndu.bookq.model.Book;
import dev.thev1ndu.bookq.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookQueryResolver implements GraphQLQueryResolver {
    private static final Logger log = LoggerFactory.getLogger(BookQueryResolver.class);
    private final BookRepository bookRepository;

    public List<Book> books() {
        return bookRepository.findAll();
    }

    public Book book(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
