package com.snsolutions.jackout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.snsolutions.jackout.R;
import com.snsolutions.jackout.adapter.viewHolder.ProductViewHolder;
import com.snsolutions.jackout.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private Context context;
    private ArrayList<Product> itens;

    public ProductAdapter(Context context, ArrayList<Product> itens) {
        this.context = context;
        this.itens = itens;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_product_list_item, parent, false);
        ProductViewHolder viewHolder = new ProductViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product produto = itens.get(position);
        double price =  produto.getCost() + 10;
        double quantity = 1;
        double total = price * quantity;
//
//
        holder.item.setText(String.valueOf(position+1));
        holder.id.setText(String.valueOf(produto.getId()));
        holder.description.setText(produto.getDescription());
        holder.quantity.setText(String.valueOf(quantity));
        holder.price.setText(String.valueOf(price));
        holder.total.setText(String.valueOf(total));
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

};
