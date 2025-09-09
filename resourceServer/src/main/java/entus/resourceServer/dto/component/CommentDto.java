package entus.resourceServer.dto.component;

import entus.resourceServer.domain.Comment;
import lombok.Data;

@Data
public class CommentDto {
    private Long commentId;

    private String authorName;
    private String content;

    private Integer likeCount;
    private boolean like;

    public CommentDto(Comment comment, boolean like) {
        this.commentId = comment.getId();
        this.authorName = comment.getAuthor().getName();
        this.content = comment.getContent();
        this.likeCount = comment.getLikeCount();
        this.like = like;
    }
}
