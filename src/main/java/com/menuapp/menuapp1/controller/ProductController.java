package com.menuapp.menuapp1.controller;

import com.menuapp.menuapp1.dto.CreateProductDto;
import com.menuapp.menuapp1.dto.ResponseProductDto;
import com.menuapp.menuapp1.exceptions.ProductNotFoundException;
import com.menuapp.menuapp1.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @Operation(
            summary = "Create Product REST API",
            description = "Create Product REST API is used to save Products into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("/save")
    public ResponseProductDto save(@RequestBody CreateProductDto createProductDto) {
        return productService.save(createProductDto);
    }

    @Operation(
            summary = "Get All Products REST API",
            description = "Get REST API used to fetch all the products from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )

    @GetMapping("/all")
    public List<ResponseProductDto> findAll() {
        return productService.findAll();
    }


// RESPONSE ENTITY TRY
//        @GetMapping("/all")
//    public ResponseEntity<List<ResponseProductDto>> findAll() {
//       List<ResponseProductDto> list = productService.findAll();
//       return new ResponseEntity<>(list,HttpStatus.OK);
//    }

    @Operation(
            summary = "Find Product By ID",
            description = "GET REST API is used to fetch data for a product providing an ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/product/{id}")
    public ResponseEntity<ResponseProductDto> findById(@PathVariable Long id) {
        ResponseProductDto responseProductDto = productService.findById(id);
        return new ResponseEntity<>(responseProductDto, HttpStatus.OK);
    }





    @Operation(
            summary = "Find Product By Id REST API",
            description = "GET METHOD REST API is used to get a single product from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PutMapping("product/{id}")
    public ResponseEntity<ResponseProductDto> updateById(@RequestBody CreateProductDto createProductDto,
                                         @PathVariable("id") Long id) {



               ResponseProductDto product = productService.updateById(createProductDto, id);
        return new  ResponseEntity<>(product,HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Product REST API",
            description = "Delete Product REST API is used to delete a particular product from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);

        return "Product with id " + id + " was deleted.";
    }

// Exception Handling in POSTMAN
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(ProductNotFoundException  ex) {
        return ex.getMessage();  // You can customize the response body here
    }

}
