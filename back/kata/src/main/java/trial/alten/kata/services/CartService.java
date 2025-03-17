package trial.alten.kata.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trial.alten.kata.exceptions.InvalidRequestException;
import trial.alten.kata.models.Cart;
import trial.alten.kata.models.OrderDetail;
import trial.alten.kata.models.Product;
import trial.alten.kata.models.User;
import trial.alten.kata.repositories.CartRepository;
import trial.alten.kata.repositories.OrderDetailRepository;
import trial.alten.kata.repositories.ProductRepository;
import trial.alten.kata.repositories.UserRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository, OrderDetailRepository orderDetailRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderDetailRepository = orderDetailRepository;
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

    @Transactional
    public Cart addToCart(Long userId, Long productId, int quantity) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new InvalidRequestException("User not found"));

        Cart cart = cartRepository.findByUser(user).orElse(null);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
        }
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new InvalidRequestException("Product not found"));
        //TODO: check if product is available IN_STOCK

        Cart finalCart = cart;
        OrderDetail orderDetail = orderDetailRepository.findByCartIdAndProductId(cart.getId(), productId).orElseGet(() -> {
            OrderDetail od = new OrderDetail();
            od.setCart(finalCart);
            od.setProduct(product);
            od.setQuantity(0);
            finalCart.getOrderDetails().add(od);
            return od;
        });
        orderDetail.setQuantity(orderDetail.getQuantity() + quantity);

        return cartRepository.save(finalCart);
    }

    @Transactional
    public Cart updateQuantity(Long userId, Long productId, int quantity) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new InvalidRequestException("User not found"));

        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new InvalidRequestException("Cart not found"));

        OrderDetail orderDetail = orderDetailRepository.findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new InvalidRequestException("Product not found in cart"));

        if (quantity <= 0) {
            orderDetailRepository.delete(orderDetail);
            cart.getOrderDetails().remove(orderDetail);
        } else {
            orderDetail.setQuantity(quantity);
            orderDetailRepository.save(orderDetail);
        }

        return cartRepository.save(cart);
    }


    public Cart clearCart(Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new InvalidRequestException("User not found"));

        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new InvalidRequestException("Cart not found"));

        cart.getOrderDetails().clear();

        return cartRepository.save(cart);
    }


}
