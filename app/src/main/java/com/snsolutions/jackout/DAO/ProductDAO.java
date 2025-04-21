package com.snsolutions.jackout.DAO;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.snsolutions.jackout.helper.ConnectionSQL;
import com.snsolutions.jackout.model.Product;

public class ProductDAO {

    private final ConnectionSQL connectionSQL;

    public ProductDAO(ConnectionSQL connectionSQL) {
        this.connectionSQL = connectionSQL;
    }

    public int salvarProduto(Product produto) {
        SQLiteDatabase db =  connectionSQL.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("id", produto.getId());
            values.put("custo", produto.getCost());
            values.put("nome", produto.getDescription());
            values.put("margem_desejada_varejo", produto.getProfitRetail());
            values.put("margem_desejada_atacado", produto.getProfitWholesale());

            int idProduto = (int)db.insert("produto", null, values);
            return idProduto;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    };
}
