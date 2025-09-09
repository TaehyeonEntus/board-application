package entus.resourceServer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLike {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public PostLike(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    //<-- 생성 메서드 -->
    public static PostLike createPostLike(User user, Post post) {
        return new PostLike(user, post);
    }
}
