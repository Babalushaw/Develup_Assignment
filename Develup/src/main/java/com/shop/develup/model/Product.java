/**
 * @Copyright develUp
 *
 *
 * @author Babalu kumar
 * @since May 2023
 */

package com.shop.develup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private Long barcode;
    private String itemDescription;
    private String groupName;
    private String size;
    private Float quantity;
    private Float price;
    private Float profit;
    private Float remainingQuantity;


}
