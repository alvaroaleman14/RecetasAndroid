package com.example.recetas;

import static com.example.recetas.GenerarMenu.Name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ListarPlatosMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_platos_menu);

        Intent intento = getIntent();
        Bundle b = intento.getExtras();
        String Nombre = b.getString(Name);




    }




}