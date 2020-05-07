package com.example.menuv01.ui.sobremesas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.menuv01.CartActivity;
import com.example.menuv01.R;

import static com.example.menuv01.CartActivity.EXTRA_NOME_SOBREMESAS;
import static com.example.menuv01.CartActivity.EXTRA_PRECO_SOBREMESAS;
import static com.example.menuv01.CartActivity.EXTRA_QUANTIDADE_SOBREMESAS;
import static com.example.menuv01.CartActivity.EXTRA_TIPO_SOBREMESAS;

public class SobremesasFragmentDetail extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.menuv01.ui.sobremesas.EXTRA_ID";
    public static final String EXTRA_NOME = "com.example.menuv01.ui.sobremesas.EXTRA_NOME";
    public static final String EXTRA_DETALHE = "com.example.menuv01.ui.sobremesas.EXTRA_DETALHE";
    public static final String EXTRA_IMAGEVIEW = "com.example.menuv01.ui.sobremesas.EXTRA_IMAGEVIEW";
    public static final String EXTRA_PRECO = "com.example.menuv01.ui.sobremesas.EXTRA_PRECO";
    public static final String EXTRA_TIPO = "Sobremesa";
    //  public static final String EXTRA_QUANTIDADE = "com.example.menuv01.ui.sobremesas.EXTRA_QUANTIDADE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int[] count = {1};
        setContentView(R.layout.activity_sobremesas_fragment_detail);
        final TextView[] editTextNome = {findViewById(R.id.sobremesas_detail_textViewNome1)};
        TextView editTextDetalhe = findViewById(R.id.sobremesas_detail_textViewDetalhe1);
        TextView editTextPreco = findViewById(R.id.sobremesas_detail_textViewPreco1);
        TextView editTextQuantidade = findViewById(R.id.sobremesas_detail_textViewQuantidade1);
        ImageView editTextImageView = findViewById(R.id.sobremesas_detail_imageView1);
        Button sobremesas_voltar = findViewById(R.id.button_sobremesas_voltar);
        Button sobremesas_fazerpedido = findViewById(R.id.button_sobremesas_fazerpedido);
        ImageButton adicionarSobremesa = findViewById(R.id.button_sobremesas_adicionar);
        ImageButton restarSobremesa = findViewById(R.id.button_sobremesas_restar);

        adicionarSobremesa.setOnClickListener(v -> {
            count[0]++;
            editTextQuantidade.setText(String.valueOf(count[0]));
        });
        restarSobremesa.setOnClickListener(v -> {
            if (count[0] > 0)
                count[0]--;
            editTextQuantidade.setText(String.valueOf(count[0]));
        });

        Intent intent = getIntent();
        String nomeSobremesaSelected = intent.getStringExtra(EXTRA_NOME);
        String tipoSobremesaSelected = intent.getStringExtra(EXTRA_TIPO);
        String precoSobremesaSelected = intent.getStringExtra(EXTRA_PRECO);

        if (intent.hasExtra(EXTRA_ID)) {
            Double precoSobremesaSelectedDouble = Double.parseDouble(precoSobremesaSelected);
            setTitle("Detalhe da sobremesa numero: " + intent.getStringExtra(EXTRA_ID));    //Takes the Selected Entrada and shows everything below
            editTextNome[0].setText(intent.getStringExtra(EXTRA_NOME));
            editTextDetalhe.setText(intent.getStringExtra(EXTRA_DETALHE));
            editTextPreco.setText(getString(R.string.currency) + String.format("%.2f", precoSobremesaSelectedDouble));
            editTextImageView.setImageResource(intent.getIntExtra(EXTRA_IMAGEVIEW, R.id.sobremesas_detail_imageView1));
        } else {
            setTitle("Add note");
        }
        sobremesas_voltar.setOnClickListener(v -> SobremesasFragmentDetail.this.finish());
        sobremesas_fazerpedido.setOnClickListener(v -> {

            //Sends the current item selected through the EXTRA with intent

            Intent intent2 = new Intent(this, CartActivity.class);
            intent2.putExtra(EXTRA_NOME_SOBREMESAS, nomeSobremesaSelected);
            intent2.putExtra(EXTRA_PRECO_SOBREMESAS, precoSobremesaSelected);
            intent2.putExtra(EXTRA_TIPO_SOBREMESAS, "Sobremesa");
            intent2.putExtra(EXTRA_QUANTIDADE_SOBREMESAS, String.valueOf(count[0]));
            startActivity(intent2);

            /*int imageViewId = sobremesas.getSobremesasid()-1;
            int resourceId = getContext().getResources().getIdentifier("sobremesasn"+imageViewId, "drawable", "com.example.menuv01");
            intent.putExtra(SlideshowFragmentDetail.EXTRA_ID, String.valueOf(sobremesas.getSobremesasid()));   //Sends the current item selected through the EXTRA with intent
            intent.putExtra(SlideshowFragmentDetail.EXTRA_NOME, sobremesas.getSobremesasnome());
            intent.putExtra(SlideshowFragmentDetail.EXTRA_DETALHE, sobremesas.getSobremesasdetalhe());
            intent.putExtra(SlideshowFragmentDetail.EXTRA_PRECO, String.valueOf(sobremesas.getSobremesaspreco()));
            intent.putExtra(SlideshowFragmentDetail.EXTRA_IMAGEVIEW, resourceId);
            startActivityForResult(intent, DETAIL_SOBREMESAS_REQUEST);*/
        });
    }
}