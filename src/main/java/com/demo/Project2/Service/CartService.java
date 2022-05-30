package com.demo.Project2.Service;

import com.demo.Project2.Model.Cart;

import com.demo.Project2.Model.Product;
import com.demo.Project2.Model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {

    private ArrayList<Cart> carts = new ArrayList();
    private final UserService userService;
    private final ProductService productService;

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public boolean addToCart(Cart cartProduct) {
        return carts.add(cartProduct);
    }

    public boolean deleteCart(Integer index) {
        carts.remove(index);
        return true;
    }
    

    public Integer AddProductToCart(Integer userid, String productid) {
        User user = userService.getUser(userid);
        Product product = productService.getProduct(productid);
        if(user == null || product == null) {
            return -1;
        }
        boolean IsFound = false;
        for (Cart pro:carts) {
            if(pro.getUserID().equals(userid)) {
                for (Product prod:pro.getProductList()) {
                    if(prod.getProductID().equals(productid)) {
                        IsFound = true;
                        break;
                    }
                }
            } if (IsFound)
                break;
        }
        Cart cart = new Cart(carts.size() + 1, userid, null);

        cart.getProductList().add(product);
        carts.add(cart);
        return 1;
    }


    public Integer RemoveProductFromCart(Integer userid, String productid) {
        User user = userService.getUser(userid);
        Product product = productService.getProduct(productid);
        if (user == null || product == null) {
            return -1;
        }
        boolean IsFound = false;
        for (Cart pro : carts) {
            if (pro.getUserID().equals(userid)) {
                int index = 0;
                if (pro.getProductList() != null) {
                    for (Product prod : pro.getProductList()) {
                        if (prod.getProductID().equals(productid)) {
                            IsFound = true;
                            pro.getProductList().remove(index);
                            break;
                        }
                        index++;
                    }
                }
            }
            if (IsFound)
                break;
        }
        if (IsFound) {
            return 1;
        } else {
            return 0;
        }
    }
}
