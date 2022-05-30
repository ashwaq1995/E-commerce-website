package com.demo.Project2.Service;

import com.demo.Project2.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ArrayList<Product> products = new ArrayList();

    public ArrayList<Product> getProducts() {
        return products;
    }

    public boolean addProduct(Product product) {
        return products.add(product);
    }

    public boolean updateProduct(Integer index, Product product) {
        products.set(index, product);
        return true;
    }


    public boolean removeProduct(Integer index) {
        products.remove(index);
        return true;
    }


    public Product getProduct(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID().equals(id))
                products.get(i);
        }
        return null;
    }

    public ArrayList<Product> getRateFive() {
        ArrayList<Product> rate5 = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Product pro = products.get(i);
            if (pro.isRatedFive()) {
                rate5.add(pro);
            }
        }
        return rate5;
    }
}
