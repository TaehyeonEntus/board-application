package entus.resourceServer.repository;

import entus.resourceServer.domain.Comment;
import entus.resourceServer.domain.CommentLike;
import entus.resourceServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
