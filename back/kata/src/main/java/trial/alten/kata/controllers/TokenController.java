package trial.alten.kata.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trial.alten.kata.dtos.AuthRequestDto;
import trial.alten.kata.services.AuthService;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final AuthService authService;

    public TokenController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody AuthRequestDto authRequest) {
        String token = authService.authenticate(authRequest.getEmail(), authRequest.getPassword());
        return ResponseEntity.ok(token);
    }
}
