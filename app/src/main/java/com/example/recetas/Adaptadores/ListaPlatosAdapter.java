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
import com.example.recetas.Enum.Alergenos;
import com.example.recetas.Enum.TipoComida;
import com.example.recetas.R;
import com.example.recetas.VerPlato;

import java.util.ArrayList;
import java.util.List;

public class ListaPlatosAdapter extends RecyclerView.Adapter<ListaPlatosAdapter.PlatoViewHolder> {

    List<Plato> listaPlatos;
    List<Plato> listaOriginal;
    Context context;


    public ListaPlatosAdapter(List<Plato> listaPlatos) {
        this.listaPlatos = listaPlatos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaPlatos);
    }

    @NonNull
    @Override
    public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plato,null,false);
        context= view.getContext();
        return new PlatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoViewHolder holder, int position) {
        /*holder.viewName.setText(listaPlatos.get(position).getName());
        //holder.viewDescription.setText(listaPlatos.get(position).getDescription());
        holder.viewProtein.setText(": "+listaPlatos.get(position).getProtein().toString());
        holder.viewFat.setText(": "+listaPlatos.get(position).getFat().toString());
        holder.viewCarbohydrate.setText(": "+listaPlatos.get(position).getCarbohydrate().toString());
        holder.viewCalorie.setText(":"+listaPlatos.get(position).getCalorie().toString());
        holder.viewCalorie.setText(": "+listaPlatos.get(position).getCalorie().toString());
        String AlergenosAux= listaPlatos.get(position).getAllergen().toString();
        holder.viewAlergen.setText(": "+AlergenosAux.substring(1,AlergenosAux.length()-1));
        holder.viewIsRestaurant.setText(listaPlatos.get(position).getIs_restaurant() == true ? ": Restaurante" : ": Casero");
        //String TipoAux= listaPlatos.get(position).getType().toString();
        //holder.viewType.setText(": "+TipoAux.substring(1,TipoAux.length()-1));*/


        holder.viewName.setText(listaPlatos.get(position).getName());
        holder.viewProtein.setText(listaPlatos.get(position).getProtein().toString());
        holder.viewFat.setText(listaPlatos.get(position).getFat().toString());
        holder.viewCarbohydrate.setText(listaPlatos.get(position).getCarbohydrate().toString());
        holder.viewCalorie.setText(listaPlatos.get(position).getCalorie().toString());
        holder.viewCalorie.setText(listaPlatos.get(position).getCalorie().toString());
        //String AlergenosAux= listaPlatos.get(position).getAllergen().toString();
        //holder.viewAlergen.setText(AlergenosAux.substring(1,AlergenosAux.length()-1));

        List<Alergenos> alergenosList= listaPlatos.get(position).getAllergen();



        String AlergenosAux= AlergenosTraduccion(alergenosList).toString();
        holder.viewAlergen.setText(AlergenosAux.substring(1,AlergenosAux.length()-1));
        holder.viewIsRestaurant.setText(listaPlatos.get(position).getIs_restaurant() == true ? context.getString(R.string.Restaurant) : context.getString(R.string.Homemade));

    }




    @Override
    public int getItemCount() {
        return listaPlatos.size();
    }


    public List<String> TipoComidaTraduccion(List<TipoComida> tipoComidas){
        List<String> comidasSalida =new ArrayList<>();
        for(TipoComida comida: tipoComidas){
            if(comida.equals(TipoComida.Desayuno)){
                comidasSalida.add(context.getString(R.string.Breakfast));
            }else if(comida.equals(TipoComida.Almuerzo)){
                comidasSalida.add(context.getString(R.string.Lunch));
            }else if(comida.equals(TipoComida.Cena)){
                comidasSalida.add(context.getString(R.string.Dinner));
            }
        }
        return comidasSalida;
    }


    public List<String> AlergenosTraduccion(List<Alergenos> alergenosList){
        List<String> alergenosSalida =new ArrayList<>();
        for(Alergenos alergeno: alergenosList){
            if(alergeno.equals(Alergenos.Altramuces)){
                alergenosSalida.add(context.getString(R.string.Lupine));
            }else if(alergeno.equals(Alergenos.Apio)){
                alergenosSalida.add(context.getString(R.string.Celery));
            }else if(alergeno.equals(Alergenos.Crustaceos)){
                alergenosSalida.add(context.getString(R.string.Crustacean));
            }else if(alergeno.equals(Alergenos.Glutén)){
                alergenosSalida.add(context.getString(R.string.Gluten));
            }else if(alergeno.equals(Alergenos.Dióxido_De_Azufre)){
                alergenosSalida.add(context.getString(R.string.Sulfur_Dioxide));
            }else if(alergeno.equals(Alergenos.Frutos_Secos)){
                alergenosSalida.add(context.getString(R.string.Dried_Fruit));
            }else if(alergeno.equals(Alergenos.Huevo)){
                alergenosSalida.add(context.getString(R.string.Egg));
            }else if(alergeno.equals(Alergenos.Lacteos)){
                alergenosSalida.add(context.getString(R.string.Dairy));
            }else if(alergeno.equals(Alergenos.Moluscos)){
                alergenosSalida.add(context.getString(R.string.Mollusk));
            }else if(alergeno.equals(Alergenos.Mostaza)){
                alergenosSalida.add(context.getString(R.string.Mustard));
            }else if(alergeno.equals(Alergenos.Pescado)){
                alergenosSalida.add(context.getString(R.string.Fish));
            }else if(alergeno.equals(Alergenos.Sesamo)){
                alergenosSalida.add(context.getString(R.string.Sesame));
            }else if(alergeno.equals(Alergenos.Soja)){
                alergenosSalida.add(context.getString(R.string.Soy));
            }
        }
        return alergenosSalida;
    }


    public class PlatoViewHolder extends RecyclerView.ViewHolder {

        TextView viewName;
        //TextView viewDescription;
        TextView viewProtein;
        TextView viewFat;
        TextView viewCarbohydrate;
        TextView viewCalorie;
        TextView viewAlergen;
        TextView viewIsRestaurant;
        //TextView viewType;



        public PlatoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewName = itemView.findViewById(R.id.viewName);
            //viewDescription = itemView.findViewById(R.id.viewDescription);
            viewProtein = itemView.findViewById(R.id.viewProtein);
            viewFat = itemView.findViewById(R.id.viewFat);
            viewCarbohydrate = itemView.findViewById(R.id.viewCarbohydrate);
            viewCalorie = itemView.findViewById(R.id.viewCalorie);
            viewAlergen = itemView.findViewById(R.id.viewAllergen);
            viewIsRestaurant = itemView.findViewById(R.id.viewIsRestaurant);
            //viewType = itemView.findViewById(R.id.viewType);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerPlato.class);
                    intent.putExtra("ID", listaPlatos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }

            });

        }
    }





}
