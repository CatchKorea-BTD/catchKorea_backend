package CatchKorea.backend.service;

import CatchKorea.backend.dto.PostDto;
import CatchKorea.backend.entity.Post;
import CatchKorea.backend.repositroy.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public void save(Post post) {
        postRepository.save(post);
    }
    public List<PostDto> readPostOneByName(String title){

        return postRepository.findByTitleContainingIgnoreCase(title);
    }

}
