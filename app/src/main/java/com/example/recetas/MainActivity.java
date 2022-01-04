package com.example.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.recetas.DB.RecipeBook;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipeBook recipeBook = RecipeBook.getInstancia(MainActivity.this);
        SQLiteDatabase db = recipeBook.getWritableDatabase();

    }



    public void Select_InsertarPlato(View view){
        Intent intento= new Intent(this, InsertarPlato.class);
        startActivity(intento);
    }


    public void Select_GenerarMenu(View view){
       // Intent intento= new Intent(this, InsertarPlatoMain.class);
       // startActivity(intento);
    }
}