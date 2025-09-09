package entus.resourceServer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;

    private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment")
    private Set<CommentLike> commentLikes = new HashSet<>();

    protected Comment(String content, User author, Post post) {
        this.content = content;
        this.author = author;
        this.post = post;
    }

    //<-- 생성 메서드 -->
    public static Comment createComment(User author, Post post, String content) {
        Comment comment = new Comment(content, author, post);
        author.addComment(comment);
        return comment;
    }

    //<-- 편의 메서드 -->
    public int getLikeCount(){
        return this.commentLikes.size();
    }

    public boolean isLikedBy(Long userId) {
        return commentLikes.stream().anyMatch(pl -> pl.getUser().getId().equals(userId));
    }
}
