package entus.resourceServer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseEntity{
    @Id
    @Column(name="user_id")
    private Long id;

    @OneToMany(mappedBy = "author")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Comment> comments = new ArrayList<>();

    protected User(Long id) {
        this.id = id;
    }

    //<-- 생성 메서드 -->
    public static User createUser(Long userId){
        return new User(userId);
    }
}
