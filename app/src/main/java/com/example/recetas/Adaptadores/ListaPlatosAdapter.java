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

import org.w3c.dom.Text;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plato,null,false);
        return new PlatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoViewHolder holder, int position) {
        //Completar con el resto de atributos
        holder.viewName.setText(listaPlatos.get(position).getName());
        holder.viewDescription.setText(listaPlatos.get(position).getDescription());
        holder.viewProtein.setText(listaPlatos.get(position).getProtein().toString());
        holder.viewFat.setText(listaPlatos.get(position).getFat().toString());
        holder.viewCarbohydrate.setText(listaPlatos.get(position).getCarbohydrate().toString());
        holder.viewCalorie.setText(listaPlatos.get(position).getCalorie().toString());
        holder.viewCalorie.setText(listaPlatos.get(position).getCalorie().toString());
        String AlergenosAux= listaPlatos.get(position).getAllergen().toString();
        holder.viewAlergen.setText(AlergenosAux.substring(1,AlergenosAux.length()-1));
        holder.viewIsRestaurant.setText(listaPlatos.get(position).getIs_restaurant() == true ? "Restaurante" : "Casero");
        holder.viewType.setText(listaPlatos.get(position).getType().toString());
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
        TextView viewName;
        TextView viewDescription;
        TextView viewProtein;
        TextView viewFat;
        TextView viewCarbohydrate;
        TextView viewCalorie;
        TextView viewAlergen;
        TextView viewIsRestaurant;
        TextView viewType;


        public PlatoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewName = itemView.findViewById(R.id.viewName);
            viewDescription = itemView.findViewById(R.id.viewDescription);
            viewProtein = itemView.findViewById(R.id.viewProtein);
            viewFat = itemView.findViewById(R.id.viewFat);
            viewCarbohydrate = itemView.findViewById(R.id.viewCarbohydrate);
            viewCalorie = itemView.findViewById(R.id.viewCalorie);
            viewAlergen = itemView.findViewById(R.id.viewAlergen);
            viewIsRestaurant = itemView.findViewById(R.id.viewIsRestaurant);
            viewType = itemView.findViewById(R.id.viewType);

        }
    }





}
