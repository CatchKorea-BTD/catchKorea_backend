package CatchKorea.backend.service;

import CatchKorea.backend.dto.PostDto;


import CatchKorea.backend.entity.Post;
import CatchKorea.backend.repositroy.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


import java.util.stream.Collectors;

import static CatchKorea.backend.dto.PostDto.*;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void save(Post post) {
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
