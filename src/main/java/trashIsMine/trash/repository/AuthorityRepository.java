package trashIsMine.trash.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import trashIsMine.trash.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}