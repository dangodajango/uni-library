package uni.plovdiv.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Builder
public class Book {

    @Id
    private Long id;

    @Column(nullable = false, length = 13)
    private String isbn;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private Double price;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

    /**
     * The id of the user borrowed the book.
     */
    @Column(name = "borrowed_by")
    private Long borrowedBy;
}
