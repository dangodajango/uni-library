package uni.plovdiv.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 13, unique = true)
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
