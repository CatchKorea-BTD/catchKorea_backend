package CatchKorea.backend.service;

import CatchKorea.backend.dto.PostDto;


import CatchKorea.backend.entity.Category;
import CatchKorea.backend.entity.Post;
import CatchKorea.backend.repositroy.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import java.util.stream.Collectors;

import static CatchKorea.backend.dto.PostDto.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;

    public void save(Category category, PostRequestDto postRequestDto) {
        Post post = postRequestDto.to_Entity();
        log.info(postRequestDto.getHashtag());
        List<String> hashTags = Arrays.stream(postRequestDto.getHashtag().split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        log.info(hashTags.toString());
        post.setCategory(category);
        post.setHashtag(hashTags);
        savePost(post);
    }

    private void savePost(Post post) {
        postRepository.save(post);
    }

    public List<PostTitleResponseDto> readPostAllByName(String title){
        List<Post> postList = postRepository.findPostsByTitle(title);
        List<PostTitleResponseDto> postTitleDtoList = postList.stream()
                .map(PostTitleResponseDto::new)
                .collect(Collectors.toList());

        return postTitleDtoList;
    }

    public List<PostTitleResponseDto> findPostByCategory(Long categoryId){
        List<Post> postList = postRepository.findPostByCategoryId(categoryId);
        List<PostTitleResponseDto> postTitleDtoList = postList.stream()
                .map(PostTitleResponseDto::new)
                .collect(Collectors.toList());

        return postTitleDtoList;
    }

    public List<PostTitleResponseDto> findPostsByHashTag(String hashTag) {
        List<Post> postList = postRepository.findPostByHashtag(hashTag);

        List<PostTitleResponseDto> postTitleDtoList = postList.stream()
                .map(PostTitleResponseDto::new)
                .collect(Collectors.toList());

        return postTitleDtoList;
    }
}
