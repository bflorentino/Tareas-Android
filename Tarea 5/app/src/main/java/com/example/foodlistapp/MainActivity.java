package com.example.foodlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    String tiposComida [] = {"Frutas", "Granos", "Lacteos", "Proteinas", "Vegetales"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         spinner = findViewById(R.id.spinner);
        ArrayAdapter<String>tipoComidaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tiposComida);
        spinner.setAdapter(tipoComidaAdapter);
    }

    public void onClickTipoComida (View view){
        Intent tipoComidaConImagen = new Intent(this, General.class);
        startActivity(tipoComidaConImagen);
    }
}