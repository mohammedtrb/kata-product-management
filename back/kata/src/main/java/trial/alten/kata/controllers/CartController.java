package trial.alten.kata.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trial.alten.kata.models.Cart;
import trial.alten.kata.models.User;
import trial.alten.kata.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<Cart> addToCart( @PathVariable Long userId, @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.addToCart(userId, productId));
    }

}
