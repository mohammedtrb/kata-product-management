package trial.alten.kata.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trial.alten.kata.models.Cart;
import trial.alten.kata.services.CartService;

@RestController
@RequestMapping("/cart")
@Tag(name = "Cart Operations", description = "Endpoints for handling cart operations.")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(
            summary = "Get Cart",
            description = "Endpoint for getting a user's cart."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK. Cart retrieved successfully."
    )
    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @Operation(
            summary = "Add to Cart",
            description = "Endpoint for adding a product to a user's cart."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK. Product added to cart successfully."
    )
    @PostMapping("/{userId}/{productId}/add/{quantity}")
    public ResponseEntity<Cart> addToCart( @PathVariable Long userId, @PathVariable Long productId, @RequestParam(defaultValue = "1") int quantity) {
        return ResponseEntity.ok(cartService.addToCart(userId, productId, quantity));
    }

    @Operation(
            summary = "Update Cart",
            description = "Endpoint for updating a product on a user's cart."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK. Cart updated successfully."
    )
    @PutMapping("/{userId}/{productId}/update")
    public ResponseEntity<Cart> updateCart(@PathVariable Long userId, @PathVariable Long productId, @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.updateQuantity(userId, productId, quantity));
    }

    @Operation(
            summary = "Clear Cart",
            description = "Endpoint for clearing a user's cart."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK. Cart cleared."
    )
    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Cart> clearCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.clearCart(userId));
    }

}
