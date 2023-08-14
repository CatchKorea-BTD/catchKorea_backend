package CatchKorea.backend.repositroy;

import CatchKorea.backend.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<Hashtag, Long> {
}
