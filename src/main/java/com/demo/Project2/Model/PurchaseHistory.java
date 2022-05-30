package com.demo.Project2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class PurchaseHistory {
    @NotEmpty(message = "id is required")
    @Size(min = 3, message = "ID have to be 3 character long")
    private String id;

    @NotEmpty(message = "userID is required")
    @Size(min = 3, message = "userID have to be 3 character long")
    private String userID;

    @NotEmpty(message = "Please provide a ProductID")
    @Size(min = 3, message = "ProductID have to be 3 character long")
    private String ProductID;

    @NotNull(message = "price is required")
    @Positive(message = "price must be positive number")
    private Integer price;

}
