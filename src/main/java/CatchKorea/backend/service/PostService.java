package CatchKorea.backend.service;


import CatchKorea.backend.entity.Category;
import CatchKorea.backend.entity.Post;
import CatchKorea.backend.repositroy.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;


import java.util.Optional;
import java.util.stream.Collectors;

import static CatchKorea.backend.dto.PostDto.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;

    public void save(Category category, PostRequestDto postRequestDto) {
        Post post = postRequestDto.to_Entity();
        List<String> hashTags = Arrays.stream(postRequestDto.getHashtag().split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        post.setCategory(category);
        post.setHashtag(hashTags);
        postRepository.save(post);
    }

    public Optional<Post> getPostById(Long id) {
        Optional<Post> post = postRepository.findPostById(id);
        return post;
    }

    public Optional<Post> getPostByName(String title){
        Optional<Post> post = postRepository.findPostByTitle(title);
        return post;
    }

    public List<PostResponseDto> findPostByCategory(Long categoryId){
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
