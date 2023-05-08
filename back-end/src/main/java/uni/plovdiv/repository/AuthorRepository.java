package uni.plovdiv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uni.plovdiv.model.Author;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    /**
     * The author table has a unique constraint which consists of the
     * first name, last name and the birthdate of an author.
     */
    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM AUTHOR " +
                    "WHERE first_name = :firstName " +
                    "AND last_name = :lastName " +
                    "AND birth_year = :birthYear",
            nativeQuery = true
    )
    void deleteAuthorFromFirstNameLastNameBirthDate(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("birthYear") LocalDate birthYear);
}
