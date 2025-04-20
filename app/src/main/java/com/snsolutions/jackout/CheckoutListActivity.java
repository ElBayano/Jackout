package com.snsolutions.jackout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.snsolutions.jackout.adapter.ProductAdapter;
import com.snsolutions.jackout.helper.DBHelper;
import com.snsolutions.jackout.model.Product;

import java.util.ArrayList;

public class CheckoutListActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private ProductAdapter adapter;
    private ArrayList<Product> products;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_list);

        DBHelper hd = new DBHelper();
        products = new ArrayList<Product>();
        products.add(hd.getProductById(103));
        products.add(hd.getProductById(103));
        products.add(hd.getProductById(104));
        products.add(hd.getProductById(104));
        products.add(hd.getProductById(105));
        products.add(hd.getProductById(105));


        recycler = findViewById(R.id.recyler_view_checkout);

        adapter = new ProductAdapter(CheckoutListActivity.this, products);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                CheckoutListActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }


}
