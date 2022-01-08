package com.example.recetas.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class RecipeBook extends SQLiteOpenHelper {


    public static class DishEntry implements BaseColumns {
        public static final String TABLE_NAME = "dish";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_PROTEIN = "protein";
        public static final String COLUMN_NAME_FAT = "fat";
        public static final String COLUMN_NAME_CARBOHYDRATE = "carbohydrate";
        public static final String COLUMN_NAME_CALORIE = "calorie";
        public static final String COLUMN_NAME_ALLERGEN = "allergen";
        public static final String COLUMN_NAME_IS_RESTAURANT = "is_restaurant";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_RECIPE = "recipe";
        public static final String COLUMN_NAME_ID_RESTAURANT = "id_restaurant";
        public static final String COLUMN_NAME_URL = "url";
    }


    public static class RestaurantEntry implements BaseColumns {
        public static final String TABLE_NAME = "restaurant";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_WEB = "web";
    }

    static RecipeBook recipeBook;

    private static final String NAME_BD = "RecipeBook.db";
    private static final int VERSION_BD = 1;

    private static final String SQL_CREATE_DISH_ENTRIES =
            "CREATE TABLE " + DishEntry.TABLE_NAME + " (" +
                    DishEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DishEntry.COLUMN_NAME_NAME + " TEXT NOT NULL," +
                    DishEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    DishEntry.COLUMN_NAME_PROTEIN + " FLOAT," +
                    DishEntry.COLUMN_NAME_FAT + " FLOAT," +
                    DishEntry.COLUMN_NAME_CARBOHYDRATE + " FLOAT," +
                    DishEntry.COLUMN_NAME_CALORIE + " FLOAT," +
                    DishEntry.COLUMN_NAME_ALLERGEN + " TEXT," +
                    DishEntry.COLUMN_NAME_IS_RESTAURANT + " BOOLEAN," +
                    DishEntry.COLUMN_NAME_TYPE + " TEXT," +
                    DishEntry.COLUMN_NAME_RECIPE + " TEXT," +
                    DishEntry.COLUMN_NAME_URL + " TEXT," +
                    DishEntry.COLUMN_NAME_ID_RESTAURANT + " INTEGER REFERENCES "
                    + RestaurantEntry.TABLE_NAME + "(" + RestaurantEntry._ID + "))";
    private static final String SQL_DELETE_DISH_ENTRIES =
            "DROP TABLE IF EXISTS " + DishEntry.TABLE_NAME;

    private static final String SQL_CREATE_RESTAURANT_ENTRIES =
            "CREATE TABLE " + RestaurantEntry.TABLE_NAME + " (" +
                    RestaurantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    RestaurantEntry.COLUMN_NAME_NAME + " TEXT NOT NULL," +
                    RestaurantEntry.COLUMN_NAME_ADDRESS + " TEXT," +
                    RestaurantEntry.COLUMN_NAME_WEB + " TEXT)";
    private static final String SQL_DELETE_RESTAURANT_ENTRIES =
            "DROP TABLE IF EXISTS " + RestaurantEntry.TABLE_NAME;


    //CONSTRUCTOR PRIVADO PARA QUE NO SE PUEDA INVOCAR FUERA DE ESTA CLASE

    private RecipeBook(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    protected RecipeBook(Context context){
        super(context,NAME_BD,null,VERSION_BD);
    }


    //CREA EL LIBRO DE RECETAS, SI ESTA CREADO DEVUELVE SU INSTANCIA
    public static RecipeBook getInstancia(Context contexto) {
        if (recipeBook == null) {
            recipeBook = new RecipeBook(contexto, NAME_BD, null, VERSION_BD);
        }
        return recipeBook;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_DISH_ENTRIES);
        sqLiteDatabase.execSQL(SQL_CREATE_RESTAURANT_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_DISH_ENTRIES);
        sqLiteDatabase.execSQL(SQL_DELETE_RESTAURANT_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
