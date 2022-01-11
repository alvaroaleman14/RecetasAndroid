package com.example.recetas;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.recetas.Adaptadores.ListaPlatosAdapter;
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
    private TextView textViewSeleccionar,textViewBreakFast,textViewLunch,textViewDinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_platos_menu);

        textViewSeleccionar= findViewById(R.id.textViewSeleccionar);
        textViewBreakFast=findViewById(R.id.textViewBreakFast);
        textViewLunch=findViewById(R.id.textViewLunch);
        textViewDinner=findViewById(R.id.textViewDinner);

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
        List<Plato> listaPlatos= intento.getParcelableArrayListExtra("array");

        if(listaPlatos.isEmpty()==false && listaPlatos!=null){
            List<List<Plato>> platos= MostrarMenu(listaPlatos);

            List<Plato> desayunos = platos.get(0);
            List<Plato> almuerzos= platos.get(1);
            List<Plato> cenas= platos.get(2);


            if(desayunos.isEmpty()){
                textViewBreakFast.setText(R.string.NoBreakFast);
                textViewBreakFast.setTextColor(Color.RED);
                listaPlatosDesayuno.setVisibility(View.GONE);
            }else{
                listaPlatosAdapterDesayuno = new ListaPlatosAdapter(desayunos);
                listaPlatosDesayuno.setAdapter(listaPlatosAdapterDesayuno);
            }


            if(almuerzos.isEmpty()){
                textViewLunch.setText(R.string.NoLunch);
                textViewLunch.setTextColor(Color.RED);
                listaPlatosAlmuerzo.setVisibility(View.GONE);
            }else{
                listaPlatosAdapterAlmuerzo =new ListaPlatosAdapter(almuerzos);
                listaPlatosAlmuerzo.setAdapter(listaPlatosAdapterAlmuerzo);
            }

            if(cenas.isEmpty()){
                textViewDinner.setText(R.string.NoDinner);
                textViewDinner.setTextColor(Color.RED);
                listaPlatosCena.setVisibility(View.GONE);
            }else{
                listaPlatosAdapterCena =new ListaPlatosAdapter(cenas);
                listaPlatosCena.setAdapter(listaPlatosAdapterCena);
            }




        }else{

            textViewBreakFast.setVisibility(View.GONE);
            textViewLunch.setVisibility(View.GONE);
            textViewDinner.setVisibility(View.GONE);
            listaPlatosDesayuno.setVisibility(View.GONE);
            listaPlatosAlmuerzo.setVisibility(View.GONE);
            listaPlatosCena.setVisibility(View.GONE);


            textViewSeleccionar.setText(R.string.NoDish);


        }


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