package entus.resourceServer.domain.dto;

import lombok.Data;
import java.util.List;

@Data
public class BoardDto {
    public BoardDto(List<PostListDto> hotPosts, List<PostListDto> posts) {
        this.hotPosts = hotPosts;
        this.posts = posts;
    }

    List<PostListDto> hotPosts;
    List<PostListDto> posts;
}
