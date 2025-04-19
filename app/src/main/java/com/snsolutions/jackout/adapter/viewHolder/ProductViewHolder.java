package com.snsolutions.jackout.adapter.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.snsolutions.jackout.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public TextView item;
    public TextView id;
    public TextView description;
    public TextView quantity;
    public TextView price;
    public TextView total;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        item = itemView.findViewById(R.id.item);
        id = itemView.findViewById(R.id.codigo);
        description = itemView.findViewById(R.id.descricao);
        quantity = itemView.findViewById(R.id.quantidade);
        price = itemView.findViewById(R.id.valor);
        total = itemView.findViewById(R.id.valor_total);
    }
}