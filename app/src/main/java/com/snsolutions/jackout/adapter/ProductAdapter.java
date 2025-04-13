package com.snsolutions.jackout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.snsolutions.jackout.databinding.ProductBinding;
import com.snsolutions.jackout.model.Product;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final ArrayList<Product> productsList;
    private final Context context;

    public ProductAdapter(ArrayList<Product> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }


    @NonNull
    @Override
    public @NotNull ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int i) {
        ProductBinding productItem;
        productItem = ProductBinding.inflate(LayoutInflater.from(context), parent, false);


        return new ProductViewHolder(productItem);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductAdapter.ProductViewHolder productViewHolder, int i) {
        double price  = productsList.get(i).getCost()/(1- (double) productsList.get(i).getProfit() /100);
        int quantity = 0;
        for (Product item : productsList){
            if (item.getId() == productsList.get(i).getId()){
                quantity++;
            }
        }


        productViewHolder.binding.codigo.setText(String.valueOf(productsList.get(i).getId()));
        productViewHolder.binding.descricao.setText(productsList.get(i).getDescription());
        productViewHolder.binding.item.setText(String.valueOf(i + 1));
        productViewHolder.binding.valor.setText(String.valueOf(price));
        productViewHolder.binding.valorTotal.setText(String.valueOf((price * 3)));
        productViewHolder.binding.quantidade.setText(String.valueOf(quantity));
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ProductBinding binding;
        public ProductViewHolder(ProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
