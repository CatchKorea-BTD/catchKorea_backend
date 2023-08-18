package CatchKorea.backend.service;

import CatchKorea.backend.entity.Category;
import CatchKorea.backend.entity.Post;
import CatchKorea.backend.repositroy.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static CatchKorea.backend.dto.PostDto.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class PostServiceTest {

    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    @DisplayName("Save 테스트")
    public void testSavePost() {
        // Given
        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("대중교통");

        PostRequestDto postRequestDto = new PostRequestDto();
        postRequestDto.setTitle("네이버페이");
        postRequestDto.setContents("네이버 페이는 구매, 지불을 편하게 해주는 어플입니다..");
        postRequestDto.setServiceLink("www.naver.com");
        postRequestDto.setHashtag("tag1, tag2,              tag3         "); // trim 확인용 공백

        // When
        postService.save(category, postRequestDto);

        // Then
        ArgumentCaptor<Post> postArgumentCaptor = ArgumentCaptor.forClass(Post.class);
        verify(postRepository).save(postArgumentCaptor.capture());

        Post capturedPost = postArgumentCaptor.getValue();
        assertEquals(postRequestDto.getTitle(), capturedPost.getTitle());
        assertEquals(postRequestDto.getContents(), capturedPost.getContents());
        assertEquals(postRequestDto.getServiceLink(), capturedPost.getServiceLink());
        assertEquals(category, capturedPost.getCategory());

        List<String> expectedHashtags = Arrays.asList("tag1", "tag2","tag3");
        assertEquals(expectedHashtags, capturedPost.getHashtag());
    }
    @Test
    @DisplayName("PostGetById 테스트")
    public void testPostGetById() throws Exception {
    //given
        Post post = Post.builder()
                .id(1L)
                .title("카카오맵")
                .contents("카카오맵은 대한민국의 대표적인 지도 맵입니다.")
                .serviceLink("www.google.com")
                .build();
    //when
        when(postRepository.findPostById(1L)).thenReturn(Optional.of(post));
        Optional<Post> postOptional = postService.getPostById(1L);
        Post real_post = postOptional.get();
    //then
        assertNotNull(real_post.getId());
        assertEquals(real_post.getId(), post.getId());
        assertEquals(real_post.getTitle(), post.getTitle());
        assertEquals(real_post.getContents(), post.getContents());
        assertEquals(real_post.getServiceLink(), post.getServiceLink());
    }

    @Test
    @DisplayName("GetPostByName 테스트")
    public void testGetPostByName() throws Exception {
        //given
        Post post = Post.builder()
                .id(1L)
                .title("KAKAO MAP")
                .contents("카카오맵은 대한민국의 대표적인 지도 맵입니다.")
                .serviceLink("www.google.com")
                .build();
        //when
        when(postRepository.findPostByTitleIgnoreCase("kakao map")).thenReturn(Optional.of(post));
        Optional<Post> postOptional = postService.getPostByName("kakao map");
        Post real_post = postOptional.get();
        //then
        assertEquals(real_post.getId(), 1L);
        assertEquals(real_post.getTitle(), post.getTitle());
        assertEquals(real_post.getContents(), post.getContents());
        assertEquals(real_post.getServiceLink(), post.getServiceLink());
    }

    @Test
    @DisplayName("FindPostByCategory 테스트")
    public void testFindPostByCategory() throws Exception {
        // given
        List<Post> postList = new ArrayList<>();
        Post post1 = Post.builder()
                .id(1L)
                .title("카카오맵")
                .contents("카카오맵은 대한민국의 대표적인 지도 맵입니다.")
                .serviceLink("www.google.com")
                .build();
        Post post2 = Post.builder()
                .id(2L)
                .title("네이버맵")
                .contents("네이버맵은 대한민국의 대표적인 지도 맵입니다.")
                .serviceLink("www.naver.com")
                .build();
        postList.add(post1);
        postList.add(post2);

        Category category = Category.builder()
                .id(1L)
                .categoryName("대중교통")
                .postList(postList)
                .build();

        // when
        when(postRepository.findPostByCategoryId(1L)).thenReturn(postList);
        List<PostResponseDto> foundPosts = postService.findPostByCategory(1L);

        // then
        assertEquals(foundPosts.size(), postList.size());
        assertEquals(foundPosts.get(0).getId(), postList.get(0).getId());
        assertEquals(foundPosts.get(1).getId(), postList.get(1).getId());
    }
    @Test
    @DisplayName("FindPostsByHashTag 테스트")
    public void testFindPostsByHashTag()throws Exception {
    //given
        String hashTag = "tag1";
        List<String> hashtag = Arrays.asList("tag1", "tag2","tag3");
        Post post1 = Post.builder()
                .id(1L)
                .title("카카오맵")
                .contents("카카오맵은 대한민국의 대표적인 지도 맵입니다.")
                .serviceLink("www.google.com")
                .hashtag(hashtag)
                .build();
        Post post2 = Post.builder()
                .id(2L)
                .title("네이버맵")
                .contents("네이버맵은 대한민국의 대표적인 지도 맵입니다.")
                .serviceLink("www.naver.com")
                .hashtag(hashtag)
                .build();

        List<Post> postList = new ArrayList<>();
        postList.add(post1);
        postList.add(post2);

        when(postRepository.findPostByHashtag(hashTag)).thenReturn(postList);
    //when
        List<PostResponseDto> foundPosts = postService.findPostsByHashTag(hashTag);

    //then
        assertEquals(foundPosts.size(), postList.size());
        assertEquals(foundPosts.get(0).getId(), postList.get(0).getId());
        assertEquals(foundPosts.get(1).getId(), postList.get(1).getId());
    }
}