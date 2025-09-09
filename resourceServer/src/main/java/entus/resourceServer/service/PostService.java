package entus.resourceServer.service;

import entus.resourceServer.domain.*;
import entus.resourceServer.repository.PostLikeRepository;
import entus.resourceServer.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
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

    public Long add(Post post) {
        return postRepository.save(post).getId();
    }

    public Post get(Long postId) {
        return postRepository.findById(postId).orElseThrow();
    }

    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public List<Post> getTop5Posts(){
        return postRepository.findTop5PostsByOrderByViewCountDesc();
    }

    @Transactional
    public Post getAndAddViewCount(Long postId) throws BadRequestException {
        Post post = postRepository.findById(postId).orElseThrow(BadRequestException::new);
        post.addViewCount();
        return post;
    }

    public void like(Post post, User user) {
        Optional<PostLike> postLike = postLikeRepository.findByUserAndPost(user,post);
        if(postLike.isPresent()) {
            postLikeRepository.delete(postLike.get());
        } else{
            postLikeRepository.save(PostLike.createPostLike(user,post));
        }
    }
}
