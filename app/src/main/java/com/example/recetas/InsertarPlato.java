package com.example.recetas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recetas.DB.Dish;
import com.example.recetas.DB.Restaurant;
import com.example.recetas.Entidades.Restaurante;
import com.example.recetas.Enum.Alergenos;
import com.example.recetas.Enum.TipoComida;

import java.util.ArrayList;
import java.util.List;

public class InsertarPlato extends AppCompatActivity {

    EditText dishName, descriptionDish, protein, calorie, carbohydrate, fat, restaurantName, address, web, recipe, linkInterest;
    CheckBox breakfast, lunch, dinner, gluten, crustacean, egg, fish, driedFruit, soy, dairy, mollusk, mustard, celery, lupine, sesame, sulfurDioxide;
    Button addDish, cancel;
    Switch isInRestaurant;
    TextView tvRestaurantName, tvAddress, tvWeb, tvRecipe, tvLinkInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_plato);

        dishName = (EditText) findViewById(R.id.editTextDishName);
        descriptionDish = (EditText) findViewById(R.id.editTextDescriptionDish);
        protein = (EditText) findViewById(R.id.editTextProtein);
        calorie = (EditText) findViewById(R.id.editTextCalorie);
        carbohydrate = (EditText) findViewById(R.id.editTextCarbohydrate);
        fat = (EditText) findViewById(R.id.editTextFat);

        breakfast = (CheckBox) findViewById(R.id.checkBoxBreakfast);
        lunch = (CheckBox) findViewById(R.id.checkBoxLunch);
        dinner = (CheckBox) findViewById(R.id.checkBoxDinner);

        gluten = (CheckBox) findViewById(R.id.checkBoxGluten);
        crustacean = (CheckBox) findViewById(R.id.checkBoxCrustacean);
        egg = (CheckBox) findViewById(R.id.checkBoxEgg);
        fish = (CheckBox) findViewById(R.id.checkBoxFish);
        driedFruit = (CheckBox) findViewById(R.id.checkBoxDriedFruit);
        soy = (CheckBox) findViewById(R.id.checkBoxSoy);
        dairy = (CheckBox) findViewById(R.id.checkBoxDairy);
        mollusk = (CheckBox) findViewById(R.id.checkBoxMollusk);
        mustard = (CheckBox) findViewById(R.id.checkBoxMustard);
        celery = (CheckBox) findViewById(R.id.checkBoxCelery);
        lupine = (CheckBox) findViewById(R.id.checkBoxLupine);
        sesame = (CheckBox) findViewById(R.id.checkBoxSesame);
        sulfurDioxide = (CheckBox) findViewById(R.id.checkBoxSulfurDioxide);

        addDish = (Button) findViewById(R.id.buttonAddDish);
        cancel = (Button) findViewById(R.id.buttonCancelDish);

        //ELEMENTOS QUE SE MOSTRARAN O NO DEPENDIENDO SI EL PLATO ES DE RESTAURANTE O NO
        tvRestaurantName = (TextView) findViewById(R.id.textViewRestaurantName);
        tvRestaurantName.setVisibility(View.GONE);
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

        restaurantName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length()>0) {
                    Restaurant restaurant = new Restaurant(InsertarPlato.this);
                    List<Restaurante> lookForRestaurant = restaurant.conseguirRestaurante(editable.toString());
                    if (!lookForRestaurant.isEmpty()) {
                        address.setText(lookForRestaurant.get(0).getAddress());
                        web.setText(lookForRestaurant.get(0).getWeb());
                    } else {
                        address.setText("");
                        web.setText("");
                    }
                }

            }
        });

        isInRestaurant = (Switch) findViewById(R.id.switchIsInRestaurant);
        isInRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInRestaurant.isChecked()){
                    tvRestaurantName.setVisibility(View.VISIBLE);
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
                    tvRestaurantName.setVisibility(View.GONE);
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

    //AÑADE EL PLATO
    public void onClickAdd(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(InsertarPlato.this);
        builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                setAddDish(v);
            }
        });
        builder.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.setTitle(R.string.AddDishMessageTitle);
        builder.setMessage(R.string.AddDishMessage);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //VUELVE A LA ACTIVIDAD PRINCIPAL
    public void onClickCancel(View v){
        Intent attempt = new Intent(this, MainActivity.class);
        startActivity(attempt);
        finish();
    }

    //OBTIENE LOS VALORES DEL FORMULARIO PARA INSERTARLOS
    public void setAddDish(View view){
        //PLATO
        String dishName, descriptionDish, recipe, linkInterest;
        List<Alergenos> allergens = new ArrayList<>();
        List<TipoComida> foodDay = new ArrayList<>();
        float protein, calorie, carbohydrate, fat;
        boolean isInRestaurant;

        long idDish;

        //RESTAURANTE
        String restaurantName, address, web;

        dishName = this.dishName.getText().toString();
        descriptionDish = this.descriptionDish.getText().toString();
        linkInterest = this.linkInterest.getText().toString();

        if (this.protein.getText().toString().length()>0){
            protein = Float.valueOf(this.protein.getText().toString());
        } else {
            protein = 0;
        }
        if (this.calorie.getText().toString().length()>0){
            calorie = Float.valueOf(this.calorie.getText().toString());
        } else {
            calorie = 0;
        }
        if (this.carbohydrate.getText().toString().length()>0){
            carbohydrate = Float.valueOf(this.carbohydrate.getText().toString());
        } else {
            carbohydrate = 0;
        }
        if (this.fat.getText().toString().length()>0){
            fat = Float.valueOf(this.fat.getText().toString());
        } else {
            fat = 0;
        }

        if (this.gluten.isChecked()){allergens.add(Alergenos.Glutén);}
        if (this.crustacean.isChecked()){allergens.add(Alergenos.Crustaceos);}
        if (this.egg.isChecked()){allergens.add(Alergenos.Huevo);}
        if (this.fish.isChecked()){allergens.add(Alergenos.Pescado);}
        if (this.driedFruit.isChecked()){allergens.add(Alergenos.Frutos_Secos);}
        if (this.soy.isChecked()){allergens.add(Alergenos.Soja);}
        if (this.dairy.isChecked()){allergens.add(Alergenos.Lacteos);}
        if (this.mollusk.isChecked()){allergens.add(Alergenos.Moluscos);}
        if (this.mustard.isChecked()){allergens.add(Alergenos.Mostaza);}
        if (this.celery.isChecked()){allergens.add(Alergenos.Apio);}
        if (this.lupine.isChecked()){allergens.add(Alergenos.Altramuces);}
        if (this.sesame.isChecked()){allergens.add(Alergenos.Sesamo);}
        if (this.sulfurDioxide.isChecked()){allergens.add(Alergenos.Dióxido_De_Azufre);}

        if (this.breakfast.isChecked()){foodDay.add(TipoComida.Desayuno);}
        if (this.lunch.isChecked()){foodDay.add(TipoComida.Almuerzo);}
        if (this.dinner.isChecked()){foodDay.add(TipoComida.Cena);}

        Dish dish = new Dish(InsertarPlato.this);
        Restaurant restaurant = new Restaurant(InsertarPlato.this);
        if (this.isInRestaurant.isChecked()) {
            isInRestaurant = true;
            restaurantName = this.restaurantName.getText().toString();
            address = this.address.getText().toString();
            web = this.web.getText().toString();
            long idRestaurant;
            if (!restaurantName.equals("")){
                idRestaurant= restaurant.insertarRestaurante(restaurantName,address,web);
                if (idRestaurant != -1) {
                    if (!dishName.equals("")){
                        idDish = dish.insertarPlato(dishName,descriptionDish,protein,calorie,carbohydrate,fat,allergens,isInRestaurant,foodDay,"","", (int) idRestaurant);
                        if (idDish != -1) {
                            onClickCancel(view);
                            Toast.makeText(getApplicationContext(), R.string.AddDishSuccessfully, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.AddDishError, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(InsertarPlato.this);
                        builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                        builder.setTitle(R.string.Error);
                        builder.setMessage(R.string.ErrorMessageDishName);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.AddRestaurantError, Toast.LENGTH_SHORT).show();
                }
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(InsertarPlato.this);
                builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.setTitle(R.string.Error);
                builder.setMessage(R.string.ErrorMessageRestaurantName);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        } else {
            isInRestaurant = false;
            recipe = this.recipe.getText().toString();
            linkInterest = this.linkInterest.getText().toString();
            if (!dishName.equals("")){
                idDish = dish.insertarPlato(dishName,descriptionDish,protein,calorie,carbohydrate,fat,allergens,isInRestaurant,foodDay,recipe,linkInterest,null);
                if (idDish != -1) {
                    onClickCancel(view);
                    Toast.makeText(getApplicationContext(), R.string.AddDishSuccessfully, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.AddDishError, Toast.LENGTH_SHORT).show();
                }
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(InsertarPlato.this);
                builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.setTitle(R.string.Error);
                builder.setMessage(R.string.ErrorMessageDishName);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

    }
}