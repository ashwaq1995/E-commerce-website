package com.demo.Project2.Model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
public class Cart {
    @NotEmpty(message = "Please provide ID")
    @Size(min = 3, message = "ID have to be 3 character long")
    private String cartID;

    @NotEmpty(message = "userID is required")
    @Size(min = 3, message = "userID have to be 3 character long")
    private String userID;

    @NotEmpty(message = "product is required")
    private ArrayList<Product> productList;

    public Cart(int i, Integer userid, Object o){
        this.productList = new ArrayList<>();
    }

    public Cart(String cartID, String userID) {
        this.cartID = cartID;
        this.userID = userID;
        this.productList = new ArrayList<>();
    }


    public Integer getTotalCart(){
        Integer total  = 0;
        for(int i = 0; i < productList.size(); i++)
            total += getProductList().get(i).getPrice();
        return total;
    }

}
