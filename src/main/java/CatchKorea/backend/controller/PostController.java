package CatchKorea.backend.controller;

import CatchKorea.backend.entity.Category;
import CatchKorea.backend.entity.Post;
import CatchKorea.backend.service.CategoryService;
import CatchKorea.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static CatchKorea.backend.dto.CategoryDto.*;
import static CatchKorea.backend.dto.PostDto.*;
import static CatchKorea.backend.exception.CustomExceptions.*;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CategoryService categoryService;

    @PostMapping("/post/{category_id}")
    public ResponseEntity<PostRequestDto> uploadPost(@PathVariable Long category_id,
                                                     @RequestBody PostRequestDto postRequestDto) {
        Category category = categoryService.findCategoryById(category_id)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "category를 찾을 수 없습니다."));
        postService.save(category,postRequestDto);
        return ResponseEntity.ok(postRequestDto);
    }

    @PostMapping("category/upload")
    public ResponseEntity<CategoryRequestDto> uploadCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryRequestDto.to_Entity();
        categoryService.save(category);
        return ResponseEntity.ok(categoryRequestDto);
    }


    // 게시물로 조회
    @GetMapping("/search")
    public ResponseEntity<List<PostTitleResponseDto>> getPostByTitle(@RequestParam String query) {
        List<PostTitleResponseDto> postTitles = postService.readPostAllByName(query);
        if (postTitles.isEmpty()) {
            throw new CustomException(HttpStatus.OK, "게시물 검색 결과가 없습니다.");
        }
        return ResponseEntity.ok(postTitles);
    }


    @GetMapping("/post/{category_id}")
    public ResponseEntity<List<PostTitleResponseDto>> getPostByCategory(@PathVariable Long category_id) {
        List<PostTitleResponseDto> postTitleDtoList = postService.findPostByCategory(category_id);
        if (postTitleDtoList.isEmpty()) {
            throw new CustomException(HttpStatus.OK, "게시물이 없습니다.");
        }
        return ResponseEntity.ok(postTitleDtoList);
    }

    @GetMapping("/post")
    public ResponseEntity<?> getPostsByHashTag(@RequestParam String hashTag) {
        List<PostTitleResponseDto> postTitleDtoList = postService.findPostsByHashTag(hashTag);
        if (postTitleDtoList.isEmpty()) {
            throw new CustomException(HttpStatus.OK,"해당 HashTag를 가진 제품이 없습니다.");
        }
        return ResponseEntity.ok(postTitleDtoList);
    }
}
