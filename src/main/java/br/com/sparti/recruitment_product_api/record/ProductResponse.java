package br.com.sparti.recruitment_product_api.record;

import br.com.sparti.recruitment_product_api.model.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ProductResponse {
    private UUID id;
    private String name;
    @Schema(type = "Enumeration", example = "kg")
    private String unitMeasurement;
    private Float amount;
    @Schema(type = "BigDecimal", example = "25.50")
    private String price;
    private boolean perishable;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(type = "string", pattern = "dd/MM/yyyy", example = "07/10/2024")
    private LocalDate expirationDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(type = "string", pattern = "dd/MM/yyyy", example = "07/10/2024")
    private LocalDate dateManufacture;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.unitMeasurement = product.getUnitMeasurement();
        this.amount = product.getAmount();
        this.price = product.getPrice().toString();
        this.perishable = product.isPerishable();
        this.expirationDate = product.getExpirationDate();
        this.dateManufacture = product.getDateManufacture();
    }
}
