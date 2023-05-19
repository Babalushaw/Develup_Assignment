/**
 * @Copyright develUp
 *
 *
 * @author Babalu kumar
 * @since May 2023
 */
// Implementation class
package com.shop.develup.controller;

import com.shop.develup.model.Product;
import com.shop.develup.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * Adding product details into database
     * @return list of all product from database
     */
    @GetMapping("/add")
    public ResponseEntity<Object> getAllProductList(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProductList());
    }

    /**
     * API 1:
     * Endpoint: /products/top_selling
     * Descrip@on: This API should return the top 10 items based on the quan@ty that has been sold.
     * @return list of product
     */
    @GetMapping("/top_selling")
    public ResponseEntity<Object> topSelling(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.topSelling());
    }

    /**
     *API 2:
     * Endpoint: /products/product_details
     * Descrip@on:Low Stock: Return the count of items that have stock less than the threshold of 100 quan@ty.
     * All Items: Return the count of all the items currently present in stock.
     * @return count of product_category, item and product remainingQuantity lesser than 100
     */
    @GetMapping("/product_details")
    public ResponseEntity<Object> productDetails(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.productDetails());
    }

    /**
     * API 3:
     * Endpoint: /products/update
     * Descrip@on: This API allows the user to update or delete the following details of an item:
     * name, quan@ty, remaining quan@ty, and price.
     * @param product
     * @return updated product
     */
    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(product));
    }
}
