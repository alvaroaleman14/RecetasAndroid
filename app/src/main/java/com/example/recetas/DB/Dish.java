package com.example.recetas.DB;

import static com.example.recetas.DB.RecipeBook.DishEntry.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.recetas.Entidades.Plato;
import com.example.recetas.Enum.Alergenos;
import com.example.recetas.Enum.TipoComida;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Dish extends RecipeBook {

    Context context;

    public Dish(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public long insertarPlato(String name, String description , Float protein, Float calorie, Float carbohydrate, Float fat, List<Alergenos> allergen,Boolean is_restaurant, List<TipoComida> type, String recipe, String URL){


        long id = 0;


        try{

            RecipeBook recipeBook = RecipeBook.getInstancia(context);
            SQLiteDatabase db = recipeBook.getWritableDatabase();

            ContentValues values= new ContentValues();
            values.put("name",name);
            values.put("description",description);
            values.put("protein",protein);
            values.put("calorie",calorie);
            values.put("carbohydrate",carbohydrate);
            values.put("fat",fat);
            values.put("allergen",allergen.toString());
            values.put("is_restaurant",is_restaurant.toString());
            values.put("type",type.toString());
            values.put("recipe",recipe);
            values.put("url",URL);


            id = db.insert(TABLE_NAME,null,values);


        }catch (Exception ex){
            ex.toString();
        }

        return id;



    }


    public ArrayList<Plato> mostrarPlatos(){

        RecipeBook dbHelper = new RecipeBook(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Plato> listaPlatos = new ArrayList<>();
        Plato plato;
        Cursor cursorPlatos;

        cursorPlatos = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);


        if(cursorPlatos.moveToFirst()){
            do{
                plato= new Plato();
                plato.setId(cursorPlatos.getInt(0));
                plato.setName(cursorPlatos.getString(1));
                plato.setDescription(cursorPlatos.getString(2));
                plato.setProtein(cursorPlatos.getFloat(3));
                plato.setFat(cursorPlatos.getFloat(4));
                plato.setCarbohydrate(cursorPlatos.getFloat(5));
                plato.setCalorie(cursorPlatos.getFloat(6));


                //Alérgenos
                String alergenosString= cursorPlatos.getString(7);
                List<Alergenos> alergenos;

                if(alergenosString.equals("[]")){
                    alergenos=new ArrayList<>();
                }else{
                    String alergenosStringNoBrackets=alergenosString.substring(1,alergenosString.length()-1);
                    alergenos = Arrays.asList(alergenosStringNoBrackets.split(",\\s+"))
                            .stream()
                            .map(Alergenos::valueOf)
                            .collect(Collectors.toList());
                }



                plato.setAllergen(alergenos);
                //---------


                plato.setIs_restaurant(Boolean.valueOf(cursorPlatos.getString(8)));

                //Tipo Comida
                String tipo = cursorPlatos.getString(9);

                TipoComida tipoComida=TipoComida.valueOf(tipo);

                plato.setType(tipoComida);
                //---------

                plato.setRecipe(cursorPlatos.getString(10));
                plato.setURL(cursorPlatos.getString(11));
                plato.setId_restaurant(cursorPlatos.getInt(12));


                listaPlatos.add(plato);

            }while(cursorPlatos.moveToNext());
        }

        cursorPlatos.close();

        return listaPlatos;

    }



}
