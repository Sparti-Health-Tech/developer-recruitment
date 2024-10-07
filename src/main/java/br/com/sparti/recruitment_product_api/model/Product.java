package br.com.sparti.recruitment_product_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    @Schema(type = "Enumeration", example = "kg")
    private String UnitMeasurement;

    @Column(nullable = false)
    private Float amount;

    @Column(nullable = false)
    @Schema(type = "BigDecimal", example = "25.50")
    private BigDecimal price;

    @Column(nullable = false)
    private boolean perishable;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(type = "string", pattern = "dd/MM/yyyy", example = "07/10/2024")

    private LocalDate expirationDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(type = "string", pattern = "dd/MM/yyyy", example = "07/10/2024")
    private LocalDate dateManufacture;
}
