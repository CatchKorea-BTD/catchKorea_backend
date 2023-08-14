package CatchKorea.backend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Post> postList;
    private String categoryName;
}
