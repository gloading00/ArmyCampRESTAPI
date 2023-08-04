package gr.marag.army.springboot.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class SoldierDTO {
    private long id;

    // first name should not be null or empty
    @NotEmpty(message = "First Name should not be null or empty")
    private String firstName;

    // last name should not be null or empty
    @NotEmpty(message = "Last Name should not be null or empty")
    private String lastName;

    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    // age should not be null or empty
    // age should be 2 characters
    @NotEmpty
    @Size(min = 2,max = 2, message = "Age must be 2 characters")
    private String age;


    //body status  should not be bull or empty
    //body status must be  2 characters
    @NotEmpty
    @Size(min = 1, max = 2, message = "Body status must be 2 characters")
    private String bodyStatus;
}