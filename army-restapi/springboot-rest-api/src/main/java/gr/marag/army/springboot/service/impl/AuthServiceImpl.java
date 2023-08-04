package gr.marag.army.springboot.service.impl;

import gr.marag.army.springboot.entity.Role;
import gr.marag.army.springboot.entity.User;
import gr.marag.army.springboot.exception.ArmyAPIException;
import gr.marag.army.springboot.payload.LoginDTO;
import gr.marag.army.springboot.payload.RegisterDTO;
import gr.marag.army.springboot.repository.RoleRepository;
import gr.marag.army.springboot.repository.UserRepository;
import gr.marag.army.springboot.security.JwtTokenProvider;
import gr.marag.army.springboot.service.AuthService;
import gr.marag.army.springboot.utils.PasswordGeneratorEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;

    }

    @Override
    public String register(RegisterDTO registerDTO) {
        if(userRepository.existsByUsername(registerDTO.getUsername()))
            throw new ArmyAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");

        if(userRepository.existsByEmail(registerDTO.getEmail()))
            throw new ArmyAPIException(HttpStatus.BAD_REQUEST, "Email already exists!");

        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "user added successfully!";

    }
}
