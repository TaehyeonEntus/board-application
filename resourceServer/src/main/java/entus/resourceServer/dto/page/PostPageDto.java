package entus.resourceServer.dto.page;

import entus.resourceServer.dto.component.CommentDto;
import entus.resourceServer.dto.component.PostDto;
import lombok.Data;

import java.util.List;

@Data
public class PostPageDto {
    int currentPage;
    int totalPage;

    PostDto postDto;
    List<CommentDto> commentDto;

    public PostPageDto(PostDto postDto, List<CommentDto> commentDto, int currentPage, int totalPage) {
        this.postDto = postDto;
        this.commentDto = commentDto;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }
}
