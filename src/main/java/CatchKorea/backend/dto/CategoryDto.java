package CatchKorea.backend.dto;

import CatchKorea.backend.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CategoryDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryRequestDto {
        private String categoryName;
        public Category to_Entity() {
            return Category.builder()
                    .categoryName(categoryName)
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryResponseDto {
        private Long categoryId;
        private String categoryName;

        public CategoryResponseDto(Category category) {
            this.categoryId = category.getId();
            this.categoryName = category.getCategoryName();
        }
    }
}
