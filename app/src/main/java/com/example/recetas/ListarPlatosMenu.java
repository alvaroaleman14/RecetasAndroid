package com.example.recetas;

import static com.example.recetas.GenerarMenu.Name;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.recetas.Adaptadores.ListaPlatosAdapter;
import com.example.recetas.DB.Dish;
import com.example.recetas.Entidades.Plato;

import java.util.ArrayList;

public class ListarPlatosMenu extends AppCompatActivity {

    private RecyclerView listaPlatos;
    private ArrayList<Plato> listaArrayPlatos;
    private ListaPlatosAdapter listaPlatosAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_platos_menu);


        listaPlatos = findViewById(R.id.listaPlatos);
        listaPlatos.setLayoutManager(new LinearLayoutManager(this));

        Dish DBdish= new Dish(ListarPlatosMenu.this);

        listaArrayPlatos=new ArrayList<>();

        listaPlatosAdapter = new ListaPlatosAdapter(DBdish.mostrarPlatos());
        listaPlatos.setAdapter(listaPlatosAdapter);






        Intent intento = getIntent();
        Bundle b = intento.getExtras();
        String Nombre = b.getString(Name);




    }




}