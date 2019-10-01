package com.example.testandroid.Main;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.testandroid.Adapters.AdapterAmiibo;
import com.example.testandroid.Basicos.BaseActivity;
import com.example.testandroid.Basicos.OnSingleClickListener;
import com.example.testandroid.Pojos.Amiibo;
import com.example.testandroid.Pojos.Personaje;
import com.example.testandroid.Pojos.Relase;
import com.example.testandroid.R;
import com.example.testandroid.Service.ServicioRetrofit;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaActivity extends BaseActivity {

    @BindView(R.id.txt_buscar) EditText txt_buscar;
    @BindView(R.id.recyclerview) RecyclerView recyclerView;



    Retrofit retrofit;
    ServicioRetrofit servicioRetrofit;
    Call<Amiibo> listCall;

    AdapterAmiibo adapterAmiibo;
    ArrayList<Personaje> personajes;
    ArrayList<Personaje> personajesTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        iniciarServicioRetro();
        init();
        onClick();

    }

    @Override
    public int getLayout() {
        return R.layout.activity_lista;
    }

    private void init(){
        personajes = new ArrayList<>();
        personajesTemp = new ArrayList<>();
        adapterAmiibo = new AdapterAmiibo(personajes, ListaActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListaActivity.this));
        recyclerView.setAdapter(adapterAmiibo);
    }

    private void onClick(){

        txt_buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                logePrint(s.toString());
                adapterAmiibo.filter(s.toString(),personajesTemp);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        adapterAmiibo.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                details(personajes.get(recyclerView.getChildAdapterPosition(v)));
            }
        });

    }

    private void iniciarServicioRetro(){
        logePrint("Inicia servicio retro");
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.amiiboapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        inicarInterfazPeticion();

    }

    private void inicarInterfazPeticion(){
        servicioRetrofit = retrofit.create(ServicioRetrofit.class);
        peticionPersonajesGeneral();

    }

    private void peticionPersonajesGeneral(){

        listCall = servicioRetrofit.getPersonajes();

        listCall.enqueue(new Callback<Amiibo>() {
            @Override
            public void onResponse(Call<Amiibo> call, Response<Amiibo> response) {
                logePrint("Call: " + call);
                logePrint("Code: " + response);
                if(response.isSuccessful()){
                    Amiibo amiibo = response.body();

                    personajes.addAll(amiibo.getAmiibo());
                    personajesTemp.addAll(amiibo.getAmiibo());

                    if(personajes.size() > 0){
                        logePrint("size " + personajes.size());

                        adapterAmiibo.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<Amiibo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void details(Personaje p){
        int width = (getResources().getDisplayMetrics().widthPixels);
        int height = (getResources().getDisplayMetrics().heightPixels);
        Dialog alertDialog1 = new Dialog(ListaActivity.this);
        alertDialog1.setCancelable(false);
        alertDialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog1.setContentView(R.layout.layout_detalles);
        alertDialog1.getWindow().setBackgroundDrawableResource(R.color.transparent);
        alertDialog1.getWindow().setLayout(width, height);
        llenarVistaDetalles(p,alertDialog1);
    }

    private void llenarVistaDetalles(Personaje p,Dialog dialog){
        ImageView image = dialog.findViewById(R.id.img_personaje);
        TextView nombre = dialog.findViewById(R.id.txt_nombre_personaje);
        TextView caracter = dialog.findViewById(R.id.txt_caracter_personaje);
        TextView amiiboSeries = dialog.findViewById(R.id.txt_amiibo_series_personaje);
        TextView gameSeries = dialog.findViewById(R.id.txt_game_series_personaje);
        TextView tipo = dialog.findViewById(R.id.txt_tipo_personaje);
        TextView fechas = dialog.findViewById(R.id.txt_fechas_personaje);
        Button button = dialog.findViewById(R.id.btn_aceptar);
        try {
            Glide.with(ListaActivity.this).load(p.getImage()).into(image);
        }catch (Exception e){
            e.printStackTrace();
        }
        nombre.setText(p.getName());
        caracter.setText(p.getCharacter());
        amiiboSeries.setText(p.getAmiiboSeries());
        gameSeries.setText(p.getGameSeries());
        tipo.setText(p.getType());
        String fecha = validFecha(p.getRelease());
        fechas.setText(fecha);
        button.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();


    }
    private String validFecha(Relase relase){
        String fecha = "";
        if(relase.getAu() != null)
            fecha = getResources().getString(R.string.australia) + relase.getAu() + "\n";
        if(relase.getEu() != null)
            fecha += getResources().getString(R.string.union_europea) + relase.getEu() + "\n";
        if(relase.getJp() != null)
            fecha += getResources().getString(R.string.japon) + relase.getJp() + "\n";
        if(relase.getNa() != null)
            fecha += getResources().getString(R.string.namibia) + relase.getNa() + "\n";

        return fecha;
    }

    public void logePrint(String message) {
        Log.e("Mylog", message);
    }
}
