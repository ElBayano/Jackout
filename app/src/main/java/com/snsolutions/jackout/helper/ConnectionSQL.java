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
    private static final int DB_VERSION = 3;
    private static final String DB_NAME = "db_jackaut";

    public ConnectionSQL(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    // Cria um metodo que retorna um instancia do banco de dados (Padrão Singleton )
    public static ConnectionSQL getInstance(Context context) {
        if (CONNECTION_INSTANCE == null) {
            CONNECTION_INSTANCE = new ConnectionSQL(context);
        }
        ;
        return CONNECTION_INSTANCE;
    }

    ;


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTableCliente =
                "CREATE TABLE IF NOT EXISTS Cliente (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
                        "nome TEXT NOT NULL, " +
                        "endereco TEXT NOT NULL);";

        String queryCreateTableFormaPagamento =
                "CREATE TABLE IF NOT EXISTS Forma_pagamento (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
                        "descricao TEXT NOT NULL, " +
                        "taxa REAL NOT NULL);";

        String queryCreateTableVendedor =
                "CREATE TABLE IF NOT EXISTS Vendedor (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
                        "nome TEXT NOT NULL, " +
                        "endereco TEXT NOT NULL);";

        String queryCreateTableProdutos =
                "CREATE TABLE IF NOT EXISTS Produto (" +
                        "id INTEGER PRIMARY KEY NOT NULL UNIQUE, " +
                        "nome TEXT NOT NULL, " +
                        "margem_desejada_varejo REAL NOT NULL, " +
                        "margem_desejada_atacado REAL NOT NULL);";

        String queryCreateTableStatusOperacao =
                "CREATE TABLE IF NOT EXISTS Status_Operacao (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
                        "descricao TEXT NOT NULL);";

        String queryCreateTableVenda =
                "CREATE TABLE IF NOT EXISTS Venda (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
                        "data_hora TIMESTAMP NOT NULL, " +
                        "valor_total REAL NOT NULL, " +
                        "cliente_id INTEGER NOT NULL, " +
                        "vendedor INTEGER NOT NULL, " +
                        "forma_pagamento INTEGER NOT NULL, " +
                        "status_operacao_id INTEGER NOT NULL, " +
                        "FOREIGN KEY (cliente_id) REFERENCES Cliente(id), " +
                        "FOREIGN KEY (vendedor) REFERENCES Vendedor(id), " +
                        "FOREIGN KEY (forma_pagamento) REFERENCES Forma_pagamento(id), " +
                        "FOREIGN KEY (status_operacao_id) REFERENCES Status_Operacao(id));";

        String queryCreateTableVendaProduto =
                "CREATE TABLE IF NOT EXISTS Venda_Produto (" +
                        "venda_id INTEGER NOT NULL, " +
                        "produto_id INTEGER NOT NULL, " +
                        "quantidade REAL CHECK (quantidade > 0) NOT NULL, " +
                        "valor_total REAL NOT NULL, " +
                        "PRIMARY KEY (produto_id, venda_id), " +
                        "FOREIGN KEY (produto_id) REFERENCES Produto(id), " +
                        "FOREIGN KEY (venda_id) REFERENCES Venda(id));";

        String queryCreateTableEntrada =
                "CREATE TABLE IF NOT EXISTS Entrada (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
                        "nota_fisacal INTEGER NOT NULL, " +
                        "data_hora TIMESTAMP NOT NULL, " +
                        "produto INTEGER NOT NULL, " +
                        "quantidade REAL NOT NULL, " +
                        "fornecedor TEXT NOT NULL, " +
                        "custo_unitario REAL NOT NULL, " +
                        "custo_total REAL NOT NULL, " +
                        "status_operacao_id INTEGER NOT NULL, " +
                        "FOREIGN KEY (produto) REFERENCES Produto(id), " +
                        "FOREIGN KEY (status_operacao_id) REFERENCES Status_Operacao(id));";

        String queryCreateTableEstoque =
                "CREATE TABLE IF NOT EXISTS Estoque (" +
                        "produto_id INTEGER PRIMARY KEY NOT NULL, " +
                        "quantidade_atual INTEGER CHECK (quantidade_atual >= 0) NOT NULL, " +
                        "estoque_min REAL NOT NULL DEFAULT 0, " +
                        "estoque_max REAL NOT NULL DEFAULT 0, " +
                        "preco_varejo REAL NOT NULL DEFAULT 0, " +
                        "preco_atacado REAL NOT NULL DEFAULT 0, " +
                        "custo_total REAL NOT NULL DEFAULT 0," +
                        "FOREIGN KEY (produto_id) REFERENCES Produto(id));";

        String queryCreateTrgEntradaInserida =
                "CREATE TRIGGER IF NOT EXISTS trg_entrada_insere_estoque " +
                        "AFTER INSERT ON Entrada " +
                        "WHEN NOT EXISTS (SELECT 1 FROM Estoque WHERE produto_id = NEW.produto) " +
                        "BEGIN " +
                        "    INSERT INTO Estoque (produto_id, quantidade_atual, estoque_min, estoque_max, preco_varejo, preco_atacado) " +
                        "    VALUES (NEW.produto, 1, 0, 0, 0, 0); " +
                        "END;";

        String queryCreateTrgEntradaInseridaAtualizar =
                "CREATE TRIGGER IF NOT EXISTS trg_entrada_atualiza_estoque " +
                        "AFTER INSERT ON Entrada " +
                        "WHEN EXISTS (SELECT 1 FROM Estoque WHERE produto_id = NEW.produto) " +
                        "BEGIN " +
                        "    UPDATE Estoque " +
                        "    SET quantidade_atual = quantidade_atual + NEW.quantidade " +
                        "    WHERE produto_id = NEW.produto; " +
                        "END;";

        String queryCreateTrgProdutoVendaInserida =
                "CREATE TRIGGER IF NOT EXISTS trg_venda_produto_inserida " +
                        "AFTER INSERT ON Venda_Produto " +
                        "BEGIN " +
                        "    UPDATE Estoque " +
                        "    SET quantidade_atual = quantidade_atual - NEW.quantidade " +
                        "    WHERE produto_id = NEW.produto_id; " +
                        "END;";

        db.execSQL(queryCreateTableCliente);
        db.execSQL(queryCreateTableFormaPagamento);
        db.execSQL(queryCreateTableVendedor);
        db.execSQL(queryCreateTableProdutos);
        db.execSQL(queryCreateTableStatusOperacao);
        db.execSQL(queryCreateTableVenda);
        db.execSQL(queryCreateTableVendaProduto);
        db.execSQL(queryCreateTableEntrada);
        db.execSQL(queryCreateTableEstoque);

        db.execSQL(queryCreateTrgEntradaInserida);
        db.execSQL(queryCreateTrgEntradaInseridaAtualizar);
        db.execSQL(queryCreateTrgProdutoVendaInserida);

        Log.i("SQLe", "Query executada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Cliente");
            db.execSQL("DROP TABLE IF EXISTS Forma_pagamento");
            db.execSQL("DROP TABLE IF EXISTS Vendedor");
            db.execSQL("DROP TABLE IF EXISTS Produto");
            db.execSQL("DROP TABLE IF EXISTS Status_Operacao");
            db.execSQL("DROP TABLE IF EXISTS Venda");
            db.execSQL("DROP TABLE IF EXISTS Venda_Produto");
            db.execSQL("DROP TABLE IF EXISTS Entrada");
            db.execSQL("DROP TABLE IF EXISTS Estoque");

            onCreate(db);

        Log.i("Teste", "onUPGRADE executada");

    }
}
