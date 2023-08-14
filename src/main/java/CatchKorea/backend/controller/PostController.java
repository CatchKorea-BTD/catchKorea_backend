package CatchKorea.backend.controller;

import CatchKorea.backend.dto.PostDto;
import CatchKorea.backend.entity.Category;
import CatchKorea.backend.entity.Hashtag;
import CatchKorea.backend.entity.Post;
import CatchKorea.backend.exception.CustomExceptions;
import CatchKorea.backend.service.CategoryService;
import CatchKorea.backend.service.HashtagService;
import CatchKorea.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static CatchKorea.backend.dto.PostDto.*;
import static CatchKorea.backend.exception.CustomExceptions.*;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CategoryService categoryService;
    private final HashtagService hashtagService;

    @PostMapping("/post/{category_id}/{hashtag_id}")
    public ResponseEntity<Post> uploadPost(@PathVariable Long category_id,@PathVariable Long hashtag_id, PostRequestDto postRequestDto) {
        Category category = categoryService.findCategoryById(category_id)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "category를 찾을 수 없습니다."));

        Hashtag hashtag = hashtagService.findHashtagById(hashtag_id)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "hashTag를 찾을 수 없습니다."));

        Post post = postRequestDto.to_Entity();
        post.setCategory(category);
        post.setHashtag(hashtag);
        postService.save(post);
        return ResponseEntity.ok(post);
    }
}
