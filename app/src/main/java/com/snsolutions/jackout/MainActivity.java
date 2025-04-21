package com.snsolutions.jackout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.snsolutions.jackout.activity.ProductActivity;
import com.snsolutions.jackout.controller.ProductController;
import com.snsolutions.jackout.helper.ConnectionSQL;
import com.snsolutions.jackout.model.Product;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ConnectionSQL connectionSQL  = ConnectionSQL.getInstance(this);
        Product produto = new Product(11.50, "Antiferrugem", 3880, 30,10);
        ProductController productController = new ProductController(connectionSQL);



        Button btn_nova_venda = findViewById(R.id.menu_btn_novaVenda);
        btn_nova_venda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productController.salvarProdutoController(produto);
                Log.i("SQLe", "Controller Executado no onclick (MainActivity)");
                Intent intent = new Intent(MainActivity.this, CheckoutListActivity.class);
                startActivity(intent);
            }
        });

        Button btn_produtos = findViewById(R.id.menu_btn_produtos);
        btn_produtos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Listener", "Executada a activity produto");
                Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });


    }
}