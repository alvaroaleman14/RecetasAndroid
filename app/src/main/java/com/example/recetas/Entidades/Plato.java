package com.example.recetas.Entidades;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.example.recetas.Enum.Alergenos;
import com.example.recetas.Enum.TipoComida;

import java.util.ArrayList;
import java.util.List;

public class Plato implements Parcelable {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String description;
    private Float protein;
    private Float calorie;
    private Float carbohydrate;
    private Float fat;
    private List<Alergenos> allergen;
    private Boolean is_restaurant;
    private List<TipoComida> type;
    private String recipe;
    private String URL;
    private Integer id_restaurant;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Float getCalorie() {
        return calorie;
    }

    public void setCalorie(Float calorie) {
        this.calorie = calorie;
    }

    public Float getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Float carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public Float getFat() {
        return fat;
    }

    public void setFat(Float fat) {
        this.fat = fat;
    }

    public List<Alergenos> getAllergen() {
        return allergen;
    }

    public void setAllergen(List<Alergenos> allergen) {
        this.allergen = allergen;
    }

    public Boolean getIs_restaurant() {
        return is_restaurant;
    }

    public void setIs_restaurant(Boolean is_restaurant) {
        this.is_restaurant = is_restaurant;
    }

    public List<TipoComida> getType() {
        return type;
    }

    public void setType(List<TipoComida> type) {
        this.type = type;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Integer getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(Integer id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public Plato(){

    }

    public static final Parcelable.Creator<Plato> CREATOR = new Parcelable.Creator<Plato>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        public Plato createFromParcel(Parcel parcel) {
            final Plato plato= new Plato();

            plato.setId(parcel.readInt());
            plato.setName(parcel.readString());
            plato.setDescription(parcel.readString());
            plato.setProtein(parcel.readFloat());
            plato.setCalorie(parcel.readFloat());
            plato.setCarbohydrate(parcel.readFloat());
            plato.setFat(parcel.readFloat());
            List<Alergenos> alergenos = new ArrayList<Alergenos>();
            parcel.readList(alergenos,Alergenos.class.getClassLoader());
            plato.setAllergen(alergenos);
            plato.setIs_restaurant(parcel.readBoolean());
            List<TipoComida> tipos= new ArrayList<TipoComida>();
            parcel.readList(tipos,TipoComida.class.getClassLoader());
            plato.setType(tipos);
            plato.setRecipe(parcel.readString());
            plato.setURL(parcel.readString());
            plato.setId_restaurant(parcel.readInt());
            return plato;
        }

        public Plato[] newArray(int size) {
            return new Plato[size];
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeFloat(protein);
        parcel.writeFloat(calorie);
        parcel.writeFloat(carbohydrate);
        parcel.writeFloat(fat);
        parcel.writeList(allergen);
        parcel.writeBoolean(is_restaurant);
        parcel.writeList(type);
        parcel.writeString(recipe);
        parcel.writeString(URL);
        parcel.writeInt(id_restaurant);

    }
}
