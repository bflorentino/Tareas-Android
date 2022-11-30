package com.example.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InsertarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText nombreText;
    String tipoText;
    int valorImagen;
    BaseDatos connection;

    Spinner spinner;
    String tiposComida [] = {"Frutas", "Granos", "Lacteos", "Proteinas", "Vegetales"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        nombreText = findViewById(R.id.insertarNombreText);
        spinner = findViewById(R.id.spinner1);

        ArrayAdapter<String> tipoComidaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tiposComida);
        spinner.setAdapter(tipoComidaAdapter);

        spinner.setOnItemSelectedListener(this);

        connection = new BaseDatos(this, "Comidas_Keeper", null, 3);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tipoText = spinner.getSelectedItem().toString();

        switch(tipoText){
            case "Frutas":
                valorImagen = R.drawable.frutas;
                break;

            case "Granos":
                valorImagen = R.drawable.granos;
                break;

            case "Lacteos":
                valorImagen = R.drawable.lacteos;
                break;

            case "Proteinas":
                valorImagen = R.drawable.proteinas;
                break;

            case "Vegetales":
                valorImagen = R.drawable.vegetales;
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    public void onClickTipoComida (View view){
        tipoText = spinner.getSelectedItem().toString();

        switch(tipoText){
            case "Frutas":
                valorImagen = R.drawable.frutas;
                break;

            case "Granos":
                valorImagen = R.drawable.granos;
                break;

            case "Lacteos":
                valorImagen = R.drawable.lacteos;
                break;

            case "Proteinas":
                valorImagen = R.drawable.proteinas;
                break;

            case "Vegetales":
                valorImagen = R.drawable.proteinas;
                break;
        }
    }

    public void onClickInsertComida(View view) {

        if(!TextUtils.isEmpty(nombreText.getText().toString()) || tipoText != "" || valorImagen != 0){

            System.out.println(tipoText);
            System.out.println(valorImagen);

            Comida comidaAinsertar = new Comida(nombreText.getText().toString(), tipoText, valorImagen);

            if(connection.onInsertFood(comidaAinsertar)){
                Toast.makeText(getApplicationContext(), "Los datos fueron insertados correctamente",Toast.LENGTH_LONG ).show();
                nombreText.setText("");
            }
            else{
                Toast.makeText(getApplicationContext(), "Hubo un error al conectar con la base de datos",Toast.LENGTH_LONG ).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Asegurese de llenar los campos.",Toast.LENGTH_LONG ).show();
        }
    }
}