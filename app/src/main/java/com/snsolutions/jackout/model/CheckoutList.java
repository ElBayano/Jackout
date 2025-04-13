package com.snsolutions.jackout.model;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.snsolutions.jackout.R;
import com.snsolutions.jackout.adapter.ProductAdapter;
import com.snsolutions.jackout.databinding.CheckoutListBinding;

import com.snsolutions.jackout.helper.DBHelper;

import java.util.ArrayList;

public class CheckoutList extends AppCompatActivity {
    private CheckoutListBinding binding;
    private ProductAdapter productAdapter;
    final ArrayList<Product> productsList = new ArrayList<>();
    final DBHelper db = new DBHelper();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CheckoutListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        productsList.addAll(db.getProductArrayList());

        productAdapter = new ProductAdapter(productsList, CheckoutList.this);

        RecyclerView recyclerViewProducts = binding.recyclerViewProducts;
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(CheckoutList.this));
        recyclerViewProducts.setHasFixedSize(true);
        recyclerViewProducts.setAdapter(productAdapter);

        productAdapter.notifyDataSetChanged();
    }
}
