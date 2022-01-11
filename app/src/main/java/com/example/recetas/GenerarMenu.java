package com.example.recetas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.recetas.DB.Dish;
import com.example.recetas.DB.RecipeBook;
import com.example.recetas.Entidades.Plato;
import com.example.recetas.Enum.Alergenos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerarMenu extends AppCompatActivity {
    CheckBox gluten, crustacean, egg, fish, driedFruit, soy, dairy, mollusk, mustard, celery, lupine, sesame, sulfurDioxide;
    RadioButton cal,carb,prot,fat,cat;
    RadioGroup calorias, proteinas,grasas, carboh, categoria;

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

        //RadioGroups
        calorias = (RadioGroup) findViewById(R.id.RadioGroupCalorie);
        grasas = (RadioGroup) findViewById(R.id.RadioGroupFat);
        proteinas = (RadioGroup) findViewById(R.id.RadioGroupProtein);
        carboh = (RadioGroup) findViewById(R.id.RadioGroupCarbohydrate);
        categoria = (RadioGroup) findViewById(R.id.RadioGroupCategory);

    }

    public void onClickListMenu(View v){
        if (calorias.getCheckedRadioButtonId() == -1 ||
                grasas.getCheckedRadioButtonId() == -1 ||
                proteinas.getCheckedRadioButtonId() == -1 ||
                carboh.getCheckedRadioButtonId() == -1 ||
                categoria.getCheckedRadioButtonId() == -1 ){

            AlertDialog.Builder builder = new AlertDialog.Builder(GenerarMenu.this);
            builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            builder.setTitle(R.string.Error);
            if (calorias.getCheckedRadioButtonId() == -1) {
                builder.setMessage(R.string.ErrorMessageCalorie);
            } else if (proteinas.getCheckedRadioButtonId() == -1) {
                builder.setMessage(R.string.ErrorMessageProtein);
            } else if (grasas.getCheckedRadioButtonId() == -1) {
                builder.setMessage(R.string.ErrorMessageFat);
            } else if (carboh.getCheckedRadioButtonId() == -1) {
                builder.setMessage(R.string.ErrorMessageCarbohydrate);
            } else if (categoria.getCheckedRadioButtonId() == -1) {
                builder.setMessage(R.string.ErrorMessageCategory);
            }
            AlertDialog dialog = builder.create();
            dialog.show();

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(GenerarMenu.this);
            builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    crearMenu(v);
                    Toast.makeText(getApplicationContext(), R.string.ListMenuSuccessfully, Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            builder.setTitle(R.string.ListMenuMessageTitle);
            builder.setMessage(R.string.ListMenuMessage);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void crearMenu(View view) {

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

        alergenos.toString();

        //Calorias
        float minCal=0,maxCal=0;
        int radioIDcal = calorias.getCheckedRadioButtonId();
        if (radioIDcal == R.id.rango1cal){
            minCal = 0;
            maxCal = 1500;
        }else if (radioIDcal == R.id.rango2cal){
            minCal = 1500;
            maxCal = 2500;
        }else if (radioIDcal == R.id.rango3cal){
            minCal = 2500;
            maxCal = 10000;
        }

        //Grasas
        float minFat=0,maxFat=0;
        int radioIDgrasa = grasas.getCheckedRadioButtonId();
        if (radioIDgrasa == R.id.rango1fat){
            minFat = 0;
            maxFat = 50;
        }else if (radioIDgrasa == R.id.rango2fat){
            minFat = 50;
            maxFat = 100;
        }else if (radioIDgrasa == R.id.rango3fat){
            minFat = 100;
            maxFat = 1000;
        }

        //Proteinas
        float minProt=0,maxProt=0;
        int radioIDprot = proteinas.getCheckedRadioButtonId();
        if (radioIDprot == R.id.rango1prot){
            minProt = 0;
            maxProt= 100;
        }else if (radioIDprot == R.id.rango2prot){
            minProt = 100;
            maxProt = 200;
        }else if (radioIDprot == R.id.rango3prot){
            minProt = 200;
            maxProt = 5000;
        }

        //Carbohidratos
        float minCarb=0,maxCarb=0;
        int radioIDcarb = carboh.getCheckedRadioButtonId();
        if (radioIDcarb == R.id.rango1carb){
            minCarb= 0;
            maxCarb= 100;
        }else if (radioIDcarb == R.id.rango2carb){
            minCarb = 100;
            maxCarb = 200;
        }else if (radioIDcarb == R.id.rango3carb){
            minCarb = 200;
            maxCarb = 5000;
        }

        //Categoria
        boolean isRes = false;
        int radioIDcat = categoria.getCheckedRadioButtonId();
        if (radioIDcat == R.id.restaurante) {
            isRes = true;
        }

        Dish dish = new Dish(GenerarMenu.this);

        ArrayList<Plato> platosArray = dish.consigueMenu(alergenos,minCal,maxCal,minProt,maxProt,minFat,maxFat,minCarb,maxCarb,isRes);

        Bundle b = new Bundle();
        Intent intento= new Intent(this, ListarPlatosMenu.class);
        intento.putParcelableArrayListExtra("array", (ArrayList<? extends Parcelable>) platosArray);
        startActivity(intento);
    }
}