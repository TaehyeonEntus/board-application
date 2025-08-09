package entus.resourceServer.service;

import entus.resourceServer.domain.Post;
import entus.resourceServer.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Long add(Post post) {
        return postRepository.save(post).getId();
    }

    public Post get(Long postId) {
        return postRepository.findById(postId).orElseThrow();
    }

    public Page<Post> getAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public List<Post> getTop5Posts(){
        return postRepository.findTop5ByOrderByIdDesc();
    }
}
