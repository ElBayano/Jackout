package com.snsolutions.jackout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.snsolutions.jackout.R;
import com.snsolutions.jackout.adapter.ProductListAdapter;
import com.snsolutions.jackout.controller.ProductController;
import com.snsolutions.jackout.helper.ConnectionSQL;
import com.snsolutions.jackout.helper.DBHelper;
import com.snsolutions.jackout.model.Product;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    private ListView listViewProdutos;
    private ArrayList<Product> productArrayList;
    private ProductListAdapter productListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        this.listViewProdutos = (ListView) findViewById(R.id.list_view_produtos);
        this.productArrayList = new ArrayList<>();


        ProductController productController = new ProductController(ConnectionSQL.getInstance( ProductListActivity.this));
        this.productArrayList = productController.getListaProdutosController();

        this.productListAdapter = new ProductListAdapter(ProductListActivity.this, productArrayList);
        this.listViewProdutos.setAdapter(productListAdapter);




        Button produto_btn_cadastrar_novo = findViewById(R.id.produto_btn_cadastrar_novo);
        produto_btn_cadastrar_novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this, NewProductActivity.class);
                startActivity(intent);
            }
        });

        Button produto_btn_cadastrar_entrada = findViewById(R.id.produto_btn_cadastrar_entrada);
        produto_btn_cadastrar_entrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this, NewEntry.class);
                startActivity(intent);
            }
        });





    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("debugar", "onStart executado : ProductListActivity");

        ProductController productController = new ProductController(ConnectionSQL.getInstance(ProductListActivity.this));
        this.productArrayList = productController.getListaProdutosController();
        this.productListAdapter.atualizarLista(this.productArrayList);
    }
}