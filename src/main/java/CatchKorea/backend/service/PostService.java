package CatchKorea.backend.service;

import CatchKorea.backend.dto.PostDto;
import CatchKorea.backend.entity.Post;
import CatchKorea.backend.repositroy.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void save(Post post) {
        postRepository.save(post);
    }
    public List<String> readPostAllByName(String title){
        List<Post> postList = postRepository.findPostsByTitle(title);
        List<String> stringList = new ArrayList<>();

        for(Post post: postList){
            stringList.add(post.getTitle());
        }

        return stringList;
    }

}
