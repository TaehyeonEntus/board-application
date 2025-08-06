package entus.resourceServer.service;

import entus.resourceServer.domain.User;
import entus.resourceServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long add(User user) {
        return userRepository.save(user).getId();
    }

    public User get(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void syncUser(Long userId) {
        userRepository.findById(userId).orElseGet(() -> userRepository.save(User.createUser(userId)));
    }
}
