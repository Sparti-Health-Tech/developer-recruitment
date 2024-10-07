package br.com.sparti.recruitment_product_api.controller;


import br.com.sparti.recruitment_product_api.model.Product;
import br.com.sparti.recruitment_product_api.record.ProductCreate;
import br.com.sparti.recruitment_product_api.record.ProductResponse;
import br.com.sparti.recruitment_product_api.record.ProductUpdate;
import br.com.sparti.recruitment_product_api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController(value = "product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a product by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "product retrieved"),
            @ApiResponse(responseCode = "404", description = "product was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    public ResponseEntity<ProductResponse> findById(@PathVariable("id") UUID id) {
        Product response = productService.findById(id).orElse(null);

        if (response != null) {
            return ResponseEntity.ok(new ProductResponse(response));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    public ResponseEntity<ProductResponse> create(@RequestBody ProductCreate product){
        return ResponseEntity.ok(new ProductResponse(productService.create(product)));
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a product by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HospitalWithSurgeonAndCXTeams updated"),
            @ApiResponse(responseCode = "404", description = "HospitalWithSurgeonAndCXTeams was not found"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    public ResponseEntity<ProductResponse> update(@PathVariable("id") UUID id ,@RequestBody ProductUpdate product){
        return ResponseEntity.ok(new ProductResponse(productService.update(id ,product)));
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a product list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "products retrieved"),
            @ApiResponse(responseCode = "404", description = "products is empty"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    public ResponseEntity<List<ProductResponse>> findAllProductList(
    ) {
        List<Product> response = productService.findAll();

        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(response.stream().map(ProductResponse::new).toList());
        }
    }

    @GetMapping(value = "/page/{page}/{elements}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a product page list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "products retrieved"),
            @ApiResponse(responseCode = "404", description = "products is empty"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    public ResponseEntity<Page<ProductResponse>> findAllProductListPage(
            @PathVariable("page") int page,
            @PathVariable("elements") int elements
    ) {
        if (page < 0 || elements <= 0) {
            return ResponseEntity.badRequest().build();
        }

        PageRequest pageable = PageRequest.of(page, elements);

        Page<Product> response = productService.findAll(pageable);

        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<ProductResponse> responseMap = response.map(ProductResponse::new).toList();

            return ResponseEntity.ok()
                    .header("Total-Elements", String.valueOf(response.getTotalElements()))
                    .header("Total-Pages", String.valueOf(response.getTotalPages()))
                    .body(new PageImpl<>(responseMap, pageable, response.getTotalElements()));
        }
    }

}
