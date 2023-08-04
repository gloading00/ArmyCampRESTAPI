package gr.marag.army.springboot.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampTypeDTO {
    private Long id;
    private String name;
    private String description;
}
