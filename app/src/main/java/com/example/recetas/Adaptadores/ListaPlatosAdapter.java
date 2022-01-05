package com.example.recetas.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recetas.Entidades.Plato;
import com.example.recetas.R;

import java.util.ArrayList;

public class ListaPlatosAdapter extends RecyclerView.Adapter<ListaPlatosAdapter.PlatoViewHolder> {

    ArrayList<Plato> listaPlatos;
    ArrayList<Plato> listaOriginal;


    public ListaPlatosAdapter(ArrayList<Plato> listaPlatos) {
        this.listaPlatos = listaPlatos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaPlatos);
    }

    @NonNull
    @Override
    public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listar_platos_menu,null,false);
        return new PlatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoViewHolder holder, int position) {
        //Completar con el resto de atributos
        holder.viewNombre.setText(listaPlatos.get(position).getName());
    }


    //Filtrar con los parámetros de la búsqueda
    public void filtrado(final String txtBuscar) {

    }


    @Override
    public int getItemCount() {
        return listaPlatos.size();
    }


    public class PlatoViewHolder extends RecyclerView.ViewHolder {


        //Completar con el resto de atributos
        TextView viewNombre;

        public PlatoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);


        }
    }





}
