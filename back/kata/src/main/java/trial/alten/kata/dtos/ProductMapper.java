package trial.alten.kata.dtos;

import trial.alten.kata.models.InventoryStatus;
import trial.alten.kata.models.Product;

public class ProductMapper {

    public static Product toEntity(ProductDto dto) {
        Product product = new Product();
        product.setCode(dto.getCode());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setInternalReference(dto.getInternalReference());
        product.setShellId(dto.getShellId());
        if(dto.getInventoryStatus() != null) {
            product.setInventoryStatus(InventoryStatus.valueOf(dto.getInventoryStatus()));
        }
        product.setRating(dto.getRating());
        return product;
    }
}
