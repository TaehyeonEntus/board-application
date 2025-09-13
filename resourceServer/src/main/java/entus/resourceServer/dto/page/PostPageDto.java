package entus.resourceServer.dto.page;

import entus.resourceServer.dto.response.CommentDetailDto;
import entus.resourceServer.dto.response.PostDetailDto;
import lombok.Data;

import java.util.List;

@Data
public class PostPageDto {
    int currentPage;
    int totalPage;

    PostDetailDto postDetailDto;
    List<CommentDetailDto> commentDetailDto;

    public PostPageDto(PostDetailDto postDetailDto, List<CommentDetailDto> commentDetailDto, int currentPage, int totalPage) {
        this.postDetailDto = postDetailDto;
        this.commentDetailDto = commentDetailDto;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }
}
