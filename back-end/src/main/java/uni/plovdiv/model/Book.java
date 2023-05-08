package uni.plovdiv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 13, unique = true)
    private String isbn;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private Double price;

    /**
     * The id of the user borrowed the book.
     */
    @Column(name = "borrowed_by")
    private Long borrowedBy;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;
}
