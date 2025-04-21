package com.snsolutions.jackout.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.snsolutions.jackout.MainActivity;
import com.snsolutions.jackout.R;
import com.snsolutions.jackout.controller.ProductController;
import com.snsolutions.jackout.helper.ConnectionSQL;
import com.snsolutions.jackout.model.Product;

public class ProductActivity extends AppCompatActivity {
    private EditText edit_text_id_produto;
    private EditText edit_text_nome_produto;
    private EditText edit_text_custo;
    private EditText edit_text_margem_varejo;
    private EditText edit_text_margem_atacado;

    private Button produto_btn_cancelar;
    private Button produto_btn_salvar;

    private Product produto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);


        this.edit_text_id_produto = findViewById(R.id.edit_text_id_produto);
        this.edit_text_nome_produto = findViewById(R.id.edit_text_nome_produto);
        this.edit_text_custo = findViewById(R.id.edit_text_custo);
        this.edit_text_margem_varejo = findViewById(R.id.edit_text_margem_varejo);
        this.edit_text_margem_atacado = findViewById(R.id.edit_text_margem_atacado);

        this.produto_btn_cancelar = findViewById(R.id.produto_btn_cancelar);
        this.produto_btn_salvar = findViewById(R.id.produto_btn_salvar);


        clickNoBotaoSalvarListener();
        clickNoBotaoCancelarListener();

    }


    private void clickNoBotaoSalvarListener(){
        this.produto_btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product produtoParaCadastrar = getDadosProdutoFormulario();


                if (produtoParaCadastrar != null) {
                    Log.i("debugar", produtoParaCadastrar.toString());
                    ProductController productController = new ProductController(ConnectionSQL
                            .getInstance(ProductActivity.this));
                    int idProduto = productController.salvarProdutoController(produtoParaCadastrar);
                    if (idProduto > 0) {
                        Toast.makeText(ProductActivity.this, String.valueOf(idProduto),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProductActivity.this, "Erro ao salvar o produto"
                                        + String.valueOf(idProduto),
                                Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ProductActivity.this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG).show();
                }


            }
        });


    }
    private void clickNoBotaoCancelarListener(){
        this.produto_btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private Product getDadosProdutoFormulario() {

        this.produto = new Product();

        if (this.edit_text_id_produto.getText().toString().isEmpty() == false){
            this.produto.setId(Integer.parseInt(this.edit_text_id_produto.getText().toString()));
        } else{
            return null;
        }

        if (this.edit_text_nome_produto.getText().toString().isEmpty() == false){
            this.produto.setDescription(this.edit_text_nome_produto.getText().toString());
        } else{
            return null;
        }

        if (this.edit_text_custo.getText().toString().isEmpty() == false){
            this.produto.setCost(Double.parseDouble(this.edit_text_custo.getText().toString()));
        } else{
            return null;
        }
        if (this.edit_text_margem_atacado.getText().toString().isEmpty() == false){
            this.produto.setProfitRetail(Double.parseDouble(this.edit_text_margem_atacado.getText().toString()));
        } else{
            return null;
        }
        if (this.edit_text_margem_varejo.getText().toString().isEmpty() == false){
            this.produto.setProfitWholesale(Double.parseDouble(this.edit_text_margem_varejo.getText().toString()));
        } else{
            return null;
        }

        return this.produto;
    }

}