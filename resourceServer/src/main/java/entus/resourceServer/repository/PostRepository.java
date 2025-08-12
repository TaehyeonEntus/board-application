package entus.resourceServer.repository;

import entus.resourceServer.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
/*    @Query( value = "select p from Post p",
            countQuery = "select count(p) from Post p")
    @EntityGraph(attributePaths = {"author"})
    Page<Post> findPostsWithAuthor(Pageable pageable);

    @Query(value = "select p from Post p order by p.viewCount desc")
    @EntityGraph(attributePaths = {"author"})
    List<Post> findTop5PostsWithAuthor(Pageable pageable);*/

    List<Post> findTop5PostsByOrderByIdDesc();
}
