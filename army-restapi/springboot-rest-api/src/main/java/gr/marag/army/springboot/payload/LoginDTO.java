package gr.marag.army.springboot.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    //user can enter with username or email
    private String usernameOrEmail;
    private String password;
}
