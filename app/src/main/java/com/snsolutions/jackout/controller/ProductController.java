package com.snsolutions.jackout.controller;

import com.snsolutions.jackout.DAO.ProductDAO;
import com.snsolutions.jackout.helper.ConnectionSQL;
import com.snsolutions.jackout.model.Product;

import java.util.ArrayList;

public class ProductController {
    private final ProductDAO productDAO;

    public ProductController(ConnectionSQL connectionSQL) {
        productDAO = new ProductDAO(connectionSQL);
    }


    public int salvarProdutoController(Product produto) {
        return this.productDAO.salvarProduto(produto);
    }

    public ArrayList<Product> getListaProdutosController (){
        return  this.productDAO.getListaProdutosDAO();
    };
}

