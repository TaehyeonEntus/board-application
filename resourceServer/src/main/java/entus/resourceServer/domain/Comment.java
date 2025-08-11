package entus.resourceServer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private Integer likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    protected Comment(String content, User author, Post post) {
        this.content = content;
        this.author = author;
        this.post = post;
        this.likeCount = 0;
    }

    //<-- 생성 메서드 -->
    public static Comment createComment(User author, Post post, String content) {
        Comment comment = new Comment(content, author, post);
        author.addComment(comment);
        return comment;
    }
}
