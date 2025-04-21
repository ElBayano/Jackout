package com.snsolutions.jackout.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ConnectionSQL extends SQLiteOpenHelper {
    private static ConnectionSQL CONNECTION_INSTANCE;
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "db_jackaut";

    public ConnectionSQL(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public static ConnectionSQL getInstance(Context context) {
        if(CONNECTION_INSTANCE == null) {
            CONNECTION_INSTANCE = new ConnectionSQL(context);
        };
        return CONNECTION_INSTANCE;
    };



    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTableCliente = "CREATE TABLE IF NOT EXISTS cliente (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "endereco TEXT" +
                ");";

        String queryCreateTableVendedor = "CREATE TABLE IF NOT EXISTS vendedor (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nome TEXT," +
                        "endereco TEXT);";

        String queryCreateTableFormaPagamento = "CREATE TABLE IF NOT EXISTS forma_pagamento (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT UNIQUE NOT NULL," +
                "taxa DOUBLE);";

        String queryCreateTableProdutos = "CREATE TABLE IF NOT EXISTS produto (" +
                "id INTEGER PRIMARY KEY," +
                "custo DOUBLE CHECK (custo > 0)," +
                "nome TEXT," +
                "margem_desejada_varejo DOUBLE," +
                "margem_desejada_atacado DOUBLE);";

        String queryCreateTableEntrada = "CREATE TABLE IF NOT EXISTS entrada (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nota_fiscal INTEGER," +
                "data_hora TIMESTAMP," +
                "produto INTEGER," +
                "fornecedor TEXT," +
                "FOREIGN KEY (produto) REFERENCES produto(id));";

        String queryCreateTableVenda = "CREATE TABLE IF NOT EXISTS venda (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "data_hora TIMESTAMP," +
                "valor_total DOUBLE CHECK (valor_total > 0)," +
                "cliente INTEGER," +
                "vendedor INTEGER," +
                "forma_pagamento INTEGER," +
                "FOREIGN KEY (cliente) REFERENCES cliente(id)," +
                "FOREIGN KEY (vendedor) REFERENCES vendedor(id)," +
                "FOREIGN KEY (forma_pagamento) REFERENCES forma_pagamento(id));";

        String queryCreateTableVendaProduto = "CREATE TABLE IF NOT EXISTS venda_produto (" +
                "id_venda INTEGER," +
                "id_produto INTEGER," +
                "quantidade INTEGER CHECK (quantidade > 0)," +
                "PRIMARY KEY (id_venda, id_produto)," +
                "FOREIGN KEY (id_venda) REFERENCES venda(id)," +
                "FOREIGN KEY (id_produto) REFERENCES produto(id));";

        String queryCreateTableEstoque = "CREATE TABLE IF NOT EXISTS estoque (" +
                "id_produto INTEGER PRIMARY KEY," +
                "quantidade_atual INTEGER CHECK (quantidade_atual >= 0)," +
                "estoque_min INTEGER," +
                "FOREIGN KEY (id_produto) REFERENCES produto(id));";

        String queryCreateTrgEntradaInserida =
                "CREATE TRIGGER IF NOT EXISTS trg_entrada_insere_estoque\n" +
                        "AFTER INSERT ON entrada\n" +
                        "WHEN NOT EXISTS (SELECT 1 FROM estoque WHERE id_produto = NEW.produto)\n" +
                        "BEGIN\n" +
                        "    INSERT INTO estoque (id_produto, quantidade_atual, estoque_min)\n" +
                        "    VALUES (NEW.produto, 1, 0);\n" +
                        "END;";

        String queryCreateTrgEntradaInseridaAtualizar =
                "CREATE TRIGGER IF NOT EXISTS trg_entrada_atualiza_estoque\n" +
                        "AFTER INSERT ON entrada\n" +
                        "WHEN EXISTS (SELECT 1 FROM estoque WHERE id_produto = NEW.produto)\n" +
                        "BEGIN\n" +
                        "    UPDATE estoque\n" +
                        "    SET quantidade_atual = quantidade_atual + 1\n" +
                        "    WHERE id_produto = NEW.produto;\n" +
                        "END;";


        String queryCreateTrgProdutoVendaInserida = "CREATE TRIGGER IF NOT EXISTS trg_venda_produto_inserida " +
                        "AFTER INSERT ON venda_produto " +
                        "BEGIN " +
                        "    UPDATE estoque " +
                        "    SET quantidade_atual = quantidade_atual - NEW.quantidade " +
                        "    WHERE id_produto = NEW.id_produto; " +
                        "END;";

        db.execSQL(queryCreateTableCliente);
        db.execSQL(queryCreateTableVendedor);
        db.execSQL(queryCreateTableFormaPagamento);
        db.execSQL(queryCreateTableProdutos);
        db.execSQL(queryCreateTableEntrada);
        db.execSQL(queryCreateTableVenda);
        db.execSQL(queryCreateTableVendaProduto);
        db.execSQL(queryCreateTableEstoque);
        db.execSQL(queryCreateTrgEntradaInserida);
        db.execSQL(queryCreateTrgEntradaInseridaAtualizar);
        db.execSQL(queryCreateTrgProdutoVendaInserida);
    Log.i("SQLe", "Query executada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Teste", "onUPGRADE executada");

    }
}
