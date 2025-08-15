package entus.resourceServer.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentAddDto {
    @NotBlank
    private String comment;
}
