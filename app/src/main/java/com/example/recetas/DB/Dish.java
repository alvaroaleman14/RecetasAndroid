package com.example.recetas.DB;

import static com.example.recetas.DB.RecipeBook.DishEntry.*;

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
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Dish extends RecipeBook {

    Context context;

    public Dish(@Nullable Context context){
        super(context);
        this.context=context;
    }

    //DEVUELVE LOS PLATOS QUE COINCIDAN CON EL NOMBRE
    public List<Plato> conseguirPLato(String name){

        SQLiteDatabase db = RecipeBook.getInstancia(context).getWritableDatabase();
        String selection = DishEntry.COLUMN_NAME_NAME + " LIKE ?";
        String argSelection[] = new String[]{name + "%"};
        String orderBy = DishEntry.COLUMN_NAME_NAME + " COLLATE NOCASE ASC";

        Cursor cursor = db.query(DishEntry.TABLE_NAME, null, selection, argSelection, null, null, orderBy);

        Plato plato;
        List<Plato> listDish = new LinkedList<>();

        if(cursor.moveToFirst()){
            do{
                plato= new Plato();
                plato.setId(cursor.getInt(0));
                plato.setName(cursor.getString(1));
                plato.setDescription(cursor.getString(2));
                plato.setProtein(cursor.getFloat(3));
                plato.setFat(cursor.getFloat(4));
                plato.setCarbohydrate(cursor.getFloat(5));
                plato.setCalorie(cursor.getFloat(6));

                //Alérgenos
                String alergenosString= cursor.getString(7);
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
                plato.setIs_restaurant(Boolean.valueOf(cursor.getString(8)));

                //Tipo Comida
                String tipoComidaString = cursor.getString(9);
                List<TipoComida> tipoComida;
                if (tipoComidaString.equals("[]")){
                    tipoComida = new ArrayList<>();
                } else {
                    String tipoComidaStringNoBrackets=tipoComidaString.substring(1,tipoComidaString.length()-1);
                    tipoComida = Arrays.asList(tipoComidaStringNoBrackets.split(",\\s+"))
                            .stream()
                            .map(TipoComida::valueOf)
                            .collect(Collectors.toList());
                }
                plato.setType(tipoComida);

                plato.setRecipe(cursor.getString(10));
                plato.setURL(cursor.getString(11));
                plato.setId_restaurant(cursor.getInt(12));

                listDish.add(plato);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listDish;
    }


    public Plato verPlato(int id){
        SQLiteDatabase db = RecipeBook.getInstancia(context).getWritableDatabase();

        String selection = COLUMN_NAME_ID + " LIKE ?";
        String argSelection[] = new String[]{id + "%"};
        String orderBy = COLUMN_NAME_ID+ " COLLATE NOCASE ASC";

        Cursor cursor = db.query(DishEntry.TABLE_NAME, null, selection, argSelection, null, null, orderBy);

        Plato plato;
        List<Plato> listDish = new LinkedList<>();


        if(cursor.moveToFirst()) {
            do {
                plato = new Plato();
                plato.setId(cursor.getInt(0));
                plato.setName(cursor.getString(1));
                plato.setDescription(cursor.getString(2));
                plato.setProtein(cursor.getFloat(3));
                plato.setFat(cursor.getFloat(4));
                plato.setCarbohydrate(cursor.getFloat(5));
                plato.setCalorie(cursor.getFloat(6));

                //Alérgenos
                String alergenosString = cursor.getString(7);
                List<Alergenos> alergenos;
                if (alergenosString.equals("[]")) {
                    alergenos = new ArrayList<>();
                } else {
                    String alergenosStringNoBrackets = alergenosString.substring(1, alergenosString.length() - 1);
                    alergenos = Arrays.asList(alergenosStringNoBrackets.split(",\\s+"))
                            .stream()
                            .map(Alergenos::valueOf)
                            .collect(Collectors.toList());
                }
                plato.setAllergen(alergenos);
                plato.setIs_restaurant(Boolean.valueOf(cursor.getString(8)));

                //Tipo Comida
                String tipoComidaString = cursor.getString(9);
                List<TipoComida> tipoComida;
                if (tipoComidaString.equals("[]")) {
                    tipoComida = new ArrayList<>();
                } else {
                    String tipoComidaStringNoBrackets = tipoComidaString.substring(1, tipoComidaString.length() - 1);
                    tipoComida = Arrays.asList(tipoComidaStringNoBrackets.split(",\\s+"))
                            .stream()
                            .map(TipoComida::valueOf)
                            .collect(Collectors.toList());
                }
                plato.setType(tipoComida);

                plato.setRecipe(cursor.getString(10));
                plato.setURL(cursor.getString(11));
                plato.setId_restaurant(cursor.getInt(12));

                listDish.add(plato);

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return listDish.get(0);


    }

    //INSERTA O ACTUALIZA UN PLATO
    public long insertarPlato(String name, String description , Float protein, Float calorie, Float carbohydrate, Float fat, List<Alergenos> allergen,Boolean is_restaurant, List<TipoComida> type, String recipe, String URL, Integer id_Restaurant){

        long id = 0;
        List<Plato> listDish = conseguirPLato(name);

        try{

            RecipeBook recipeBook = RecipeBook.getInstancia(context);
            SQLiteDatabase db = recipeBook.getWritableDatabase();

            ContentValues values= new ContentValues();
            values.put(DishEntry.COLUMN_NAME_NAME,name);
            values.put(COLUMN_NAME_DESCRIPTION,description);
            values.put(COLUMN_NAME_PROTEIN,protein);
            values.put(COLUMN_NAME_CALORIE,calorie);
            values.put(COLUMN_NAME_CARBOHYDRATE,carbohydrate);
            values.put(COLUMN_NAME_FAT,fat);
            values.put(COLUMN_NAME_ALLERGEN,allergen.toString());
            values.put(COLUMN_NAME_IS_RESTAURANT,is_restaurant.toString());
            values.put(COLUMN_NAME_TYPE,type.toString());
            values.put(COLUMN_NAME_RECIPE,recipe);
            values.put(COLUMN_NAME_URL,URL);
            values.put(COLUMN_NAME_ID_RESTAURANT,id_Restaurant);

            if (listDish == null || listDish.isEmpty()){
                id = db.insert(DishEntry.TABLE_NAME,null,values);
            } else {
                String selection = DishEntry.COLUMN_NAME_NAME + " = ?";
                String argSelection[] = new String[]{name};
                id = db.update(DishEntry.TABLE_NAME, values, selection, argSelection);
            }
            db.close();
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public ArrayList<Plato> consigueMenu(List list, float minCal,float maxCal,float minProt, float maxProt, float minFat, float maxFat, float minCarb, float maxCarb, boolean isRes){
        SQLiteDatabase db = RecipeBook.getInstancia(context).getWritableDatabase();
        String selection = "(" + COLUMN_NAME_ALLERGEN + " LIKE ?) AND (" + COLUMN_NAME_CALORIE + " BETWEEN ? AND ?) AND (" + COLUMN_NAME_PROTEIN + " BETWEEN ? AND ?) AND (" + COLUMN_NAME_FAT + " BETWEEN ? AND ?) AND ("
                + COLUMN_NAME_CARBOHYDRATE +" BETWEEN ? AND ?) AND (" + COLUMN_NAME_IS_RESTAURANT + " = ?)";
        String argSelection[] = new String[]{list.toString(),String.valueOf(minCal),String.valueOf(maxCal),String.valueOf(minProt),String.valueOf(maxProt),String.valueOf(minFat),String.valueOf(maxFat),String.valueOf(minCarb),String.valueOf(maxCarb),String.valueOf(isRes)};
        String orderBy = DishEntry.COLUMN_NAME_NAME + " COLLATE NOCASE ASC";

        Cursor cursor = db.query(DishEntry.TABLE_NAME, null, selection, argSelection, null, null, orderBy);

        Plato plato;
        ArrayList<Plato> listDish = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                plato= new Plato();
                plato.setId(cursor.getInt(0));
                plato.setName(cursor.getString(1));
                plato.setDescription(cursor.getString(2));
                plato.setProtein(cursor.getFloat(3));
                plato.setFat(cursor.getFloat(4));
                plato.setCarbohydrate(cursor.getFloat(5));
                plato.setCalorie(cursor.getFloat(6));

                //Alérgenos
                String alergenosString= cursor.getString(7);
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
                plato.setIs_restaurant(Boolean.valueOf(cursor.getString(8)));

                //Tipo Comida
                String tipoComidaString = cursor.getString(9);
                List<TipoComida> tipoComida;
                if (tipoComidaString.equals("[]")){
                    tipoComida = new ArrayList<>();
                } else {
                    String tipoComidaStringNoBrackets=tipoComidaString.substring(1,tipoComidaString.length()-1);
                    tipoComida = Arrays.asList(tipoComidaStringNoBrackets.split(",\\s+"))
                            .stream()
                            .map(TipoComida::valueOf)
                            .collect(Collectors.toList());
                }
                plato.setType(tipoComida);

                plato.setRecipe(cursor.getString(10));
                plato.setURL(cursor.getString(11));
                plato.setId_restaurant(cursor.getInt(12));

                listDish.add(plato);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listDish;
    }



}
