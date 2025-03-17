package trial.alten.kata.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trial.alten.kata.models.User;
import trial.alten.kata.services.UserService;

@RestController
@RequestMapping("/account")
@Tag(name = "Account Operations", description = "Endpoints for handling account operations.")
public class AccountController {

    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Register",
            description = "Endpoint for registering a new user."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Account created successfully."
            )
    })
    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok("Compte créé !");
    }
}


