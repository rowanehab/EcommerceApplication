package com.example.demo.Controller;

import com.example.demo.Dto.LoginDTO;
import com.example.demo.Dto.UserDTO;
import com.example.demo.Entity.MyUser;
import com.example.demo.Exceptions.EmailAlreadyExistsException;
import com.example.demo.Exceptions.UsernameAlreadyExistsException;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import com.example.demo.response.LoginMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class AuthenticationController {
    @GetMapping("/HappyRo")
    public String HappyRo() {
        return "Happy Ro :D";
    }
    @GetMapping("/SadRo")
    public String SadRo() {
        return "Sad ro :(";
    }
    @GetMapping("/okRo")
    public String okRo() {
        return "ok ro";
    }
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/save")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDto) {
        // Validate user input (this can also be done using annotations)
        if (userDto.getUsername() == null || userDto.getUsername().isEmpty()) {
            return ResponseEntity.badRequest().body("Username is required");
        }

        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Password is required");
        }

        // Check for existing username and email
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists: " + userDto.getUsername());
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + userDto.getEmail());
        }

        // Save user
        MyUser savedUser = userService.saveUser(userDto);
        String successMessage = "User successfully saved: " + savedUser.getUsername();
        logger.info(successMessage);
        return ResponseEntity.ok(successMessage);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();

        // Call the loginUser method from UserService
        LoginMessage loginMessage = userService.loginUser(loginDTO);

        if (loginMessage.getStatus()) {
            // Login successful
            return ResponseEntity.ok("Login successful");
        } else {
            // Invalid username or password
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginMessage.getMessage());
        }
    }


}
