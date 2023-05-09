package uni.plovdiv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "borrowed_by", referencedColumnName = "id", nullable = false)
    private Patron borrowedBy;

    @ManyToMany()
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;
}
