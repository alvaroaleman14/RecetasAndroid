package com.example.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.recetas.DB.Dish;
import com.example.recetas.Enum.Alergenos;
import com.example.recetas.Enum.TipoComida;

import java.util.ArrayList;
import java.util.List;

public class InsertarPlato extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_plato);
    }


    public void CrearPrueba(View view){



        EditText Nombre;
        String Descripcion, Receta, Enlaces;
        float Proteinas, Calorias, HidratosCarbono, Grasa;
        boolean Casero;
        TipoComida Tipo;
        Integer Id_Restaurant;
        List<Alergenos> Alergenos= new ArrayList<>();

        Nombre=findViewById(R.id.NombrePlato);

        //Queda por hacer
        Descripcion="Descripcion";
        Proteinas= new Float(2.2);
        Calorias=new Float(2.4);
        HidratosCarbono=new Float(2.2);
        Grasa= new Float(2.2);
        Casero= true;
        Tipo= TipoComida.ALMUERZO;
        Receta= "Receta";
        Enlaces= "Enlaces";
        Id_Restaurant=null;



        Dish dish = new Dish(InsertarPlato.this);
        long id= dish.insertarPlato(Nombre.getText().toString(),Descripcion,Proteinas,Calorias,HidratosCarbono,Grasa,Alergenos,Casero,Tipo,Receta,Enlaces,Id_Restaurant);

    }
}