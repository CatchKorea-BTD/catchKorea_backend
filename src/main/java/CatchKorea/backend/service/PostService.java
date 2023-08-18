package CatchKorea.backend.service;


import CatchKorea.backend.entity.Category;
import CatchKorea.backend.entity.Post;
import CatchKorea.backend.entity.Question;
import CatchKorea.backend.exception.CustomExceptions;
import CatchKorea.backend.repositroy.PostRepository;
import CatchKorea.backend.repositroy.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.List;


import java.util.Optional;
import java.util.stream.Collectors;

import static CatchKorea.backend.dto.PostDto.*;
import static CatchKorea.backend.exception.CustomExceptions.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public void save(Category category, PostRequestDto postRequestDto) {
        Post post = postRequestDto.to_Entity();
        List<String> hashTags = Arrays.stream(postRequestDto.getHashtag().split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        post.setCategory(category);
        post.setHashtag(hashTags);
        postRepository.save(post);

    }

    @Transactional
    public void saveQuestion(QuestionRequestDto questionRequestDto) {
        Question question = questionRequestDto.to_Entity();
        questionRepository.save(question);
    }

    @Transactional
    public void updatePost(Long postId, PostRequestDto postRequestDto) {
        Post post = postRepository.findPostById(postId).orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "post를 찾을 수 없습니다."));
        if (postRequestDto.getTitle() != null) {
            post.setTitle(postRequestDto.getTitle());
        }
        if (postRequestDto.getContents() != null) {
            post.setContents(postRequestDto.getContents());
        }
        if (postRequestDto.getServiceLink() != null) {
            post.setServiceLink(postRequestDto.getServiceLink());
        }
        if (postRequestDto.getHashtag() != null) {
            List<String> hashTags = Arrays.stream(postRequestDto.getHashtag().split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
            post.setHashtag(hashTags);
        }
        if (postRequestDto.getImageLink() != null) {
            post.setImageLink(postRequestDto.getImageLink());
        }
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findPostById(id).orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "Post를 찾을 수 없습니다."));
        postRepository.delete(post);
    }

    @Transactional
    public void saveWithoutCategory(PostRequestDto postRequestDto) {
        Post post = postRequestDto.to_Entity();
        List<String> hashTags = Arrays.stream(postRequestDto.getHashtag().split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        post.setHashtag(hashTags);
        postRepository.save(post);
    }

    public Optional<Post> getPostById(Long id) {
        Optional<Post> post = postRepository.findPostById(id);
        return post;
    }

    public Optional<Post> getPostByName(String title) {
        Optional<Post> post = postRepository.findPostByTitleIgnoreCase(title);
        return post;
    }

    public List<PostResponseDto> findPostByCategory(Long categoryId) {
        List<Post> postList = postRepository.findPostByCategoryId(categoryId);
        List<PostResponseDto> postTitleDtoList = postList.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());

        return postTitleDtoList;
    }

    public List<PostResponseDto> findPostsByHashTag(String hashTag) {
        List<Post> postList = postRepository.findPostByHashtag(hashTag);

        List<PostResponseDto> postTitleDtoList = postList.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());

        return postTitleDtoList;
    }

}
