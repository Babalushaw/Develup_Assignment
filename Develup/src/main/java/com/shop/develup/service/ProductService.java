/**
 * @Copyright develUp
 *
 *
 * @author Babalu kumar
 * @since May 2023
 */
package com.shop.develup.service;

import com.shop.develup.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Object getAllProductList();

    Object productDetails();

    Object topSelling();

    Object update(Product product);
}
