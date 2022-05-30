package com.demo.Project2.Controller;

import com.demo.Project2.Model.Api;
import com.demo.Project2.Model.Merchant;
import com.demo.Project2.Service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping
    public ResponseEntity<ArrayList<Merchant>> getMerchants() {
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }

    @PostMapping
    public ResponseEntity<Api> addMerchant(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isMerchantAdded = merchantService.addMerchant(merchant);
        if (!isMerchantAdded) {
            return ResponseEntity.status(500).body(new Api("Error Merchant is Invalid Added", 400));
        }
        return ResponseEntity.status(201).body(new Api("Merchant is Added", 201));
    }

    @PutMapping("/{index}")
    public ResponseEntity<Api> updateMerchant(@PathVariable Integer index, @RequestBody Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isMerchantEdit = merchantService.updateMerchant(index, merchant);
        if (!isMerchantEdit) {
            return ResponseEntity.status(500).body(new Api("Error Merchant is Invalid Edited", 400));
        }
        return ResponseEntity.status(201).body(new Api("Merchant is Updated", 201));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Api> removeMerchant(@RequestParam String id) {
        boolean isMerchantDeleted = merchantService.removeMerchant(id);
        if (!isMerchantDeleted) {
            return ResponseEntity.status(500).body(new Api("Error Merchant is Invalid Deleted", 400));
        }
        return ResponseEntity.status(200).body(new Api("Deleted!", 200));
    }


//    @PostMapping("/buy/{product}")
//    public ResponseEntity<String> buyProduct(@RequestParam String userid,@RequestParam String productid,@RequestParam String merchantid){
//        Integer carCase = merchantService.buyProduct(userid,productid,merchantid);
//        switch (carCase){
//            case -1:
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User id or card id or merchant id is wrong");
//            case 0:
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You don't have enough money for the product");
//            case 1:
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no product available to buy");
//            case 2:
//                return ResponseEntity.status(HttpStatus.OK).body("Product purchased !");
//            default:
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error !");
//        }
//    }
}
