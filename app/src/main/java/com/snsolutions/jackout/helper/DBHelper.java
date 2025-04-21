package com.snsolutions.jackout.helper;

import com.snsolutions.jackout.model.Product;

import java.util.ArrayList;

public class DBHelper {
    public  ArrayList<Product> productArrayList = new ArrayList<>();

    public DBHelper() {
        this.productArrayList.add(new Product(10.10, "Vassoura", 103, 30, 10));
        this.productArrayList.add(new Product(10.10, "Vassoura", 103, 30,10));
        this.productArrayList.add(new Product(9.95, "Pepino", 104, 30,10));
        this.productArrayList.add(new Product(9.95, "Pepino", 104, 30,10));
        this.productArrayList.add(new Product(30.31, "Tesoura", 105, 30,10));
        this.productArrayList.add(new Product(30.31, "Tesoura", 105, 30,10));
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
