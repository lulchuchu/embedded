package com.example.demo.Service;

import com.example.demo.Modal.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserServiceInterface extends UserDetailsService {
    public void deleteUser(Long id);
    public User addUser(User user) ;

    public User getUser(Long id);

    public List<User> getAllUsers();

    public User findByUsername(String username);

    public boolean checkDuplicateEmail(String email);

    public boolean checkDuplicateUserName(String username);
}
