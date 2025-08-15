package entus.resourceServer.service;

import entus.resourceServer.domain.Post;
import entus.resourceServer.domain.PostLike;
import entus.resourceServer.domain.User;
import entus.resourceServer.repository.PostLikeRepository;
import entus.resourceServer.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final UserService userService;

    public Long add(Post post) {
        return postRepository.save(post).getId();
    }

    public Post get(Long postId) {
        return postRepository.findById(postId).orElseThrow();
    }

    public Page<Post> getPostsByPage(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public List<Post> getTop5Posts(){
        return postRepository.findTop5PostsByOrderByViewCountDesc();
    }

    @Transactional
    public void addViewCount(Long postId) {
        postRepository.findById(postId).orElseThrow().addViewCount();
    }

    public Post like(Long postId, Long userId) {
        Post post = get(postId);
        User user = userService.get(userId);
        Optional<PostLike> oPostLike = postLikeRepository.findByUserAndPost(user, post);

        if(oPostLike.isPresent())
            postLikeRepository.delete(oPostLike.get());
        else
            postLikeRepository.save(new PostLike(user, post));

        return post;
    }
}
