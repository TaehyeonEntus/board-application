package entus.resourceServer.service;

import entus.resourceServer.domain.*;
import entus.resourceServer.repository.CommentLikeRepository;
import entus.resourceServer.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    public Long add(Comment comment) {
        return commentRepository.save(comment).getId();
    }

    public Comment get(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow();
    }

    public Page<Comment> getCommentsByPost(Post post, Pageable pageable) {
        return commentRepository.findAllByPost(post,pageable);
    }

    public void like(Comment comment, User user) {
        Optional<CommentLike> commentLike = commentLikeRepository.findByUserAndComment(user,comment);
        if(commentLike.isPresent()) {
            commentLikeRepository.delete(commentLike.get());
        } else{
            commentLikeRepository.save(CommentLike.createCommentLike(user,comment));
        }
    }
}
