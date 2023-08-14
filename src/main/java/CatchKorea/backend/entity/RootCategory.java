package CatchKorea.backend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class RootCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "rootCategory", fetch = FetchType.LAZY)
    private List<Post> postList;
}
