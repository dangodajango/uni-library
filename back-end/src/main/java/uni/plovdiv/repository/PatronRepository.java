package uni.plovdiv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uni.plovdiv.model.Patron;

import java.util.Optional;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {

    @Query(
            value = "SELECT * FROM PATRON " +
                    "WHERE ucn = :ucn",
            nativeQuery = true
    )
    Optional<Patron> findPatronByUcn(@Param("ucn") String ucn);
}
