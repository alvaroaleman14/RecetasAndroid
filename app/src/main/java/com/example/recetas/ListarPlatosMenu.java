package com.example.recetas;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.DeadSystemException;
import android.os.Parcelable;
import android.widget.TextView;

import com.example.recetas.Adaptadores.ListaPlatosAdapter;
import com.example.recetas.DB.Dish;
import com.example.recetas.Entidades.Plato;
import com.example.recetas.Enum.TipoComida;

import java.util.ArrayList;
import java.util.List;

public class ListarPlatosMenu extends AppCompatActivity {

    private RecyclerView listaPlatosDesayuno;
    private RecyclerView listaPlatosAlmuerzo;
    private RecyclerView listaPlatosCena;
    private List<Plato> listaPlatos;
    private ListaPlatosAdapter listaPlatosAdapterDesayuno,listaPlatosAdapterAlmuerzo,listaPlatosAdapterCena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_platos_menu);


        listaPlatosDesayuno = findViewById(R.id.listaPlatosDesayuno);
        listaPlatosDesayuno.setHasFixedSize(true);
        listaPlatosDesayuno.setLayoutManager(new LinearLayoutManager(this));

        listaPlatosAlmuerzo = findViewById(R.id.listaPlatosAlmuerzos);
        listaPlatosAlmuerzo.setHasFixedSize(true);
        listaPlatosAlmuerzo.setLayoutManager(new LinearLayoutManager(this));

        listaPlatosCena = findViewById(R.id.listaPlatosCenas);
        listaPlatosCena.setHasFixedSize(true);
        listaPlatosCena.setLayoutManager(new LinearLayoutManager(this));


        Intent intento = getIntent();
        Bundle b = intento.getExtras();
        List<Plato> listaPlatos;
        listaPlatos= (List<Plato>) b.getParcelable("list");


        List<List<Plato>> platos= MostrarMenu(listaPlatos);

        List<Plato> desayunos = platos.get(0);
        List<Plato> almuerzos= platos.get(1);
        List<Plato> cenas= platos.get(2);


        listaPlatosAdapterDesayuno = new ListaPlatosAdapter(desayunos);
        listaPlatosDesayuno.setAdapter(listaPlatosAdapterDesayuno);

        listaPlatosAdapterAlmuerzo =new ListaPlatosAdapter(almuerzos);
        listaPlatosAlmuerzo.setAdapter(listaPlatosAdapterAlmuerzo);

        listaPlatosAdapterCena =new ListaPlatosAdapter(cenas);
        listaPlatosCena.setAdapter(listaPlatosAdapterCena);




    }


    public List<List<Plato>> MostrarMenu(List<Plato> platos){

        List<List<Plato>> salida= new ArrayList<>();
        List<Plato> Desayunos= new ArrayList<>();
        List<Plato> Almuerzos= new ArrayList<>();
        List<Plato> Cenas= new ArrayList<>();
        for(Plato plato:platos){
            if(plato.getType().contains(TipoComida.Desayuno)){
                Desayunos.add(plato);
            }
            if(plato.getType().contains(TipoComida.Almuerzo)){
                Almuerzos.add(plato);
            }
            if(plato.getType().contains(TipoComida.Cena)){
                Cenas.add(plato);
            }
        }
            salida.add(Desayunos);
            salida.add(Almuerzos);
            salida.add(Cenas);


            return salida;

    }




}