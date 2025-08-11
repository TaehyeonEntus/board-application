package entus.resourceServer.domain.dto;

import entus.resourceServer.domain.Post;
import lombok.Data;

@Data
public class PostListDto {
    private Long postId;

    private String authorName;

    private String title;

    private Integer viewCount;

    private Integer likeCount;

    public PostListDto(Post post) {
        this.postId = post.getId();
        this.authorName = post.getAuthor().getName();
        this.title = post.getTitle();
        this.viewCount = post.getViewCount();
        this.likeCount = post.getLikeCount();
    }
}
