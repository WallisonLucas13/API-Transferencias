package com.example.transfer.api.services;

import com.example.transfer.api.exceptions.NotFoundUserException;
import com.example.transfer.api.models.Transfer;
import com.example.transfer.api.models.User;
import com.example.transfer.api.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundUserException("Usuário não encontrado"));
    }

    @Transactional
    public void updateUser(User user, Transfer transfer) {
        user.getTransfers().add(transfer);
        userRepository.save(user);
    }
}
