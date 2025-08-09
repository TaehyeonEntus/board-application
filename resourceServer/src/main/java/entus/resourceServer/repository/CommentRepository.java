package entus.resourceServer.repository;

import entus.resourceServer.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> getCommentByOrderByLikeCountDesc();
}
