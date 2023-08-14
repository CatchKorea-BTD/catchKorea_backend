package CatchKorea.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RootCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "rootCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> postList;

    public RootCategory(String name) {
        this.name = name;
    }
}