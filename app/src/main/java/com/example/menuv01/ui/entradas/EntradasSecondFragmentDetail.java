package com.example.menuv01.ui.entradas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.menuv01.CartActivity;
import com.example.menuv01.R;

import static com.example.menuv01.CartActivity.EXTRA_NOME_ENTRADAS;
import static com.example.menuv01.CartActivity.EXTRA_PRECO_ENTRADAS;
import static com.example.menuv01.CartActivity.EXTRA_QUANTIDADE_ENTRADAS;
import static com.example.menuv01.CartActivity.EXTRA_TIPO_ENTRADAS;

public class EntradasSecondFragmentDetail extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.menuv01.ui.entradas.EXTRA_ID";
    public static final String EXTRA_NOME = "com.example.menuv01.ui.entradas.EXTRA_NOME";
    public static final String EXTRA_DETALHE = "com.example.menuv01.ui.entradas.EXTRA_DETALHE";
    public static final String EXTRA_IMAGEVIEW = "com.example.menuv01.ui.entradas.EXTRA_IMAGEVIEW";
    public static final String EXTRA_PRECO = "com.example.menuv01.ui.entradas.EXTRA_PRECO";
    public static final String EXTRA_TIPO = "Entrada";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas_fragment_detail);
        final int[] count = {1};

        TextView editTextNome = findViewById(R.id.entrada_detail_textViewNome1);
        TextView editTextDetalhe = findViewById(R.id.entrada_detail_textViewDetalhe1);
        TextView editTextPreco = findViewById(R.id.entrada_detail_textViewPreco1);
        TextView editTextQuantidade = findViewById(R.id.entrada_detail_textViewQuantidade1);
        ImageView editTextImageView = findViewById(R.id.entrada_detail_imageView1);
        Button entradas_voltar = findViewById(R.id.button_entrada_voltar);
        Button entradas_fazerpedido = findViewById(R.id.button_entrada_fazerpedido);
        ImageButton adicionarEntrada = findViewById(R.id.button_entrada_adicionar);
        ImageButton restarEntrada = findViewById(R.id.button_entrada_restar);
        adicionarEntrada.setOnClickListener(v -> {
            count[0]++;
            editTextQuantidade.setText(String.valueOf(count[0]));
        });
        restarEntrada.setOnClickListener(v -> {
            if (count[0] > 0)
                count[0]--;
            editTextQuantidade.setText(String.valueOf(count[0]));
        });
        Intent intent = getIntent();

        String nomeEntradaSelected = intent.getStringExtra(EXTRA_NOME);
        String precoEntradaSelected = intent.getStringExtra(EXTRA_PRECO);

        if (intent.hasExtra(EXTRA_ID)) {
            Double precoEntradaSelectedDouble = Double.parseDouble(precoEntradaSelected);
            setTitle("Detalhe da entrada numero: " + intent.getStringExtra(EXTRA_ID));    //Takes the Selected Entrada and shows everything below
            editTextNome.setText(intent.getStringExtra(EXTRA_NOME));
            editTextDetalhe.setText(intent.getStringExtra(EXTRA_DETALHE));
            editTextPreco.setText(getString(R.string.currency) + String.format("%.2f", precoEntradaSelectedDouble));
            editTextImageView.setImageResource(intent.getIntExtra(EXTRA_IMAGEVIEW, R.id.entrada_detail_imageView1));
            ;
        } else {
            setTitle("Add note");
        }
        entradas_voltar.setOnClickListener(v -> EntradasSecondFragmentDetail.this.finish());
        entradas_fazerpedido.setOnClickListener(v -> {

            //Sends the current item selected through the EXTRA with intent

            Intent intent2 = new Intent(this, CartActivity.class);
            intent2.putExtra(EXTRA_NOME_ENTRADAS, nomeEntradaSelected);
            intent2.putExtra(EXTRA_PRECO_ENTRADAS, precoEntradaSelected);
            intent2.putExtra(EXTRA_TIPO_ENTRADAS, "Entrada");
            intent2.putExtra(EXTRA_QUANTIDADE_ENTRADAS, String.valueOf(count[0]));
            startActivity(intent2);

        });
    }
}