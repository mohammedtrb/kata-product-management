package trial.alten.kata.services;

import org.springframework.stereotype.Service;
import trial.alten.kata.exceptions.InvalidRequestException;
import trial.alten.kata.models.Cart;
import trial.alten.kata.models.User;
import trial.alten.kata.repositories.CartRepository;
import trial.alten.kata.repositories.ProductRepository;
import trial.alten.kata.repositories.UserRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user).orElseThrow(() -> new InvalidRequestException("Cart not found"));
    }

    public Cart getCartByUserId(Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new InvalidRequestException("User not found"));

        return getCartByUser(user);
    }

    public Cart addToCart(Long userId, Long productId) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new InvalidRequestException("User not found"));

        Cart cart = cartRepository.findByUser(user).orElse(null);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
        }

        cart.getProducts().add(productRepository
                .findById(productId)
                .orElseThrow(() -> new InvalidRequestException("Product not found")));

        return cartRepository.save(cart);
    }


}
