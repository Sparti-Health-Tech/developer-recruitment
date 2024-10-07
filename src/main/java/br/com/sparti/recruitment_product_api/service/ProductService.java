package br.com.sparti.recruitment_product_api.service;

import br.com.sparti.recruitment_product_api.model.Product;
import br.com.sparti.recruitment_product_api.record.ProductCreate;
import br.com.sparti.recruitment_product_api.record.ProductUpdate;
import br.com.sparti.recruitment_product_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Page<Product> findAll(PageRequest pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }

    public Product create(ProductCreate productCreate) {
        if (productCreate.getExpirationDate() == null && productCreate.isPerishable()) {
            throw new  IllegalArgumentException("Expiration date is mandatory when the product is perishable");
        }

        Product product = new Product();
        product.setName(productCreate.getName());
        product.setUnitMeasurement(productCreate.getUnitMeasurement());
        product.setAmount(productCreate.getAmount());
        product.setPrice(parseBigDecimal(productCreate.getPrice()));
        product.setPerishable(productCreate.isPerishable());
        product.setExpirationDate(productCreate.getExpirationDate());
        product.setDateManufacture(productCreate.getDateManufacture());

        return productRepository.save(product);
    }

    public Product update(UUID id, ProductUpdate productUpdate) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            if (productOptional.get().getExpirationDate() == null && productOptional.get().isPerishable()) {
                throw new  IllegalArgumentException("Expiration date is mandatory when the product is perishable");
            }
            Product product = productOptional.get();
            product.setName(productUpdate.getName());
            product.setUnitMeasurement(productUpdate.getUnitMeasurement());
            product.setAmount(productUpdate.getAmount());
            product.setPrice(parseBigDecimal(productUpdate.getPrice()));
            product.setPerishable(productUpdate.isPerishable());
            product.setExpirationDate(productUpdate.getExpirationDate());
            product.setDateManufacture(productUpdate.getDateManufacture());

            return productRepository.save(product);
        }
        return null;
    }

    public static BigDecimal parseBigDecimal(String valor) {
        // Substitui a vírgula por ponto
        if (valor.contains(",")) {
            valor = valor.replace(",", ".");
        }

        // Cria o BigDecimal a partir da string corrigida
        return new BigDecimal(valor);
    }
}
