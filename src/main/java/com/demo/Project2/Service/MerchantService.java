package com.demo.Project2.Service;

import com.demo.Project2.Model.Merchant;
import com.demo.Project2.Model.MerchantStock;
import com.demo.Project2.Model.Product;
import com.demo.Project2.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private ArrayList<Merchant> merchants = new ArrayList();

    private final UserService userService;
    private final ProductService productService;

    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }

    public boolean addMerchant(Merchant merchant) {
        return merchants.add(merchant);
    }

    public boolean updateMerchant(Integer index, Merchant merchant) {
        if (index >= merchants.size() || index < 0) {
            return false;
        }
        merchants.set(index, merchant);
        return true;
    }

    public boolean removeMerchant(String id) {
        Integer currentMerchant = getMerchant(id);
        merchants.remove((int)currentMerchant);
        return true;
    }

    public Integer getMerchant(String id){
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getMerchantID().equals(id)){
                return i;
            }
        }
        return null;
    }


//    public Integer buyProduct(String userid, String productid, String merchantid) {
//        User user = userService.getUser(userid);
//        Product product = productService.getProduct(productid);
//        Merchant merchant = getMerchant(merchantid);
//
//        if(product == null || user == null){
//            return -1;
//        }
//
//        if(user.getBalance() < product.getPrice()){
//            return 0;
//        }
//
//        if(user.getStock() == 0){
//            return 1;
//        }
//
//        Integer oldStock = product.getStock();
//        product.setStock(oldStock-1);
//
//        Integer oldBalance = user.getBalance();
//        user.setBalance(oldBalance-product.getPrice());
//
//        merchant.add(product);
//
//        return 2;
//    }

}
