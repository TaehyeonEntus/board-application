package entus.resourceServer.dto.response;

import entus.resourceServer.domain.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostSummaryDto {
    private Long postId;

    private String authorName;

    private String title;

    private Integer viewCount;

    private Integer likeCount;

    private LocalDateTime createdAt;

    public PostSummaryDto(Post post) {
        this.postId = post.getId();
        this.authorName = post.getAuthor().getName();
        this.title = post.getTitle();
        this.viewCount = post.getViewCount();
        this.likeCount = post.getPostLikes().size();
        this.createdAt = post.getCreatedAt();
    }
}
