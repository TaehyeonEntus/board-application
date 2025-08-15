package entus.resourceServer.domain.dto;

import entus.resourceServer.domain.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostDto {
    private Long postId;

    private Long authorId;

    private String authorName;

    private String title;

    private String content;

    private Integer viewCount;

    private Integer likeCount;

    private List<CommentDto> comments;

    private boolean like;

    public PostDto(Post post,Long userId) {
        this.postId = post.getId();
        this.authorId = post.getAuthor().getId();
        this.authorName = post.getAuthor().getName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.viewCount = post.getViewCount();
        this.likeCount = post.getPostLikes().size();
        this.like = post.getPostLikes().stream().anyMatch(pl -> pl.getUser().getId().equals(userId));
        this.comments = post.getComments().stream().map(comment -> new CommentDto(comment, userId)).toList();
    }
}
