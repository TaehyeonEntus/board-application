package entus.resourceServer.dto.page;

import entus.resourceServer.dto.response.CommentDetailDto;
import lombok.Data;

import java.util.List;

@Data
public class CommentPageDto {
    int currentPage;
    int totalPages;

    List<CommentDetailDto> commentDetailDto;

    public CommentPageDto(List<CommentDetailDto> commentDetailDto, int currentPage, int totalPages) {
        this.commentDetailDto = commentDetailDto;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }
}
