package com.skodin.services;

import com.skodin.models.ClientEntity;
import com.skodin.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository userRepository;


    public void register(String clientId, String clientSecret) {
        if(userRepository.findById(clientId).isPresent()) {
            throw new RuntimeException(
                    "Client with id: " + clientId + " already registered");
        }

        String hash = BCrypt.hashpw(clientSecret, BCrypt.gensalt());
        userRepository.save(new ClientEntity(clientId, hash));
    }

    public void checkCredentials(String clientId, String clientSecret) throws LoginException {
        Optional<ClientEntity> optionalUserEntity = userRepository
                .findById(clientId);

        if (optionalUserEntity.isEmpty())
            throw new LoginException(
                    "Client with id: " + clientId + " not found");

        ClientEntity clientEntity = optionalUserEntity.get();

        if (!BCrypt.checkpw(clientSecret, clientEntity.getHash()))
            throw new LoginException("Secret is incorrect");
    }
}