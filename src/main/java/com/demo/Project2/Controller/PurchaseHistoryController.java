package com.demo.Project2.Controller;

import com.demo.Project2.Model.PurchaseHistory;
import com.demo.Project2.Service.PurchaseHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping("api/v1/purchaseHistories")
@RequiredArgsConstructor
public class PurchaseHistoryController {
    private final PurchaseHistoryService purchaseHistoryService;

    //Create endpoint where user can get all PurchaseHistory
    @GetMapping
    public ResponseEntity<ArrayList<PurchaseHistory>> getAllPurchaseHistories() {
        return ResponseEntity.status(200).body(purchaseHistoryService.getAllPurchaseHistories());
    }

}
