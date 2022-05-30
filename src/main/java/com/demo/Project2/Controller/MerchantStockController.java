package com.demo.Project2.Controller;

import com.demo.Project2.Model.Api;
import com.demo.Project2.Model.MerchantStock;
import com.demo.Project2.Service.MerchantStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping
    public ResponseEntity<ArrayList<MerchantStock>> getMerchantStocks() {
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }

    @PostMapping
    public ResponseEntity<Api> addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isMerchantStockAdded = merchantStockService.addMerchant(merchantStock);
        if (!isMerchantStockAdded) {
            return ResponseEntity.status(500).body(new Api("Error MerchantStock is Invalid Added", 400));
        }
        return ResponseEntity.status(201).body(new Api("MerchantStock is Added", 200));
    }

    @PutMapping("/{index}")
    public ResponseEntity<Api> updateMerchantStock(@PathVariable Integer index, @RequestBody MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isMerchantEdit = merchantStockService.updateMerchantStock(index, merchantStock);
        if (!isMerchantEdit) {
            return ResponseEntity.status(500).body(new Api("Error MerchantStock is Invalid Edited", 400));
        }
        return ResponseEntity.status(201).body(new Api("MerchantStock is Updated", 200));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Api> removeMerchantStock(@RequestParam String id) {
        boolean isMerchantStockDeleted = merchantStockService.removeMerchantStock(id);
        if (!isMerchantStockDeleted) {
            return ResponseEntity.status(500).body(new Api("Error MerchantStock is Invalid Deleted", 400));
        }
        return ResponseEntity.status(200).body(new Api("Deleted!", 200));
    }


    //Create endpoint where user can add product to a merchantStock
    //this endpoint should accept a userid and merchant id and stock

    @PostMapping("/addProduct/MerchantID")
    public ResponseEntity<String> AddProductToMerchantStock(@RequestParam String prodctid,@PathVariable String userid,@PathVariable String merchantid){
        Integer AddToStockCase = merchantStockService.addStock(prodctid,userid,merchantid);
        switch (AddToStockCase){
            case -1:
                return ResponseEntity.status(400).body("User id or product id is wrong");
            case 0:
                return ResponseEntity.status(400).body("There is no product and user available");
            case 1:
                return ResponseEntity.status(200).body("Car purchased !");
            default:
                return ResponseEntity.status(500).body("Server error !");
        }
    }
}
