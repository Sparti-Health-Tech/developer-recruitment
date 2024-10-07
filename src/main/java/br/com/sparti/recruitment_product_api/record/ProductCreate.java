package br.com.sparti.recruitment_product_api.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Data
public class ProductCreate {
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
}
