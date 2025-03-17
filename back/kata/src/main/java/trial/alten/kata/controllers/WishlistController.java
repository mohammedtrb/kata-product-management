package trial.alten.kata.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trial.alten.kata.models.Wishlist;
import trial.alten.kata.services.UserService;
import trial.alten.kata.services.WishlistService;

@RestController
@RequestMapping("/wishlist")
@Tag(name = "Wishlist Operations", description = "Endpoints for handling wishlist operations.")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Wishlist> getWishlist(@PathVariable  Long userId) {
        return new ResponseEntity<>(wishlistService.getWishlistByUser(userId), HttpStatus.OK);
    }

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<Wishlist> addToWishlist(@PathVariable Long userId,@PathVariable Long productId) {
        return new ResponseEntity<>(wishlistService.addToWishlist(userId, productId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<Wishlist> removeFromWishlist(@PathVariable Long userId,@PathVariable Long productId) {
        return new ResponseEntity<>(wishlistService.removeFromWishlist(userId, productId), HttpStatus.OK);
    }
}
