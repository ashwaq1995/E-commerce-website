package com.demo.Project2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
public class Product {
    @NotEmpty(message = "Please provide an ID of product")
    @Size(min = 3, message = "ID have to be 3 character long")
    private String ProductID;

    @NotEmpty(message = "Please provide a name of product")
    @Size(min = 3, message = "have to be 3 length long")
    private String name;

    @NotNull(message = "price is required")
    @Positive(message = "price must be positive number")
    private Integer price;

    @NotEmpty(message = "Please provide a categoryID of product")
    @Size(min = 3, message = "categoryID have to be 3 character long")
    private String categoryID;

    @NotEmpty(message = "comment is required")
    private ArrayList<Comment> comment;

    public Product(String productID, String name, Integer price, String categoryID) {
        ProductID = productID;
        this.name = name;
        this.price = price;
        this.categoryID = categoryID;
        this.comment = new ArrayList<>();
    }

    public boolean isRatedFive() {
        for (int i = 0; i < getComment().size(); i++) {
            if (getComment().get(i).getRate() < 5)
                return false;
        }
        return true;
    }
}
