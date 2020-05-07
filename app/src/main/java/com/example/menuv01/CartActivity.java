package com.example.menuv01;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CartActivity extends AppCompatActivity {
    public static final String EXTRA_NOME_ENTRADAS = "com.example.menuv01.ui.entradas.EXTRA_NOME";
    public static final String EXTRA_TIPO_ENTRADAS = "com.example.menuv01.ui.entradas.EXTRA_TIPO";
    public static final String EXTRA_PRECO_ENTRADAS = "com.example.menuv01.ui.entradas.EXTRA_PRECO";
    public static final String EXTRA_QUANTIDADE_ENTRADAS = "com.example.menuv01.ui.entradas.EXTRA_QUANTIDADE";

    public static final String EXTRA_NOME_PRINCIPAL = "com.example.menuv01.ui.principal.EXTRA_NOME";
    public static final String EXTRA_TIPO_PRINCIPAL = "com.example.menuv01.ui.principal.EXTRA_TIPO";
    public static final String EXTRA_PRECO_PRINCIPAL = "com.example.menuv01.ui.principal.EXTRA_PRECO";
    public static final String EXTRA_QUANTIDADE_PRINCIPAL = "com.example.menuv01.ui.principal.EXTRA_QUANTIDADE";

    public static final String EXTRA_NOME_SOBREMESAS = "com.example.menuv01.ui.sobremesas.EXTRA_NOME";
    public static final String EXTRA_TIPO_SOBREMESAS = "com.example.menuv01.ui.sobremesas.EXTRA_TIPO";
    public static final String EXTRA_PRECO_SOBREMESAS = "com.example.menuv01.ui.sobremesas.EXTRA_PRECO";
    public static final String EXTRA_QUANTIDADE_SOBREMESAS = "com.example.menuv01.ui.sobremesas.EXTRA_QUANTIDADE";

    public static TextView tv_total_cart;
    public static int total = 0;
    public static BigDecimal totalBigDecimal;
    RecyclerView recycler_cart;
    String jsonCartList;
    ArrayList<CartItems> mCartItems = CartListAdapter.selecteditems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //Set back button to activity
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Detalhe do pedido");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        tv_total_cart = findViewById(R.id.tv_total_cart);
        Log.d("Size List of Cart: ", String.valueOf(mCartItems.size()));
        recycler_cart = findViewById(R.id.recycler_cart);
        recycler_cart.setHasFixedSize(true);
      //  recycler_cart.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recycler_cart.setLayoutManager(new LinearLayoutManager(this));      //Setting the layout
        //recycler_cart.getRecycledViewPool().setMaxRecycledViews(0, 0);
        CartListAdapter adapter2 = new CartListAdapter(mCartItems);
        recycler_cart.setAdapter(adapter2);
        getIntentData();
        calculateTotal();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getIntentData() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            String entradaNome = extras.getString(EXTRA_NOME_ENTRADAS);
            String entradaTipo = extras.getString(EXTRA_TIPO_ENTRADAS);
            String entradaPreco = extras.getString(EXTRA_PRECO_ENTRADAS);
            String entradaQuantidade = extras.getString(EXTRA_QUANTIDADE_ENTRADAS);
            String principalNome = extras.getString(EXTRA_NOME_PRINCIPAL);
            String principalTipo = extras.getString(EXTRA_TIPO_PRINCIPAL);
            String principalPreco = extras.getString(EXTRA_PRECO_PRINCIPAL);
            String principalQuantidade = extras.getString(EXTRA_QUANTIDADE_PRINCIPAL);
            String sobremesaNome = extras.getString(EXTRA_NOME_SOBREMESAS);
            String sobremesaTipo = extras.getString(EXTRA_TIPO_SOBREMESAS);
            String sobremesaPreco = extras.getString(EXTRA_PRECO_SOBREMESAS);
            String sobremesaQuantidade = extras.getString(EXTRA_QUANTIDADE_SOBREMESAS);

            if (extras.getString(EXTRA_NOME_ENTRADAS) != null) {
                CartItems newCartItemEntrada = new CartItems(entradaNome, entradaTipo, entradaPreco, entradaQuantidade);
                mCartItems.add(newCartItemEntrada);   // Get the Required Parameters for sending Order to server.
            }
            if (extras.getString(EXTRA_NOME_PRINCIPAL) != null) {
                CartItems newCartItemPrincipal = new CartItems(principalNome, principalTipo, principalPreco, principalQuantidade);
                mCartItems.add(newCartItemPrincipal);   // Get the Required Parameters for sending Order to server.
            }
            if (extras.getString(EXTRA_NOME_SOBREMESAS) != null) {
                CartItems newCartItemSobremesa = new CartItems(sobremesaNome, sobremesaTipo, sobremesaPreco, sobremesaQuantidade);
                mCartItems.add(newCartItemSobremesa);   // Get the Required Parameters for sending Order to server.
            }
        }
    }

    public void calculateTotal() {
        int i = 0;
        total = 0;
        totalBigDecimal = BigDecimal.ZERO;
        while (i < mCartItems.size()) {
            BigDecimal precoI = new BigDecimal(mCartItems.get(i).getPreco());
            BigDecimal quantidadeI = new BigDecimal(mCartItems.get(i).getQuantidade());
            totalBigDecimal = totalBigDecimal.add(precoI.multiply(quantidadeI));
            i++;
        }
        tv_total_cart.setText(getString(R.string.currency) + String.format("%.2f", totalBigDecimal));
    }

    public void insertOrder(View view) {
        if (mCartItems.size() > 0) {
            Gson gson = new Gson();
            jsonCartList = gson.toJson(mCartItems);
            DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        // Request code for creating a PDF document.
                        Date currentTime = Calendar.getInstance().getTime();
                        String FILENAME = "pedido_" + currentTime + ".json";
                        String destPath = getApplicationContext().getExternalFilesDir(null).getAbsolutePath();
                        File file = new File(destPath + File.separator + FILENAME);
                        try {
                            FileOutputStream fOut = new FileOutputStream(file);
                            fOut.write(jsonCartList.getBytes());
                            Toast.makeText(getApplicationContext(), "Salvado em " + destPath + "/" + FILENAME,
                                    Toast.LENGTH_LONG).show();
                            mCartItems.clear();
                            fOut.close();
                            Intent startHome = new Intent(getApplicationContext(), HomeDrawer.class);
                            startActivity(startHome);
                        } catch (IOException e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
            builder.setMessage("Quer finalizar o pedido ?                          Total:" + String.format("%.2f", totalBigDecimal)).setPositiveButton("Sim", dialogClickListener)
                    .setNegativeButton("Não", dialogClickListener).show();
        } else {
            Toast.makeText(getApplicationContext(), "Não tem itens no carrinho!", Toast.LENGTH_LONG).show();
        }
    }
}