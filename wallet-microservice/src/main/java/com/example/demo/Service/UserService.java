package com.example.demo.Service;

import com.example.demo.Dto.LoginDTO;
import com.example.demo.Dto.UserDTO;
import com.example.demo.Entity.MyUser;
import com.example.demo.Repository.UserRepository;
import com.example.demo.response.LoginMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<MyUser> user = userRepository.findByUsername(username);
        if(user.isPresent()){
        var userObj=user.get();
        return User.builder()
                .username(userObj.getUsername())
                .password(userObj.getPassword())
                .roles(userObj.getRole())
                .build();
    }
        else{
        throw new UsernameNotFoundException(username);
    }
}

    public MyUser saveUser(UserDTO userDTO) {
        MyUser user = new MyUser();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());

        return userRepository.save(user);
    }
    public LoginMessage loginUser(LoginDTO loginDTO) {
        MyUser user1 = userRepository.findByEmail(loginDTO.getEmail());
        if (user1 != null) { // Check if user1 is not null
            String getPassword = loginDTO.getPassword();
            String storedPassword = user1.getPassword();

            if (storedPassword.equals(getPassword)) { // Direct comparison of plain text passwords
                return new LoginMessage("Login Success", true);
            } else {
                return new LoginMessage("Password does not match", false);
            }
        } else {
            return new LoginMessage("Email does not exist", false);
        }
    }

    public Long getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<MyUser> userOptional = userRepository.findByUsername(username);
        return userOptional.map(MyUser::getId).orElse(null);
    }


}
