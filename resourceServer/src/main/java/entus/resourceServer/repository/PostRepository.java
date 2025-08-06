package entus.resourceServer.repository;

import entus.resourceServer.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findTop5ByOrderByViewDesc();
}
