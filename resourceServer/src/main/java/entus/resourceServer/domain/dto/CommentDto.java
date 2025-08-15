package entus.resourceServer.domain.dto;

import entus.resourceServer.domain.Comment;
import lombok.Data;

@Data
public class CommentDto {
    private Long commentId;
    private String authorName;
    private Integer likeCount;
    private String content;

    private boolean like;

    public CommentDto(Comment comment, Long userId) {
        this.commentId = comment.getId();
        this.authorName = comment.getAuthor().getName();
        this.likeCount = comment.getCommentLikes().size();
        this.like = comment.getCommentLikes().stream().anyMatch(pl -> pl.getUser().getId().equals(userId));
        this.content = comment.getContent();
    }
}
