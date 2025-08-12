package entus.resourceServer.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostAddDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
