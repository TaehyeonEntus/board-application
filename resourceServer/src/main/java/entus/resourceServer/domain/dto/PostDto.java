package entus.resourceServer.domain.dto;

import entus.resourceServer.domain.Comment;
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

    private List<Comment> comments;

    public PostDto(Post post) {
        this.postId = post.getId();
        this.authorId = post.getAuthor().getId();
        this.authorName = post.getAuthor().getName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.viewCount = post.getViewCount();
        this.likeCount = post.getLikeCount();
        this.comments = post.getComments();
    }
}
