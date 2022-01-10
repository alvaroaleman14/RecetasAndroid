package com.example.recetas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
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
        AlertDialog.Builder builder = new AlertDialog.Builder(GenerarMenu.this);
        builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                crearMenu(v);
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

    //Esta función en caso de no ser seleccionada devolvería -1
        int radioIDcal = calorias.getCheckedRadioButtonId();
        cal = findViewById(radioIDcal);

        int radioIDgrasa = grasas.getCheckedRadioButtonId();
        fat = findViewById(radioIDgrasa);

        int radioIDprot = proteinas.getCheckedRadioButtonId();
        prot = findViewById(radioIDprot);

        int radioIDcarb = carboh.getCheckedRadioButtonId();
        carb = findViewById(radioIDcarb);

        int radioIDcat = categoria.getCheckedRadioButtonId();
        cat = findViewById(radioIDcat);

        Dish dish = new Dish(GenerarMenu.this);

        dish.consigueMenu(alergenos,cal,prot,fat,carb,cat);


        Bundle b = new Bundle();
       // b.putString(Name, "Prueba");

        Intent intento= new Intent(this, ListarPlatosMenu.class);
       // intento.putExtras(b);
        startActivity(intento);
    }
}