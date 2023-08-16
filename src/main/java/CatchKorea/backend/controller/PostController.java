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
import java.util.Optional;

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

    @PostMapping("/upload/post")
    public ResponseEntity<?> uploadPostWithoutCategory(@RequestBody PostRequestDto postRequestDto) {
        postService.saveWithoutCategory(postRequestDto);
        return ResponseEntity.ok(postRequestDto);
    }

    @PostMapping("category/upload")
    public ResponseEntity<CategoryResponseDto> uploadCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryRequestDto.to_Entity();
        categoryService.save(category);
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto(category);
        return ResponseEntity.ok(categoryResponseDto);
    }


    // 게시물로 조회
    @GetMapping("/search")
    public ResponseEntity<PostContentsResponse> getPostByTitle(@RequestParam String query) {
        Post post = postService.getPostByName(query).orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "해당 제목에 대한 게시물이 존재하지 않습니다."));
        PostContentsResponse postContentsResponse = new PostContentsResponse(post);
        return ResponseEntity.ok(postContentsResponse);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<PostContentsResponse> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id).orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "게시물이 존재하지 않습니다."));
        PostContentsResponse postContentsResponse = new PostContentsResponse(post);
        return ResponseEntity.ok(postContentsResponse);
    }


    @GetMapping("/post/{category_id}")
    public ResponseEntity<List<PostResponseDto>> getPostByCategory(@PathVariable Long category_id) {
        List<PostResponseDto> postTitleDtoList = postService.findPostByCategory(category_id);
        if (postTitleDtoList.isEmpty()) {
            throw new CustomException(HttpStatus.OK, "게시물이 없습니다.");
        }
        return ResponseEntity.ok(postTitleDtoList);
    }

    @GetMapping("/post")
    public ResponseEntity<List<PostResponseDto>> getPostsByHashTag(@RequestParam String hashTag) {
        List<PostResponseDto> postTitleDtoList = postService.findPostsByHashTag(hashTag);
        if (postTitleDtoList.isEmpty()) {
            throw new CustomException(HttpStatus.OK,"해당 HashTag를 가진 제품이 없습니다.");
        }
        return ResponseEntity.ok(postTitleDtoList);
    }
}
