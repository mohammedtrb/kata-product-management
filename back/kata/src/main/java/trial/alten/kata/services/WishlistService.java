package trial.alten.kata.services;

import org.springframework.stereotype.Service;
import trial.alten.kata.exceptions.InvalidRequestException;
import trial.alten.kata.models.Product;
import trial.alten.kata.models.User;
import trial.alten.kata.models.Wishlist;
import trial.alten.kata.repositories.ProductRepository;
import trial.alten.kata.repositories.UserRepository;
import trial.alten.kata.repositories.WishlistRepository;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public WishlistService(WishlistRepository wishlistRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    public Wishlist getWishlistByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new InvalidRequestException("User not found"));

        return wishlistRepository.findByUser(user).orElse(null);
    }

    public Wishlist addToWishlist(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new InvalidRequestException("User not found"));
        Wishlist wishlist = wishlistRepository.findByUser(user).orElse(new Wishlist());

        if (wishlist.getId() == null) {
            wishlist.setUser(user);
            wishlist.setCreatedAt(System.currentTimeMillis());
        }

        Product product = productRepository.findById(productId).orElseThrow(() -> new InvalidRequestException("Product not found"));

        if (!wishlist.getProducts().contains(product)) {
            wishlist.getProducts().add(product);
        }

        wishlist.setUpdatedAt(System.currentTimeMillis());
        return wishlistRepository.save(wishlist);
    }

    public Wishlist removeFromWishlist(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new InvalidRequestException("User not found"));
        Wishlist wishlist = wishlistRepository.findByUser(user).orElseThrow(() -> new InvalidRequestException("Wishlist not found"));

        Product product = productRepository.findById(productId).orElseThrow(() -> new InvalidRequestException("Product not found"));

        if (wishlist.getProducts().contains(product)) {
            wishlist.getProducts().remove(product);
        }

        wishlist.setUpdatedAt(System.currentTimeMillis());
        return wishlistRepository.save(wishlist);
    }
}
