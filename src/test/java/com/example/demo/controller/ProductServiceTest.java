package com.example.demo.controller;

import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    void listAll() {
        List<Product> products = productService.listAll();
        assertFalse(products.isEmpty());
    }

    @Test
    void get() {
        Product productSave = new Product(2, "Huawei y7", 72.50f);
        productService.save(productSave);
        Product product = productService.get(2);
        assertEquals(productSave.getName(), product.getName());
        assertEquals(productSave.getId(), product.getId());
        assertEquals(productSave.getPrice(), product.getPrice());
    }

    @Test
    void save() {
        Product product1 = new Product(3, "Asus Laptop", 45.45f);
        productService.save(product1);
        Product productRetrieved = productService.get(3);
        assertEquals(product1.getName(), productRetrieved.getName());
        assertEquals(product1.getId(), productRetrieved.getId());
        assertEquals(product1.getPrice(), productRetrieved.getPrice());
        productService.delete(3);
    }


    @Test
    void delete() {
        Product productSV = new Product(3, "Asus Laptop", 45.45f);
        productService.save(productSV);
        Product productRetrieved = productService.get(3);
        assertEquals(productSV.getName(), productRetrieved.getName());
        assertEquals(productSV.getId(), productRetrieved.getId());
        assertEquals(productSV.getPrice(), productRetrieved.getPrice());
        productService.delete(3);
        assertThrows(NoSuchElementException.class, () -> productService.get(3),
                "Expected doThing() to throw, but it didn't");

    }

}