package com.example.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.recetas.DB.Dish;
import com.example.recetas.DB.RecipeBook;
import com.example.recetas.Enum.Alergenos;

import java.util.ArrayList;
import java.util.List;

public class GenerarMenu extends AppCompatActivity {
    CheckBox gluten, crustacean, egg, fish, driedFruit, soy, dairy, mollusk, mustard, celery, lupine, sesame, sulfurDioxide;
    RadioButton cal1,cal2,cal3,carb1,carb2,carb3,prot1,prot2,prot3,fat1,fat2,fat3;

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
/*
        //RadioButtons calorias
        cal1 = (RadioButton) findViewById(R.id.rango1cal);
        cal2 = (RadioButton) findViewById(R.id.rango2cal);
        cal3 = (RadioButton) findViewById(R.id.rango3cal);

        //RadioButtons carbohidratos
        carb1 = (RadioButton) findViewById(R.id.rango1carb);
        carb2 = (RadioButton) findViewById(R.id.rango2carb);
        carb3 = (RadioButton) findViewById(R.id.rango3carb);

        //RadioButtons proteinas
        prot1 = (RadioButton) findViewById(R.id.rango1prot);
        prot2 = (RadioButton) findViewById(R.id.rango2prot);
        prot3 = (RadioButton) findViewById(R.id.rango3prot);

        //RadioButtons grasas
        fat1 = (RadioButton) findViewById(R.id.rango1fat);
        fat2 = (RadioButton) findViewById(R.id.rango2fat);
        fat3 = (RadioButton) findViewById(R.id.rango3fat);*/
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
            alergenos.add(Alergenos.Mostaza);
        }
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

        if (alergenos.size() != 0){
            String selection = RecipeBook.DishEntry.COLUMN_NAME_ALLERGEN + " = ?";
            String [] selectionArg = new String[] {alergenos.toString()};
            String orderBy = RecipeBook.DishEntry.COLUMN_NAME_ALLERGEN;
           // Cursor cursor = db.query(RecipeBook.DishEntry.TABLE_NAME, RecipeBook.DishEntry.COLUMN_NAME_ALLERGEN, selection, selectionArg, null, null, orderBy);
        }


    /*

        switch (view.getId()) {
            case R.id.range1:
                range2.setChecked(false);
                range3.setChecked(false);
                String selection1 = RecipeBook.DishEntry.COLUMN_NAME_CALORIE + " = ?";
                String[] selectionArgs1 = new String[]{"0-1500"};
                Cursor cursor = db.rawQuery("SELECT * FROM DISH WHERE CALORIE BETWEEN 0 and 1500 ORDER BY CALORIE", selectionArgs1);
                break;

            case R.id.range2:
                range1.setChecked(false);
                range3.setChecked(false);
                String selection2 = RecipeBook.DishEntry.COLUMN_NAME_CALORIE + " = ?";
                String[] selectionArgs2 = new String[]{"1500-2500"};
                Cursor cursor2 = db.rawQuery("SELECT * FROM DISH WHERE CALORIE BETWEEN 1500 and 2500 ORDER BY CALORIE", selectionArgs2);
                break;


            case R.id.range3:
                range1.setChecked(false);
                range2.setChecked(false);
                String selection3 = RecipeBook.DishEntry.COLUMN_NAME_CALORIE + " = ?";
                String[] selectionArgs3 = new String[]{"3500-5000"};
                Cursor cursor3 = db.rawQuery("SELECT * FROM DISH WHERE CALORIE > 2500 ORDER BY CALORIE", selectionArgs3);
                break;
        }
        */
        Bundle b = new Bundle();
       // b.putString(Name, "Prueba");

        Intent intento= new Intent(this, ListarPlatosMenu.class);
       // intento.putExtras(b);
        startActivity(intento);
    }
}