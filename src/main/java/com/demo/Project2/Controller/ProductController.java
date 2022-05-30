package com.demo.Project2.Controller;

import com.demo.Project2.Model.Api;
import com.demo.Project2.Model.Comment;

import com.demo.Project2.Model.Product;
import com.demo.Project2.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ArrayList<Product>> getProducts() {
        return ResponseEntity.status(200).body(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<Api> addProduct(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isProductAdded = productService.addProduct(product);
        if (!isProductAdded) {
            return ResponseEntity.status(500).body(new Api("Error Product is Invalid Added", 400));
        }
        return ResponseEntity.status(200).body(new Api("Product is Added", 201));
    }


    @PutMapping("/{index}")
    public ResponseEntity<Api> updateProduct(@PathVariable Integer index, @RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isProductEdit = productService.updateProduct(index, product);
        if (!isProductEdit) {
            return ResponseEntity.status(500).body(new Api("Error, Product is Invalid Edited", 400));
        }
        return ResponseEntity.status(200).body(new Api("Product Updated", 201));
    }

    //Create endpoint where user can post comment on product
    @GetMapping("/{productid}/comments")
    public ResponseEntity<List<Comment>> getProductComment(@PathVariable String id){
        Product product = this.productService.getProduct(id);

        if(product==null){
            return ResponseEntity.status(500).body(new ArrayList<>());
        }
        return ResponseEntity.status(200).body(product.getComment());
    }


    @DeleteMapping("/remove")
    public ResponseEntity<Api> removeProduct(@PathVariable Integer index) {
        boolean isProductDeleted = productService.removeProduct(index);
        if (!isProductDeleted) {
            return ResponseEntity.status(500).body(new Api("Error Product is Invalid Deleted", 400));
        }
        return ResponseEntity.status(200).body(new Api("Deleted!", 200));
    }

    //Create endpoint where user can get all the rate 5 products
    @GetMapping("/rated5")
    public ResponseEntity<List<Product>> getRateFiveProduct(){
        ArrayList<Product> products = this.productService.getRateFive();

        return ResponseEntity.status(200).body(products);
    }


}
