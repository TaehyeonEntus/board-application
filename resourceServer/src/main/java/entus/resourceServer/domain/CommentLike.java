package entus.resourceServer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public CommentLike(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;
}
