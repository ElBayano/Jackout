package com.snsolutions.jackout.model;

import java.io.Serializable;

public class Product implements Serializable {
    double cost;
    String description;
    int id;
    double profit;

    public Product(double cost, String description, Integer id, double profit)  {
        this.cost = cost;
        this.description = description;
        this.id = id;
        this.profit = profit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
