package CatchKorea.backend.dto;

import CatchKorea.backend.entity.Category;
import CatchKorea.backend.entity.Hashtag;
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
}
