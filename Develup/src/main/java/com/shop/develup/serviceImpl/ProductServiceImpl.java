/**
 * @Copyright develUp
 *
 *
 * @author Babalu kumar
 * @since May 2023
 */
package com.shop.develup.serviceImpl;

import com.shop.develup.model.Product;
import com.shop.develup.repository.ProductRepository;
import com.shop.develup.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Repository
public class ProductServiceImpl implements ProductService {
    private Logger log= LoggerFactory.getLogger((ProductServiceImpl.class));
    @Autowired
    private ProductRepository productRepository;

    /**
     * adding product to database from csv file
     * @return all product
     */
    @Override
    public Object getAllProductList() {

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\BABALU KUMAR\\Downloads\\Develup\\Assessment Backend.csv"))) {
            String line;
            boolean t=false;
            while ((line = br.readLine()) != null) {
                if(t==false){
                    t=true;
                    continue;
                }
                String[] data = line.split(",");
                Product product=new Product();
                product.setBarcode(Long.parseLong(data[0]));
                product.setItemDescription(data[1]);
                product.setGroupName(data[2]);
                product.setSize(data[3]);
                product.setPrice(Float.parseFloat(data[4]));
                product.setQuantity(Float.parseFloat(data[5]));
                product.setProfit(Float.parseFloat(data[6]));
                product.setRemainingQuantity(Float.parseFloat(data[7]));
                productRepository.save(product);
            }
            return productRepository.findAll();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server not available");
       }
    }

    /**
     *Low Stock: Return the count of items that have stock less than the threshold of 100 quan@ty.
     * All Items: Return the count of all the items currently present in stock.
     * Item Groups: Return the count of categories.
     * @return product_details
     */
    @Override
    public Object productDetails() {
        try{
            HashSet<String> category=new HashSet<>();
            HashSet<String> item=new HashSet<>();
            List<Product> productList=productRepository.findAll();
            Long thresholdCount=productList.stream().filter(product -> Float.compare(product.getRemainingQuantity(),100f)<0).count();
            productList.stream().forEach(product -> {
                category.add(product.getItemDescription());
                item.add(product.getGroupName());
            });
            HashMap<String ,Long> response=new HashMap<>();
            response.put("stock less than the threshold of 100 quan@ty",thresholdCount);
            response.put("count of all the items currently present in stock", (long) item.size());
            response.put("count of categories" , (long) category.size());
            return response.toString();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server not available");
        }
    }

    /**
     *This should return the top 10 items based on the quan@ty that has been sold.
     * @return top 10 productList
     */
    @Override
    public Object topSelling() {
        try{
            //comparator for sort according to unsold quantity then profit
            Comparator<Product> sorter =new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    Float f1=o1.getQuantity()-o1.getRemainingQuantity();
                    Float f2=o2.getQuantity()-o2.getRemainingQuantity();
                    if(Float.compare(f1,f2)==0){
                        return Float.compare(o2.getProfit(), o1.getProfit());
                    }
                    log.info("after if else ");
                    return Float.compare(f2,f1);
                }
            };
            List<Product> productList=productRepository.findAll();
            Collections.sort(productList,sorter);
            List<Product> response=new ArrayList<>();
            // getting top 10 product
            for(int i=0;i<10;i++){
                response.add(productList.get(i));
            }
            return response;
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server not available");
        }
    }

    /**
     *This method allows the user to update or delete the following details of an item:
     * name, quan@ty, remaining quan@ty, and price.
     * @param product
     * @return updated product
     */
    @Override
    public Object update(Product product) {
        try{
            if(product.getBarcode()==null){
                return "barcode not match";
            }
            else if(productRepository.findById(product.getBarcode())==null){
                return "product not found";
            }
            else return productRepository.save(product);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server not available");
        }
    }
}


//C:\Users\BABALU KUMAR\Downloads\Develup