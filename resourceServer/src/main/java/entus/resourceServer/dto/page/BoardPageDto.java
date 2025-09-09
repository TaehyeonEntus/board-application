package entus.resourceServer.dto.page;

import entus.resourceServer.dto.component.PostSummaryDto;
import lombok.Data;
import java.util.List;

@Data
public class BoardPageDto {
    int currentPage;
    int totalPage;

    List<PostSummaryDto> hotPosts;
    List<PostSummaryDto> posts;

    public BoardPageDto(List<PostSummaryDto> hotPosts, List<PostSummaryDto> posts, int currentPage, int totalPage) {
        this.hotPosts = hotPosts;
        this.posts = posts;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }
}
