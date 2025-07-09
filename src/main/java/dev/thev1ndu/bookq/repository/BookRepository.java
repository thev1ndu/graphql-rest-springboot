package dev.thev1ndu.bookq.repository;

import dev.thev1ndu.bookq.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}
