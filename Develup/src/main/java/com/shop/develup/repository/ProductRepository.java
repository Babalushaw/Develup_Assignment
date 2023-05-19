/**
 * @Copyright develUp
 *
 *
 * @author Babalu kumar
 * @since May 2023
 */
package com.shop.develup.repository;

import com.shop.develup.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
