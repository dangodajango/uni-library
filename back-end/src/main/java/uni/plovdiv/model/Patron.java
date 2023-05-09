package uni.plovdiv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patron {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "display_name", nullable = false, length = 40)
    private String displayName;

    @Column(nullable = false, unique = true, length = 10)
    private String ucn;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false, length = 30)
    private String password;

    @OneToMany(mappedBy = "borrowedBy")
    private Set<Book> borrowedBooks;
}
