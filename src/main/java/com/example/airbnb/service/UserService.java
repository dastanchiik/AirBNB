package com.example.airbnb.Service;

import com.example.airbnb.dto.UserRequest;
import com.example.airbnb.dto.response.UserResponse;
import com.example.airbnb.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.airbnb.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }

    public void update(Long id, User user) {
        try {
            User user1 = userRepository.findById(id).orElseThrow();
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setUserName(user.getUserName());
            userRepository.save(user);
        } catch (Exception exception) {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public User findById(Long id) {
        userRepository.findById(id);
        return null;
    }

    public User save(UserRequest user) {
        User user1 = new ModelMapper().map(user, User.class);
        return userRepository.save(user1);
    }


    public List<UserResponse> findAll() {
        List<UserResponse> list = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setEmail(user.getEmail());
            userResponse.setUserName(user.getUserName());
        }

        return list;
    }
}
