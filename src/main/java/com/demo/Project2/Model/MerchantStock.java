package com.demo.Project2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "Please provide ID")
    @Size(min = 3, message = "MerchantStockID have to be 3 character long")
    private String MerchantStockID;

    @NotEmpty(message = "Please provide a ProductID")
    @Size(min = 3, message = "ID have to be 3 character long")
    private String ProductID;

    @NotEmpty(message = "Please provide a MerchantID")
    @Size(min = 3, message = "ID have to be 3 character long")
    private String MerchantID;

    @NotEmpty(message = "Stock required")
    @Size(min = 10, message = "stock have to be more than 10 at start")
    private String stock;

    public MerchantStock(int i, String userid, String prodctid, String merchantid) {
    }
}
