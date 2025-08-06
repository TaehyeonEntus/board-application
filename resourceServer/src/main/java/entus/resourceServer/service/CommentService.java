package entus.resourceServer.service;

import entus.resourceServer.domain.Comment;
import entus.resourceServer.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public Long add(Comment comment) {
        return commentRepository.save(comment).getId();
    }

    public Comment find(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow();
    }
}
