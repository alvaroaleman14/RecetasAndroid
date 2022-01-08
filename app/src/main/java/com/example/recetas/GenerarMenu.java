package com.example.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.recetas.DB.RecipeBook;

public class GenerarMenu extends AppCompatActivity {

    CheckBox range1, range2, range3, gluten, crustacean, egg, fish, driedFruit, soy, dairy, mollusk, mustard, celery, lupine, sesame, sulfurDioxide;
    public final static String Name="Name";
    RecipeBook recipeBook;
//    SQLiteDatabase db = recipeBook.getReadableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_menu);
        /*
        gluten = (CheckBox) findViewById(R.id.glutenFree);
        crustacean = (CheckBox) findViewById(R.id.crust);
        egg = (CheckBox) findViewById(R.id.huevos);
        fish = (CheckBox) findViewById(R.id.pescaro);
        driedFruit = (CheckBox) findViewById(R.id.dfruit);
        soy = (CheckBox) findViewById(R.id.soja);
        dairy = (CheckBox) findViewById(R.id.dairy);
        mollusk = (CheckBox) findViewById(R.id.molusco);
        mustard = (CheckBox) findViewById(R.id.mostaza);
        celery = (CheckBox) findViewById(R.id.celery);
        lupine = (CheckBox) findViewById(R.id.lupine);
        sesame = (CheckBox) findViewById(R.id.sesamo);
        sulfurDioxide = (CheckBox) findViewById(R.id.diox);
        range1 = (CheckBox) findViewById(R.id.range1);
        range2 = (CheckBox) findViewById(R.id.range2);
        range3 = (CheckBox) findViewById(R.id.range3);*/
    }


    public void CrearMenu(View view) {

        //Faltan por pasar filtros

        /*if (range1.isChecked()) {
            String selection = RecipeBook.DishEntry.COLUMN_NAME_CALORIE + " = ?";
            String[] selectionArgs = new String[]{"1500-2500"};
        }
        Bundle b = new Bundle();*/
        // b.putString(Name, "Prueba");

        Intent intento= new Intent(this, ListarPlatosMenu.class);
        // intento.putExtras(b);
        startActivity(intento);

    }

}
