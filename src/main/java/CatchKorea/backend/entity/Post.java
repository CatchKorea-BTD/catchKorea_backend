package CatchKorea.backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String hashTag;

    @Column(nullable = false)
    private String serviceLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "root_category_id")
    private RootCategory rootCategory;

    public Post(String title, String contents, String hashTag, String serviceLink) {
        this.title = title;
        this.contents = contents;
        this.hashTag = hashTag;
        this.serviceLink = serviceLink;
    }
}