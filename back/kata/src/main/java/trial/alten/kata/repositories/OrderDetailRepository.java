package trial.alten.kata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import trial.alten.kata.models.OrderDetail;

import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    Optional<OrderDetail> findByCartIdAndProductId(Long cartId, Long productId);
}
