package CatchKorea.backend.service;

import CatchKorea.backend.dto.PostDto;
import CatchKorea.backend.dto.PostDto.PostTitleDto;
import CatchKorea.backend.entity.Post;
import CatchKorea.backend.repositroy.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public void save(Post post) {
        postRepository.save(post);
    }

    public List<PostTitleDto> findPostByCategory(Long categoryId){
        List<Post> postList = postRepository.findPostByCategoryId(categoryId);
        List<PostTitleDto> postTitleDtoList = postList.stream()
                .map(PostTitleDto::new)
                .collect(Collectors.toList());

        return postTitleDtoList;
    }
}
