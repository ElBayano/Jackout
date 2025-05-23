package com.snsolutions.jackout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.snsolutions.jackout.activity.NewProductActivity;
import com.snsolutions.jackout.activity.ProductListActivity;
import com.snsolutions.jackout.controller.ProductController;
import com.snsolutions.jackout.helper.ConnectionSQL;
import com.snsolutions.jackout.model.Product;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recebe uma instancia do BD e cria um controller para acessar os produtos no BD
        ConnectionSQL connectionSQL  = ConnectionSQL.getInstance(this);
        ProductController productController = new ProductController(connectionSQL);



        // Obtém a view do Botão de checkout e cria um lintenner que envia para a activity checkout
        Button btn_nova_venda = findViewById(R.id.menu_btn_novaVenda);
        btn_nova_venda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheckoutListActivity.class);
                startActivity(intent);
            }
        });


        // Obtem a view do botão que envia para a área relacionada a produtos e cria um listener
        Button btn_produtos = findViewById(R.id.menu_btn_produtos);
        btn_produtos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Listener", "Executada a activity produto");
                Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
                startActivity(intent);
            }
        });


    }
}