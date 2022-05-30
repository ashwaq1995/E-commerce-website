package com.demo.Project2.Controller;

import com.demo.Project2.Model.Api;
import com.demo.Project2.Model.Cart;
import com.demo.Project2.Model.Product;
import com.demo.Project2.Model.User;
import com.demo.Project2.Service.CartService;

import com.demo.Project2.Service.ProductService;
import com.demo.Project2.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ArrayList<Cart>> getCarts() {
        return ResponseEntity.status(200).body(cartService.getCarts());
    }


    @PostMapping("/add")
    public ResponseEntity<Api> addToCart(@RequestBody @Valid Cart cart, Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isAddedToCart = cartService.addToCart(cart);
        if(!isAddedToCart){
            return ResponseEntity.status(500).body(new Api("Error while adding new  product to cart",500));
        }
        return ResponseEntity.status(200).body(new Api("Product added into cart successfully",200));
    }


    @DeleteMapping("/delete/{index}")
    public ResponseEntity<Api> deleteCart(@PathVariable Integer index){
        boolean isUserDelete= cartService.deleteCart(index);
        if (!isUserDelete) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry not deleted", 400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api(" Deleted!",200));

    }

    //Create endpoint where user can add product to a cart.
    //this endpoint should accept a user id and product id
    // both should be valid or return bad request

    @RequestMapping(value ="/add/{userid}/{productid}")
    @ResponseBody
    @GetMapping
    public ResponseEntity<String> AddProductToCart(@PathVariable Integer userid,@PathVariable String productid){
        Integer AddProductCase = cartService.AddProductToCart(userid,productid);
        switch (AddProductCase){
            case -1:
                return ResponseEntity.status(400).body("User id or product id is wrong");
            case 0:
                return ResponseEntity.status(400).body("Product and user not available");
            case 1:
                return ResponseEntity.status(200).body("Add Product and user for Cart ");
            default:
                return ResponseEntity.status(500).body("Server error !");
        }
    }

    //Create endpoint where user can remove product from a cart
    //this endpoint should accept a user id and product id
    // both should be valid or return bad request


    @RequestMapping(value ="/remove/{userid}/{productid}")
    @ResponseBody
    @GetMapping
    public ResponseEntity<String> RemoveProductFromCart(@PathVariable Integer userid,@PathVariable String productid){
        Integer removeProductCase = cartService.RemoveProductFromCart(userid,productid);
        switch (removeProductCase){
            case -1:
                return ResponseEntity.status(400).body("User id or product id is wrong");
            case 0:
                return ResponseEntity.status(400).body("Product and user not available");
            case 1:
                return ResponseEntity.status(200).body("Remove Product and user for Cart ");
            default:
                return ResponseEntity.status(500).body("Server error !");
        }
    }

}
