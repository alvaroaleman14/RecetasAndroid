package com.example.recetas.DB;

import static com.example.recetas.DB.RecipeBook.DishEntry.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.recetas.Enum.Alergenos;
import com.example.recetas.Enum.TipoComida;

import java.util.List;

public class Dish extends RecipeBook {

    Context context;

    public Dish(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public long insertarPlato(String name, String description , Float protein, Float calorie, Float carbohydrate, Float fat, List<Alergenos> allergen,Boolean is_restaurant, TipoComida type, String recipe, String URL, Integer id_restaurant){


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
            values.put("id_restaurant",id_restaurant);

            id = db.insert(TABLE_NAME,null,values);


        }catch (Exception ex){
            ex.toString();
        }

        return id;



    }
}
