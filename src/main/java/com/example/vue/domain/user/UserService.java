package com.example.vue.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(UserException.passNoExistExceptionSupplier(id));
    }

    public List<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
