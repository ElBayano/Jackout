package com.snsolutions.jackout.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.snsolutions.jackout.R;
import com.snsolutions.jackout.model.Product;

import java.util.ArrayList;

public class ProductListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> productArrayList;

    public ProductListAdapter(Context context, ArrayList<Product> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }

    public void removerProduto (int position) {
        this.productArrayList.remove(position);
        notifyDataSetChanged();
    }

    public void atualizarLista() {
        Log.i("debugar", "atualizarLista executado : ProoductListAdapter ");
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return productArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return productArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.layout_product_list, null);
        TextView text_view_nome_produto = v.findViewById(R.id.text_view_nome_produto);
        TextView text_view_nome_id = v.findViewById(R.id.text_view_id);
        TextView text_view_nome_estoque = v.findViewById(R.id.text_view_estoque);

        text_view_nome_id.setText(String.valueOf(productArrayList.get(position).getId()));
        text_view_nome_produto.setText(this.productArrayList.get(position).getDescription());

// TODO Confiigurar query para buscar estoque do peoduto no banco de dados
        text_view_nome_estoque.setText("EDITAR DEPOIS");

        return v;
    }
}
