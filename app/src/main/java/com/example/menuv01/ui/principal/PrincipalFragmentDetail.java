package com.example.menuv01.ui.principal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.menuv01.CartActivity;
import com.example.menuv01.R;

import static com.example.menuv01.CartActivity.EXTRA_NOME_PRINCIPAL;
import static com.example.menuv01.CartActivity.EXTRA_PRECO_PRINCIPAL;
import static com.example.menuv01.CartActivity.EXTRA_QUANTIDADE_PRINCIPAL;
import static com.example.menuv01.CartActivity.EXTRA_TIPO_PRINCIPAL;

public class PrincipalFragmentDetail extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.menuv01.ui.principal.EXTRA_ID";
    public static final String EXTRA_NOME = "com.example.menuv01.ui.principal.EXTRA_NOME";
    public static final String EXTRA_DETALHE = "com.example.menuv01.ui.principal.EXTRA_DETALHE";
    public static final String EXTRA_IMAGEVIEW = "com.example.menuv01.ui.principal.EXTRA_IMAGEVIEW";
    public static final String EXTRA_PRECO = "com.example.menuv01.ui.principal.EXTRA_PRECO";
    public static final String EXTRA_TIPO = "Principal";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int[] count = {1};


        setContentView(R.layout.fragment_principal_detail);
        final TextView[] editTextNome = {findViewById(R.id.principal_detail_textViewNome1)};
        TextView editTextDetalhe = findViewById(R.id.principal_detail_textViewDetalhe1);
        TextView editTextPreco = findViewById(R.id.principal_detail_textViewPreco1);
        TextView editTextQuantidade = findViewById(R.id.principal_detail_textViewQuantidade1);
        ImageView editTextImageView = findViewById(R.id.principal_detail_imageView1);
        Button principal_voltar = findViewById(R.id.button_principal_voltar);
        Button principal_fazerpedido = findViewById(R.id.button_principal_fazerpedido);
        ImageButton adicionarPrincipal = findViewById(R.id.button_principal_adicionar);
        ImageButton restarPrincipal = findViewById(R.id.button_principal_restar);

        adicionarPrincipal.setOnClickListener(v -> {
            count[0]++;
            editTextQuantidade.setText(String.valueOf(count[0]));
        });
        restarPrincipal.setOnClickListener(v -> {
            if (count[0] > 0)
                count[0]--;
            editTextQuantidade.setText(String.valueOf(count[0]));
        });

        Intent intent = getIntent();
        String nomePrincipalSelected = intent.getStringExtra(EXTRA_NOME);
        String tipoPrincipalSelected = intent.getStringExtra(EXTRA_TIPO);
        String precoPrincipalSelected = intent.getStringExtra(EXTRA_PRECO);

        if (intent.hasExtra(EXTRA_ID)) {
            Double precoPrincipalSelectedDouble = Double.parseDouble(precoPrincipalSelected);
            setTitle("Detalhe do prato numero: " + intent.getStringExtra(EXTRA_ID));    //Takes the Selected Entrada and shows everything below
            editTextNome[0].setText(intent.getStringExtra(EXTRA_NOME));
            editTextDetalhe.setText(intent.getStringExtra(EXTRA_DETALHE));
            editTextPreco.setText(getString(R.string.currency) + String.format("%.2f", precoPrincipalSelectedDouble));
            editTextImageView.setImageResource(intent.getIntExtra(EXTRA_IMAGEVIEW, R.id.principal_detail_imageView1));
            ;
        } else {
            setTitle("Add note");
        }
        principal_voltar.setOnClickListener(v -> PrincipalFragmentDetail.this.finish());
        principal_fazerpedido.setOnClickListener(v -> {

            //Sends the current item selected through the EXTRA with intent

            Intent intent2 = new Intent(this, CartActivity.class);
            intent2.putExtra(EXTRA_NOME_PRINCIPAL, nomePrincipalSelected);
            intent2.putExtra(EXTRA_PRECO_PRINCIPAL, precoPrincipalSelected);
            intent2.putExtra(EXTRA_TIPO_PRINCIPAL, "Principal");
            intent2.putExtra(EXTRA_QUANTIDADE_PRINCIPAL, String.valueOf(count[0]));
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