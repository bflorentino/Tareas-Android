package com.example.foodlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoComida extends AppCompatActivity {

    String imagenComida, tipocomida, nombre, receta;

    ImageView imagen;
    TextView nombre_text, tipo_text, receta_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_comida);

        Bundle data = getIntent().getExtras();
        imagenComida = data.getString("imagen");
        tipocomida = data.getString("tipoComida");
        nombre = data.getString("nombre");
        receta = data.getString("receta");

        imagen = findViewById(R.id.imagenInfoComida);
        nombre_text = findViewById(R.id.nombreInfoComida);
        tipo_text = findViewById(R.id.tipoInfoComida);
        receta_text = findViewById(R.id.recetaInfoComida);

        imagen.setImageResource(Integer.valueOf(imagenComida));
        nombre_text.setText(nombre);
        tipo_text.setText("Pertenece al tipo: " + tipocomida);
        receta_text.setText(receta);
    }

    public void onClickVolverInfo(View view){
        finish();
    }

    public void compartir(View view){
        Intent shareReceta = new Intent(Intent.ACTION_SEND);
        shareReceta.putExtra(Intent.EXTRA_TEXT, receta);
        shareReceta.setType("text/plain");

        // Verify that the intent will resolve to an activity
        if (shareReceta.resolveActivity(getPackageManager()) != null){
            startActivity(Intent.createChooser(shareReceta, null));
        }
    }
}