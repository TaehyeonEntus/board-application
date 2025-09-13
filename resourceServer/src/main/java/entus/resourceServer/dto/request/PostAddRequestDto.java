package entus.resourceServer.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostAddRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
