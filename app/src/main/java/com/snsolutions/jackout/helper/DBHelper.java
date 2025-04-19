package com.snsolutions.jackout.helper;

import com.snsolutions.jackout.model.Product;

import java.util.ArrayList;

public class DBHelper {
    public  ArrayList<Product> productArrayList = new ArrayList<>();

    public DBHelper() {
        this.productArrayList.add(new Product(10, "Vassoura", 103, 30));
        this.productArrayList.add(new Product(10, "Vassoura", 103, 30));
        this.productArrayList.add(new Product(9, "Pepino", 104, 30));
        this.productArrayList.add(new Product(9, "Pepino", 104, 30));
        this.productArrayList.add(new Product(30, "Tesoura", 105, 30));
        this.productArrayList.add(new Product(30, "Tesoura", 105, 30));
    };

    public Product getProductById(int id){
        for (Product item  : this.productArrayList) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    };

    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }
}
