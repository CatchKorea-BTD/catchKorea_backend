package CatchKorea.backend.service;

import CatchKorea.backend.entity.Category;
import CatchKorea.backend.repositroy.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findCategoryById(id);
        return category;
    }
}
