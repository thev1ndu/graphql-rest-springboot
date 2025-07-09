package dev.thev1ndu.bookq.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "title cannot be blank")
    @Size(min = 2, max = 100, message = "title must be between 2 and 100 characters")
    private String title;

    @NotBlank(message = "author name is required")
    @Size(min = 2, message = "Author name must be atleast 2 characters long")
    private String author;
}
