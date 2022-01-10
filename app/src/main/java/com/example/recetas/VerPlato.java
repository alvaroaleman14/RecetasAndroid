package com.example.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recetas.DB.Dish;
import com.example.recetas.Entidades.Plato;

public class VerPlato extends AppCompatActivity {

    TextView txtNombre;
    int id=0;
    Plato plato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_plato);

        txtNombre = findViewById(R.id.textName);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        Dish dish = new Dish(VerPlato.this);

        Plato plato=dish.verPlato(id);

        if(plato!=null){

            txtNombre.setText(plato.getName());

        }

    }


}