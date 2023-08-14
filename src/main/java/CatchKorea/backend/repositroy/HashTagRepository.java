package CatchKorea.backend.repositroy;

import CatchKorea.backend.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashTagRepository extends JpaRepository<Hashtag, Long> {
    Optional<Hashtag> findHashtagById(Long id);
}
