package CatchKorea.backend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hastTagName;
    @OneToMany(mappedBy = "hashtag", fetch = FetchType.LAZY)
    private List<Post> postList;
}
