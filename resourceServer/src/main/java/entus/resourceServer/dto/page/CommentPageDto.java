package entus.resourceServer.dto.page;

import entus.resourceServer.dto.component.CommentDto;
import lombok.Data;

import java.util.List;

@Data
public class CommentPageDto {
    int currentPage;
    int totalPages;

    List<CommentDto> commentDto;

    public CommentPageDto(List<CommentDto> commentDto, int currentPage, int totalPages) {
        this.commentDto = commentDto;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }
}
