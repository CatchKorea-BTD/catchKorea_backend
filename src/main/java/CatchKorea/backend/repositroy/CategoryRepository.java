package CatchKorea.backend.repositroy;

import CatchKorea.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findCategoryById(Long id);
}
