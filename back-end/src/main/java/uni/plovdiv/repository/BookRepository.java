package uni.plovdiv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uni.plovdiv.domain.model.Book;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query(
            value = "SELECT * FROM BOOK " +
                    "WHERE isbn = :isbn",
            nativeQuery = true
    )
    Optional<Book> findBookByIsbn(@Param("isbn") String isbn);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM BOOK " +
                    "WHERE isbn = :isbn",
            nativeQuery = true
    )
    void deleteBookFromIsbn(@Param("isbn") String isbn);
}
