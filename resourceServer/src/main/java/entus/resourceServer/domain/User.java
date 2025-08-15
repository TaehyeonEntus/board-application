package entus.resourceServer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseEntity{
    @Id
    @Column(name="user_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private Set<PostLike> postLikes = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<CommentLike> commentLikes = new HashSet<>();

    protected User(Long id) {
        this.id = id;
        this.name = "default";
    }

    //<-- 생성 메서드 -->
    public static User createUser(Long userId){
        return new User(userId);
    }

    //<-- 편의 메서드 -->
    public void addPost(Post post){
        posts.add(post);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
}
