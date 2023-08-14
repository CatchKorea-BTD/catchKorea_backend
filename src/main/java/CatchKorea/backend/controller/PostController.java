package CatchKorea.backend.controller;

import CatchKorea.backend.dto.CategoryDto;
import CatchKorea.backend.dto.HashTagDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static CatchKorea.backend.dto.CategoryDto.*;
import static CatchKorea.backend.dto.HashTagDto.*;
import static CatchKorea.backend.dto.PostDto.*;
import static CatchKorea.backend.exception.CustomExceptions.*;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CategoryService categoryService;
    private final HashtagService hashtagService;

    @PostMapping("/post/{category_id}/{hashtag_id}")
    public ResponseEntity<PostRequestDto> uploadPost(@PathVariable Long category_id,
                                                     @PathVariable Long hashtag_id,
                                                     @RequestBody PostRequestDto postRequestDto) {
        Category category = categoryService.findCategoryById(category_id)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "category를 찾을 수 없습니다."));

        Hashtag hashtag = hashtagService.findHashtagById(hashtag_id)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "hashTag를 찾을 수 없습니다."));

        Post post = postRequestDto.to_Entity();
        post.setCategory(category);
        post.setHashtag(hashtag);
        postService.save(post);
        return ResponseEntity.ok(postRequestDto);
    }

    @PostMapping("/hashtag/upload")
    public ResponseEntity<HashTagRequestDto> uploadHashTag(@RequestBody HashTagRequestDto hashTagRequestDto) {
        Hashtag hashtag = hashTagRequestDto.to_Entity();
        hashtagService.save(hashtag);
        return ResponseEntity.ok(hashTagRequestDto);
    }

    @PostMapping("category/upload")
    public ResponseEntity<CategoryRequestDto> uploadCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryRequestDto.to_Entity();
        categoryService.save(category);
        return ResponseEntity.ok(categoryRequestDto);
    }

    // 게시물로 조회
    @GetMapping("/search")
    public ResponseEntity<List<String>> getPostByTitle(@RequestParam String title){
        List<String> postTitles = postService.readPostAllByName(title);
        return ResponseEntity.ok(postTitles);

    }

}
