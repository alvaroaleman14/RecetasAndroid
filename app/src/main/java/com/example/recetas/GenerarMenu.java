package com.example.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GenerarMenu extends AppCompatActivity {

    public final static String Name="Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_menu);
    }


    public void CrearMenu(View view){

        //Faltan por pasar filtros

        Bundle b = new Bundle();
        b.putString(Name, "Prueba");

        Intent intento= new Intent(this, ListarPlatosMenu.class);
        intento.putExtras(b);
        startActivity(intento);
    }
}