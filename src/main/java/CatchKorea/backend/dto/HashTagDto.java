package CatchKorea.backend.dto;

import CatchKorea.backend.entity.Hashtag;
import CatchKorea.backend.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class HashTagDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HashTagRequestDto {
        private String hashtagName;
        public Hashtag to_Entity() {
            return Hashtag.builder()
                    .hashtagName(hashtagName)
                    .build();
        }
    }
}
