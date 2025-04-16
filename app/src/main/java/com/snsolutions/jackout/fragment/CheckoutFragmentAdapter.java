package com.snsolutions.jackout.fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.snsolutions.jackout.helper.DBHelper;
import com.snsolutions.jackout.R;

public class CheckoutFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private DBHelper data = new DBHelper();

    @NonNull
    @Override
    public CheckoutFragmentViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ListItem;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItem = inflater.inflate(R.layout.fragment_product_list_item, parent, false);
        return new CheckoutFragmentViewholder(ListItem) ;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        data.getProductById(1030).get
        return data.getProductArrayList().size();
    }

    public class CheckoutFragmentViewholder extends RecyclerView.ViewHolder {
        private TextView cost;
        private TextView description;
        private TextView quantity;
        private TextView id;


        public CheckoutFragmentViewholder(@NonNull View itemView) {
            super(itemView);
            cost = itemView.findViewById(R.id.valor);
            description = itemView.findViewById(R.id.descricao);
        }
    }
}