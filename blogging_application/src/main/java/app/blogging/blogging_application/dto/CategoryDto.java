package app.blogging.blogging_application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
    private int categoryId;

    @NotEmpty
    @Size(min = 3)
    private String categoryTitle;

    @NotEmpty
    @Size(min = 3)
    private String categoryDescription;
    
}
