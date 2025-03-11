package trial.alten.kata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trial.alten.kata.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
