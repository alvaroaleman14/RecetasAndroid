package com.example.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.recetas.DB.Dish;
import com.example.recetas.Enum.Alergenos;
import com.example.recetas.Enum.TipoComida;

import java.util.ArrayList;
import java.util.List;

public class InsertarPlato extends AppCompatActivity {

    EditText dishName, descriptionDish, protein, calorie, carbohydrate, fat, restaurantName, address, web, recipe, linkInterest;
    CheckBox gluten, crustacean, egg, fish, driedFruit, soy, dairy, mollusk, mustard, celery, lupine, sesame, sulfurDioxide;
    Button addDish, cancel;
    Switch isInRestaurant;
    TextView tvRestauranName, tvAddress, tvWeb, tvRecipe, tvLinkInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_plato);

        tvRestauranName = (TextView) findViewById(R.id.textViewRestaurantName);
        tvRestauranName.setVisibility(View.GONE);
        restaurantName = (EditText) findViewById(R.id.editTextRestaurantName);
        restaurantName.setVisibility(View.GONE);
        tvAddress = (TextView) findViewById(R.id.textViewAddress);
        tvAddress.setVisibility(View.GONE);
        address = (EditText) findViewById(R.id.editTextAddress);
        address.setVisibility(View.GONE);
        tvWeb = (TextView) findViewById(R.id.textViewWeb);
        tvWeb.setVisibility(View.GONE);
        web = (EditText) findViewById(R.id.editTextWeb);
        web.setVisibility(View.GONE);
        tvRecipe = (TextView) findViewById(R.id.textViewRecipe);
        recipe = (EditText) findViewById(R.id.editTextRecipe);
        tvLinkInterest = (TextView) findViewById(R.id.textViewLinkInterest);
        linkInterest = (EditText) findViewById(R.id.editLinkInterest);

        isInRestaurant = (Switch) findViewById(R.id.switchIsInRestaurant);
        isInRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInRestaurant.isChecked()){
                    tvRestauranName.setVisibility(View.VISIBLE);
                    restaurantName.setVisibility(View.VISIBLE);
                    tvAddress.setVisibility(View.VISIBLE);
                    address.setVisibility(View.VISIBLE);
                    tvWeb.setVisibility(View.VISIBLE);
                    web.setVisibility(View.VISIBLE);
                    tvRecipe.setVisibility(View.GONE);
                    recipe.setVisibility(View.GONE);
                    tvLinkInterest.setVisibility(View.GONE);
                    linkInterest.setVisibility(View.GONE);
                } else {
                    tvRestauranName.setVisibility(View.GONE);
                    restaurantName.setVisibility(View.GONE);
                    tvAddress.setVisibility(View.GONE);
                    address.setVisibility(View.GONE);
                    tvWeb.setVisibility(View.GONE);
                    web.setVisibility(View.GONE);
                    tvRecipe.setVisibility(View.VISIBLE);
                    recipe.setVisibility(View.VISIBLE);
                    tvLinkInterest.setVisibility(View.VISIBLE);
                    linkInterest.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    //VUELVE A LA ACTIVIDAD PRINCIPAL
    public void onClickCancel(View v){
        Intent attempt = new Intent(this, MainActivity.class);
        startActivity(attempt);
        finish();
    }

    public void CrearPrueba(View view){



        EditText Nombre;
        String Descripcion, Receta, Enlaces;
        float Proteinas, Calorias, HidratosCarbono, Grasa;
        boolean Restaurante;
        TipoComida Tipo;
        Integer Id_Restaurant;


        List<Alergenos> Alergenos= new ArrayList<>();


        //Nombre=findViewById(R.id.editTextDishName);

        //Queda por hacer

        Alergenos.add(com.example.recetas.Enum.Alergenos.Apio);
        Alergenos.add(com.example.recetas.Enum.Alergenos.Mostaza);
        Descripcion="Descripcion";
        Proteinas= new Float(2.2);
        Calorias=new Float(2.4);
        HidratosCarbono=new Float(2.2);
        Grasa= new Float(2.2);
        Restaurante= false;
        Tipo= TipoComida.Desayuno;
        Receta= "Receta";
        Enlaces= "Enlaces";
        Id_Restaurant=null;



        Dish dish = new Dish(InsertarPlato.this);
        //long id= dish.insertarPlato(Nombre.getText().toString(),Descripcion,Proteinas,Calorias,HidratosCarbono,Grasa,Alergenos,Restaurante,Tipo,Receta,Enlaces,Id_Restaurant);

    }
}