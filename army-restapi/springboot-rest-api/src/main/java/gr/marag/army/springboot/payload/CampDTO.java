package gr.marag.army.springboot.payload;


import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "CampDto Model Information"
)
public class CampDTO {
    private long id;


    @Schema(
            description = "Camp Name"
    )
    @NotEmpty
    @Size(min = 5, message = "Camp name should have at least 5 characters")
    private String name;

    @Schema(
            description = "Camp Location"
    )
    @NotEmpty
    @Size(min = 5, message = "Camp location should have at least 5 characters")
    private String location;


    @NotEmpty
    @Size(min = 10, message = "Camp description should have at least 10 characters")
    private String description;

    private Set<SoldierDTO> soldiers;

    private Long typeId;
}
