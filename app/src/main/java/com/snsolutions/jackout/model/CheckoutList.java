package com.snsolutions.jackout.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.snsolutions.jackout.adapter.ProductAdapter;
import com.snsolutions.jackout.databinding.CheckoutListBinding;
import com.snsolutions.jackout.helper.DBHelper;

import java.util.ArrayList;

public class CheckoutList extends Fragment {
    private CheckoutListBinding binding;
    private ProductAdapter productAdapter;
    private final ArrayList<com.snsolutions.jackout.model.Product> productsList = new ArrayList<>();
    private final DBHelper db = new DBHelper();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CheckoutListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productsList.addAll(db.getProductArrayList());

        productAdapter = new ProductAdapter(productsList, requireContext());

        binding.recyclerViewProducts.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerViewProducts.setHasFixedSize(true);
        binding.recyclerViewProducts.setAdapter(productAdapter);

        productAdapter.notifyDataSetChanged();
    }
}