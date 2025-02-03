package com.murara.ecommerce.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.murara.ecommerce.model.Product;
import lombok.Setter;

@Setter
public class ProductResponseDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private double price;
    @JsonProperty("image")
    private String image;

    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.type = product.getType();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.image = product.getImage();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ProductResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
