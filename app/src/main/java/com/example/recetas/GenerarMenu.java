package com.example.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.recetas.DB.Dish;
import com.example.recetas.DB.RecipeBook;
import com.example.recetas.Enum.Alergenos;

import java.util.ArrayList;
import java.util.List;

public class GenerarMenu extends AppCompatActivity {
    Context context;
    CheckBox range1, range2, range3, gluten, crustacean, egg, fish, driedFruit, soy, dairy, mollusk, mustard, celery, lupine, sesame, sulfurDioxide;
    public final static String Name="Name";
    RecipeBook recipeBook = RecipeBook.getInstancia(context);
    SQLiteDatabase db = recipeBook.getReadableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_menu);

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
        range3 = (CheckBox) findViewById(R.id.range3);
    }

    public void compruebaCB (CheckBox checkbox){

        checkbox.toString();

        String selection = RecipeBook.DishEntry.COLUMN_NAME_ALLERGEN + " = ?";
        String [] selectionArg ;
    }


    public void CrearMenu(View view) {

        List<Alergenos> alergenos = new ArrayList<>();

        if (this.gluten.isChecked()){
            alergenos.add(Alergenos.Glutén);
        }
        if (this.crustacean.isChecked()){
            alergenos.add(Alergenos.Crustaceos);
        }
        if (this.egg.isChecked()){
            alergenos.add(Alergenos.Huevo);
        }
        if (this.fish.isChecked()){
            alergenos.add(Alergenos.Pescado);
        }
        if (this.driedFruit.isChecked()){
            alergenos.add(Alergenos.Frutos_Secos);
        }
        if (this.soy.isChecked()){
            alergenos.add(Alergenos.Soja);
        }
        if (this.dairy.isChecked()){
            alergenos.add(Alergenos.Lacteos);
        }
        if (this.mollusk.isChecked()){
            alergenos.add(Alergenos.Moluscos);
        }
        if (this.mustard.isChecked()){
            alergenos.add(Alergenos.Mostaza);}
        if (this.celery.isChecked()){alergenos.add(Alergenos.Apio);
        }
        if (this.lupine.isChecked()){
            alergenos.add(Alergenos.Altramuces);
        }
        if (this.sesame.isChecked()){
            alergenos.add(Alergenos.Sesamo);
        }
        if (this.sulfurDioxide.isChecked()){
            alergenos.add(Alergenos.Dióxido_De_Azufre);
        }

        //alergenos.toString();

        String selection = RecipeBook.DishEntry.COLUMN_NAME_ALLERGEN + " = ?";
        String [] selectionArg = new String[] {alergenos.toString()};


        switch (view.getId()) {
            case R.id.range1:
                range2.setChecked(false);
                range3.setChecked(false);
                String selection1 = RecipeBook.DishEntry.COLUMN_NAME_CALORIE + " = ?";
                String[] selectionArgs1 = new String[]{"1500-2500"};
                Cursor cursor = db.rawQuery("SELECT * FROM DISH WHERE CALORIE BETWEEN 1500 and 2500 ORDER BY CALORIE", selectionArgs1);
                break;

            case R.id.range2:
                range1.setChecked(false);
                range3.setChecked(false);
                String selection2 = RecipeBook.DishEntry.COLUMN_NAME_CALORIE + " = ?";
                String[] selectionArgs2 = new String[]{"2500-3500"};
                Cursor cursor2 = db.rawQuery("SELECT * FROM DISH WHERE CALORIE BETWEEN 2500 and 3500 ORDER BY CALORIE", selectionArgs2);
                break;

            case R.id.range3:
                range1.setChecked(false);
                range2.setChecked(false);
                String selection3 = RecipeBook.DishEntry.COLUMN_NAME_CALORIE + " = ?";
                String[] selectionArgs3 = new String[]{"3500-5000"};
                Cursor cursor3 = db.rawQuery("SELECT * FROM DISH WHERE CALORIE BETWEEN 3500 and 5000 ORDER BY CALORIE", selectionArgs3);
                break;
        }

        Bundle b = new Bundle();
       // b.putString(Name, "Prueba");

        Intent intento= new Intent(this, ListarPlatosMenu.class);
       // intento.putExtras(b);
        startActivity(intento);
    }
}