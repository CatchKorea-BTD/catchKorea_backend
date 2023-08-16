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
        private String serviceLink;
        private String hashtag;

        public Post to_Entity() {
            return Post.builder()
                    .title(title)
                    .contents(contents)
                    .serviceLink(serviceLink)
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostResponseDto {
        private Long id;
        private String title;

        public PostResponseDto(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostContentsResponse {
        private Long id;
        private String title;
        private String content;
        private String serviceLink;

        public PostContentsResponse(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContents();
            this.serviceLink = post.getServiceLink();
        }
    }

}
