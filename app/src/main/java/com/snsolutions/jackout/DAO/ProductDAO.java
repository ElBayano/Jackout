package com.snsolutions.jackout.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.snsolutions.jackout.helper.ConnectionSQL;
import com.snsolutions.jackout.model.Product;

import java.util.ArrayList;

public class ProductDAO {

    private final ConnectionSQL connectionSQL;

    public ProductDAO(ConnectionSQL connectionSQL) {
        this.connectionSQL = connectionSQL;
    }

    public int salvarProduto(Product produto) {
        SQLiteDatabase db = connectionSQL.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("id", produto.getId());
            values.put("nome", produto.getDescription());
            values.put("margem_desejada_varejo", produto.getProfitRetail());
            values.put("margem_desejada_atacado", produto.getProfitWholesale());

            int idProduto = (int) db.insert("produto", null, values);
            return idProduto;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    ;


    public ArrayList<Product> getListaProdutosDAO() {
        SQLiteDatabase db = null;
        ArrayList<Product> productArrayList = new ArrayList<>();
        Cursor cursor;

        String query =
                "SELECT Produto.id, Produto.nome, Produto.margem_desejada_varejo, Produto.margem_desejada_atacado, " +
                        "Estoque.quantidade_atual, Estoque.estoque_min, Estoque.estoque_max, " +
                        "Estoque.preco_varejo, Estoque.preco_atacado, Estoque.custo_total " +
                        "FROM Produto " +
                        "LEFT JOIN Estoque ON Produto.id = Estoque.produto_id";

        try {
            db = connectionSQL.getReadableDatabase();
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                Product produto = null;

                do {
                    produto = new Product();
                    produto.setId(cursor.getInt(0));
                    produto.setDescription(cursor.getString(1));
                    produto.setProfitRetail(cursor.getDouble(2));
                    produto.setProfitWholesale(cursor.getDouble(3));
                    produto.setCost(cursor.getDouble(9));


                    productArrayList.add(produto);


//                            "id INTEGER PRIMARY KEY," +
//                            "custo DOUBLE CHECK (custo > 0)," +
//                            "nome TEXT," +
//                            "margem_desejada_varejo DOUBLE," +
//                            "margem_desejada_atacado DOUBLE);";


                } while (cursor.moveToNext());
            }


        } catch (Exception e) {
            Log.d("debugar ERRO LISTA PRODUTOS", "Erro ao retornar produtos: " + e.getMessage());
            return null;

        } finally {
            if (db != null) {
                db.close();
            }
        }


        return productArrayList;
    }

    ;
}