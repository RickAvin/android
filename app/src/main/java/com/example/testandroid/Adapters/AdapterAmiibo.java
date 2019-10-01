package com.example.testandroid.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testandroid.Pojos.Personaje;
import com.example.testandroid.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterAmiibo extends RecyclerView.Adapter<AdapterAmiibo.AmiiboviewHolder> implements View.OnClickListener {

    private List<Personaje> personajeList;
    private Context context;
    private View.OnClickListener listener;

    public AdapterAmiibo(ArrayList<Personaje> personajeList, Context context) {
        this.personajeList = personajeList;
        this.context = context;


    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {

        if(listener != null)
            listener.onClick(v);
    }

    @NonNull
    @Override
    public AmiiboviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_lista,parent,false);
        v.setOnClickListener(this);
        AmiiboviewHolder holder = new AmiiboviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AmiiboviewHolder holder, int position) {
        Personaje personaje = personajeList.get(position);
        try{
            Glide.with(context).load(personaje.getImage()).into(holder.img);
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.nombre.setText(personaje.getName());
        holder.serie.setText(personaje.getAmiiboSeries());
    }

    @Override
    public int getItemCount() {
        if(personajeList != null){
            return personajeList.size();
        }else{
           return 0;
        }
    }

    public void filter(String text,ArrayList<Personaje> personsFilter) {
        if(text.isEmpty()){
            personajeList.clear();
            personajeList.addAll(personsFilter);
        } else{
            ArrayList<Personaje> result = new ArrayList<>();
            text = text.toLowerCase();
            for(Personaje p: personsFilter){
                if(p.getName().toLowerCase().contains(text) || p.getAmiiboSeries().toLowerCase().contains(text) ||
                        p.getGameSeries().toLowerCase().contains(text) || p.getCharacter().toLowerCase().contains(text)){
                    logePrint("name: " + p.getName());
                    result.add(p);
                }
            }
            personajeList.clear();
            personajeList.addAll(result);

        }
        notifyDataSetChanged();
    }


    public static class AmiiboviewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView nombre,serie;

        public AmiiboviewHolder(@NonNull View v) {
            super(v);

            img = v.findViewById(R.id.img_view);
            nombre = v.findViewById(R.id.txt_nombre);
            serie = v.findViewById(R.id.txt_serie);


        }
    }


    public void logePrint(String message) {
        Log.e("Mylog", message);
    }
}
