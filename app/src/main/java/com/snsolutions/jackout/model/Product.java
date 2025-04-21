package com.snsolutions.jackout.model;

import java.io.Serializable;

public class Product implements Serializable {
    double cost;
    String description;
    int id;
    double profitRetail;
    double profitWholesale;

    public Product(double cost, String description, int id, double profitRetail, double profitWholesale) {
        this.cost = cost;
        this.description = description;
        this.id = id;
        this.profitRetail = profitRetail;
        this.profitWholesale = profitWholesale;
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

    public double getProfitRetail() {
        return profitRetail;
    }

    public void setProfitRetail(Integer profitRetail) {
        this.profitRetail = profitRetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProfitRetail(double profitRetail) {
        this.profitRetail = profitRetail;
    }

    public double getProfitWholesale() {
        return profitWholesale;
    }

    public void setProfitWholesale(double profitWholesale) {
        this.profitWholesale = profitWholesale;
    }

    @Override
    public String toString() {
        return "Product{" +
                "cost=" + cost +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", profit=" + profitRetail +
                '}';
    }
}
