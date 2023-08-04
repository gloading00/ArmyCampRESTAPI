package gr.marag.army.springboot.service;

import gr.marag.army.springboot.payload.LoginDTO;
import gr.marag.army.springboot.payload.RegisterDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);

}
