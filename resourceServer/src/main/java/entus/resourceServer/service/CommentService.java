package entus.resourceServer.service;

import entus.resourceServer.domain.*;
import entus.resourceServer.repository.CommentLikeRepository;
import entus.resourceServer.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final UserService userService;
    public Long add(Comment comment) {
        return commentRepository.save(comment).getId();
    }

    public Comment get(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow();
    }

    public Comment like(Long commentId, Long userId) {
        Comment comment = get(commentId);
        User user = userService.get(userId);
        Optional<CommentLike> oCommentLike = commentLikeRepository.findByUserAndComment(user, comment);

        if(oCommentLike.isPresent())
            commentLikeRepository.delete(oCommentLike.get());
        else
            commentLikeRepository.save(new CommentLike(user, comment));

        return comment;
    }
}
