package trial.alten.kata.dtos;

import jakarta.validation.constraints.*;

public class ProductDto {
    private Long id;
    @NotBlank(message = "Le code ne peut pas être vide")
    private String code;
    @NotBlank(message = "Le nom ne peut pas être vide")
    private String name;
    private String description;
    private String image;
    private String category;
    @Positive(message = "Le prix doit être positif")
    private double price;
    @NotNull(message = "La quantité ne peut pas être nulle")
    @Min(value = 0, message = "La quantité doit être supérieure ou égale à 0")
    private int quantity;
    @Pattern(regexp = "IN_STOCK|LOW_STOCK|OUT_OF_STOCK", message = "Statut d'inventaire invalide")
    private String internalReference;
    private Long shellId;
    private String inventoryStatus;
    private double rating;
    private Long createdAt;
    private Long updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getInternalReference() {
        return internalReference;
    }

    public void setInternalReference(String internalReference) {
        this.internalReference = internalReference;
    }

    public Long getShellId() {
        return shellId;
    }

    public void setShellId(Long shellId) {
        this.shellId = shellId;
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
