package com.example.intentapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;
import java.util.Objects;

public class ActivityImagenes extends AppCompatActivity {

    int[]imagenesHombre = {R.drawable.hombre1, R.drawable.hombre2, R.drawable.hombre3, R.drawable.hombre4, R.drawable.hombre5};
    int []imagenesMujeres = {R.drawable.mujer1, R.drawable.mujer2, R.drawable.mujer3, R.drawable.mujer4, R.drawable.mujer5};
    ImageView imagen;
    TextView gen, nom;

    int numeroImagen, imagenAMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes);
        imagen = findViewById(R.id.imagensuperacion);

        Bundle data = getIntent().getExtras();

        String nombre = data.getString("nombre");
        String genero = data.getString("genero");

        gen = findViewById(R.id.gen);
        nom = findViewById(R.id.nombre);
        gen.setText("Género: " + genero);
        nom.setText(nombre);

        if(Objects.equals(genero, "Hombre")){
            numeroImagen = (int) Math.round(Math.random() * imagenesHombre.length);
            imagenAMostrar = (imagenesHombre[numeroImagen]);

        }else{
            numeroImagen = (int) Math.round(Math.random() * imagenesMujeres.length);
            imagenAMostrar = (imagenesMujeres[numeroImagen]);
        }
        imagen.setImageResource(imagenAMostrar);
    }

    public void compartir(View view){
        Intent shareImage = new Intent(Intent.ACTION_SEND);
        shareImage.setType("image/*");
        shareImage.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://com.example.intentapp/"+ imagenAMostrar));

        // Verify that the intent will resolve to an activity
        if (shareImage.resolveActivity(getPackageManager()) != null){
            startActivity(Intent.createChooser(shareImage, null));
        }
    }

    public void retornar(View view){
        finish();
    }

    public void salir(View view){

        AlertDialog.Builder preguntaSalir =new AlertDialog.Builder(this);

        preguntaSalir.setTitle("Salir de la aplicación")
                .setMessage("¿Realmente deseas salir de la aplicación?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAffinity();
                        System.exit(0);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();
    }
}