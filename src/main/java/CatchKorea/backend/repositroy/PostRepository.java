package CatchKorea.backend.repositroy;

import CatchKorea.backend.dto.PostDto;
import CatchKorea.backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findPostsByTitle(String title);
}
