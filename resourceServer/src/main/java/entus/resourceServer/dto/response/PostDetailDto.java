package entus.resourceServer.dto.response;

import entus.resourceServer.domain.Post;
import lombok.Data;

@Data
public class PostDetailDto {
    private Long postId;
    private Long authorId;

    private String authorName;
    private String title;
    private String content;

    private Integer viewCount;
    private Integer likeCount;
    private boolean like;

    public PostDetailDto(Post post, boolean like) {
        this.postId = post.getId();
        this.authorId = post.getAuthor().getId();
        this.authorName = post.getAuthor().getName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.viewCount = post.getViewCount();
        this.likeCount = post.getLikeCount();
        this.like = like;
    }
}
