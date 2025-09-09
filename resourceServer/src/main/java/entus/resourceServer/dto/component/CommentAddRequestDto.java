package entus.resourceServer.dto.component;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentAddRequestDto {
    private Long postId;
    @NotBlank
    private String comment;
}
