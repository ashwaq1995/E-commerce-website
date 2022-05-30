package com.demo.Project2.Service;

import com.demo.Project2.Model.PurchaseHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryService {
    private ArrayList<PurchaseHistory> purchaseHistories = new ArrayList();

    public ArrayList<PurchaseHistory> getAllPurchaseHistories() {
        return purchaseHistories;
    }

}
