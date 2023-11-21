package com.skodin.controllers;

import com.skodin.DTO.ErrorResponse;
import com.skodin.DTO.TokenResponse;
import com.skodin.DTO.UserDTO;
import com.skodin.services.ClientService;
import com.skodin.services.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ClientService clientService;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserDTO user) {
        clientService.register(user.getClientId(), user.getClientSecret());
        return ResponseEntity.ok("Registered");
    }

    @SneakyThrows
    @PostMapping("/token")
    public TokenResponse getToken(@RequestBody UserDTO user) {
        clientService.checkCredentials(
                user.getClientId(), user.getClientSecret());
        return new TokenResponse(
                tokenService.generateToken(user.getClientId()));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleUserRegistrationException(RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }
}
