package com.example.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.recetas.DB.Dish;
import com.example.recetas.DB.Restaurant;
import com.example.recetas.Entidades.Plato;
import com.example.recetas.Entidades.Restaurante;
import com.example.recetas.Enum.Alergenos;
import com.example.recetas.Enum.TipoComida;

import java.util.ArrayList;
import java.util.List;

public class VerPlato extends AppCompatActivity {

    TextView txtNombre,txtDescripcion,txtProteina,txtGrasa,txtCarbohidrato,txtCalorie,txtAllergen,txtCategory,txtType,txtRecipe,txtURL,txtNombreRestaurante,txtDireccioRestaurante,txtWebRestaurante;
    TextView Receta, URL,NombreRestaurante,DireccionRestaurante, WebRestaurante;
    int id=0;
    Plato plato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_plato);

        txtNombre = findViewById(R.id.textName);
        txtDescripcion=findViewById(R.id.textViewDesciption);
        txtProteina=findViewById(R.id.showProtein);
        txtGrasa=findViewById(R.id.showFat);
        txtCarbohidrato=findViewById(R.id.showCarbohydrate);
        txtCalorie=findViewById(R.id.showCalorie);
        txtAllergen=findViewById(R.id.showAllergen);
        txtCategory=findViewById(R.id.showCategory);
        txtRecipe=findViewById(R.id.showRecipe);
        txtURL=findViewById(R.id.showURL);
        txtType=findViewById(R.id.showType);
        txtNombreRestaurante=findViewById(R.id.showRestaurantName);
        txtDireccioRestaurante=findViewById(R.id.showRestaurantAddress);
        txtWebRestaurante=findViewById(R.id.showWebRestaurant);

        Receta = findViewById(R.id.recetaTxt);
        URL = findViewById(R.id.urlText);
        NombreRestaurante = findViewById(R.id.nombreRestauranteTxt);
        DireccionRestaurante = findViewById(R.id.direccionRestaurantTxt);
        WebRestaurante = findViewById(R.id.webRestauranteTxt);





        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        Dish dish = new Dish(VerPlato.this);

        Plato plato=dish.verPlato(id);

        if(plato!=null){

            txtNombre.setText(plato.getName());
            txtDescripcion.setText(plato.getDescription());
            txtProteina.setText(plato.getProtein().toString());
            txtGrasa.setText(plato.getFat().toString());
            txtCarbohidrato.setText(plato.getCarbohydrate().toString());
            txtCalorie.setText(plato.getCalorie().toString());
            String AlergenosAux= AlergenosTraduccion(plato.getAllergen()).toString();
            txtAllergen.setText(AlergenosAux.substring(1,AlergenosAux.length()-1));
            txtCategory.setText(plato.getIs_restaurant() == true ? this.getString(R.string.Restaurant) : this.getString(R.string.Homemade));
            String TipoAux= TipoComidaTraduccion(plato.getType()).toString();
            txtType.setText(TipoAux.substring(1,TipoAux.length()-1));
            if(plato.getIs_restaurant()){
                txtRecipe.setVisibility(View.GONE);
                txtURL.setVisibility(View.GONE);
                Receta.setVisibility(View.GONE);
                URL.setVisibility(View.GONE);
                Restaurant restaurant = new Restaurant(VerPlato.this);
                Restaurante restaurante=restaurant.conseguirRestauranteId(plato.getId_restaurant());
                txtNombreRestaurante.setText(restaurante.getName());
                txtDireccioRestaurante.setText(restaurante.getAddress());
                txtWebRestaurante.setText(restaurante.getWeb());
                txtWebRestaurante.setMovementMethod(LinkMovementMethod.getInstance());
            }else{
                txtNombreRestaurante.setVisibility(View.GONE);
                txtDireccioRestaurante.setVisibility(View.GONE);
                txtWebRestaurante.setVisibility(View.GONE);
                NombreRestaurante.setVisibility(View.GONE);
                DireccionRestaurante.setVisibility(View.GONE);
                WebRestaurante.setVisibility(View.GONE);
                txtRecipe.setText(plato.getRecipe());
                txtURL.setText(plato.getURL());
                txtURL.setMovementMethod(LinkMovementMethod.getInstance());
            }

        }

    }


    public List<String> TipoComidaTraduccion(List<TipoComida> tipoComidas){
        List<String> comidasSalida =new ArrayList<>();
        for(TipoComida comida: tipoComidas){
            if(comida.equals(TipoComida.Desayuno)){
                comidasSalida.add(this.getString(R.string.Breakfast));
            }else if(comida.equals(TipoComida.Almuerzo)){
                comidasSalida.add(this.getString(R.string.Lunch));
            }else if(comida.equals(TipoComida.Cena)){
                comidasSalida.add(this.getString(R.string.Dinner));
            }
        }
        return comidasSalida;
    }


    public List<String> AlergenosTraduccion(List<Alergenos> alergenosList){
        List<String> alergenosSalida =new ArrayList<>();
        for(Alergenos alergeno: alergenosList){
            if(alergeno.equals(Alergenos.Altramuces)){
                alergenosSalida.add(this.getString(R.string.Lupine));
            }else if(alergeno.equals(Alergenos.Apio)){
                alergenosSalida.add(this.getString(R.string.Celery));
            }else if(alergeno.equals(Alergenos.Crustaceos)){
                alergenosSalida.add(this.getString(R.string.Crustacean));
            }else if(alergeno.equals(Alergenos.Glutén)){
                alergenosSalida.add(this.getString(R.string.Gluten));
            }else if(alergeno.equals(Alergenos.Dióxido_De_Azufre)){
                alergenosSalida.add(this.getString(R.string.Sulfur_Dioxide));
            }else if(alergeno.equals(Alergenos.Frutos_Secos)){
                alergenosSalida.add(this.getString(R.string.Dried_Fruit));
            }else if(alergeno.equals(Alergenos.Huevo)){
                alergenosSalida.add(this.getString(R.string.Egg));
            }else if(alergeno.equals(Alergenos.Lacteos)){
                alergenosSalida.add(this.getString(R.string.Dairy));
            }else if(alergeno.equals(Alergenos.Moluscos)){
                alergenosSalida.add(this.getString(R.string.Mollusk));
            }else if(alergeno.equals(Alergenos.Mostaza)){
                alergenosSalida.add(this.getString(R.string.Mustard));
            }else if(alergeno.equals(Alergenos.Pescado)){
                alergenosSalida.add(this.getString(R.string.Fish));
            }else if(alergeno.equals(Alergenos.Sesamo)){
                alergenosSalida.add(this.getString(R.string.Sesame));
            }else if(alergeno.equals(Alergenos.Soja)){
                alergenosSalida.add(this.getString(R.string.Soy));
            }
        }
        return alergenosSalida;
    }


}