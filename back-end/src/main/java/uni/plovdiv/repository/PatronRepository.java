package uni.plovdiv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.plovdiv.model.Patron;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {
}
