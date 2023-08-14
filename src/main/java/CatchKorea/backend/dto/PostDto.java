package CatchKorea.backend.dto;

import CatchKorea.backend.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PostDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostRequestDto {
        private String title;
        private String contents;
        private String serverLink;

        public Post to_Entity() {
            return Post.builder()
                    .title(title)
                    .contents(contents)
                    .serviceLink(serverLink)
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostTitleDto {
        private Long id;
        private String title;

        public PostTitleDto(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
        }
    }

}
