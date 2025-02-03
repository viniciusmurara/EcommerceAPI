package com.murara.ecommerce.controller;

import com.murara.ecommerce.DTO.ProductResponseDTO;
import com.murara.ecommerce.DTO.ProductRequestDTO;
import com.murara.ecommerce.model.Product;
import com.murara.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("products")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public void save(@RequestBody ProductRequestDTO productData) {
        Product product = new Product(productData);

        // Converter Base64 para arquivo (se necessário)
        if (productData.getImage() != null && !productData.getImage().isEmpty()) {
            try {
                // Decodificar imagem em Base64
                byte[] imageBytes = Base64.getDecoder().decode(productData.getImage().split(",")[1]);

                // Definir diretório e nome do arquivo
                String directoryPath = "src/main/resources/static/uploads/";
                File directory = new File(directoryPath);
                if (!directory.exists()) {
                    directory.mkdirs(); // Cria o diretório se não existir
                }

                String fileName = UUID.randomUUID().toString() + ".png";
                String filePath = directoryPath + fileName;

                // Salvar o arquivo no diretório
                try (FileOutputStream fos = new FileOutputStream(filePath)) {
                    fos.write(imageBytes);
                }

                // Definir o caminho do arquivo salvo no produto
                product.setImage(fileName);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao salvar imagem", e);
            }
        }

        productRepository.save(product);
    }


    @GetMapping
    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll().stream().map(ProductResponseDTO::new).toList();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
