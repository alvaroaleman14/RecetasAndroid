package com.example.recetas.DB;

import static com.example.recetas.DB.RecipeBook.RestaurantEntry.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.recetas.Entidades.Plato;
import com.example.recetas.Entidades.Restaurante;
import com.example.recetas.Enum.Alergenos;
import com.example.recetas.Enum.TipoComida;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Restaurant extends RecipeBook {

    Context context;

    public Restaurant(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public List<Restaurante> conseguirRestaurante(String name) {

        SQLiteDatabase db = RecipeBook.getInstancia(context).getWritableDatabase();
        String selection = COLUMN_NAME_NAME + " LIKE ?";
        String argSelection[] = new String[]{name + "%"};
        String orderBy = COLUMN_NAME_NAME + " COLLATE NOCASE ASC";

        Cursor cursor = db.query(TABLE_NAME, null, selection, argSelection, null, null, orderBy);

        Restaurante restaurante;
        List<Restaurante> listRestaurant = new LinkedList<>();

        if (cursor.moveToFirst()) {
            do {
                restaurante = new Restaurante();
                restaurante.setId(cursor.getInt(0));
                restaurante.setName(cursor.getString(1));
                restaurante.setAddress(cursor.getString(2));
                restaurante.setWeb(cursor.getString(3));

                listRestaurant.add(restaurante);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listRestaurant;
    }

    public Restaurante conseguirRestauranteId(int id) {

        SQLiteDatabase db = RecipeBook.getInstancia(context).getWritableDatabase();
        String selection = COLUMN_NAME_ID + " LIKE ?";
        String argSelection[] = new String[]{id + "%"};
        String orderBy = COLUMN_NAME_ID + " COLLATE NOCASE ASC";

        Cursor cursor = db.query(TABLE_NAME, null, selection, argSelection, null, null, orderBy);

        Restaurante restaurante;
        List<Restaurante> listRestaurant = new LinkedList<>();

        if (cursor.moveToFirst()) {
            do {
                restaurante = new Restaurante();
                restaurante.setId(cursor.getInt(0));
                restaurante.setName(cursor.getString(1));
                restaurante.setAddress(cursor.getString(2));
                restaurante.setWeb(cursor.getString(3));

                listRestaurant.add(restaurante);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listRestaurant.get(0);
    }

    public long insertarRestaurante(String name, String address, String web) {

        long id = 0;


        List<Restaurante> listRestaurantes = conseguirRestaurante(name);

        try {

            RecipeBook recipeBook = RecipeBook.getInstancia(context);
            SQLiteDatabase db = recipeBook.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_NAME, name);
            values.put(COLUMN_NAME_ADDRESS, address);
            values.put(COLUMN_NAME_WEB, web);


            if (listRestaurantes == null || listRestaurantes.isEmpty()) {
                id = db.insert(TABLE_NAME, null, values);
            } else {
                String selection = COLUMN_NAME_NAME + " = ?";
                String argSelection[] = new String[]{name};
                id = listRestaurantes.get(0).getId();
            }

            db.close();
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }
}
