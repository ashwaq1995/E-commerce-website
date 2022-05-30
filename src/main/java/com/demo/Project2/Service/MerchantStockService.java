package com.demo.Project2.Service;

import com.demo.Project2.Model.MerchantStock;
import com.demo.Project2.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private ArrayList<MerchantStock> merchantStocks = new ArrayList();

    private final ProductService productService;
    private final MerchantService merchantService;


    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public boolean addMerchant(MerchantStock merchantStock) {
        return merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(Integer index, MerchantStock merchantStock) {
        if (index >= merchantStocks.size() || index < 0) {
            return false;
        }
        merchantStocks.set(index, merchantStock);
        return true;
    }

    public boolean removeMerchantStock(String id) {
        Integer currentMerchantStock = getMerchantStock(id);
        merchantStocks.remove((int)currentMerchantStock);
        return true;
    }

    public Integer getMerchantStock(String id){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantStockID().equals(id)){
                return i;
            }
        }
        return null;
    }

    public Integer addStock(String userid, String prodctid, String merchantid) {
        Integer merchant = merchantService.getMerchant(merchantid);
        Product pro = productService.getProduct(prodctid);
        if(merchant ==null || pro ==null) {
            return -1;
        }

        MerchantStock m = new MerchantStock(merchantStocks.size()+1,userid, prodctid, merchantid);
        merchantStocks.add(m);
        return 1;


    }



}
