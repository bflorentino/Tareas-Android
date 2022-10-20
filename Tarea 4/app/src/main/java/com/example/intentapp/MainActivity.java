package com.example.intentapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText  editnombre;
    TextView txtgenero, txtnombre;
    RadioGroup rGeneros;
    String genero = "Hombre";
    Button vfrase, vImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editnombre = findViewById(R.id.editnombre);
        txtnombre = findViewById(R.id.nombre);
        txtgenero = findViewById(R.id.genero);
        rGeneros = findViewById(R.id.generos);
        vfrase = findViewById(R.id.abrirFrase);
        vImagen = findViewById(R.id.abrirImagen);

        rGeneros.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checked) {
                if(checked == R.id.sHombre){
                    genero = "Hombre";
                }
                else{
                    genero = "Mujer";
                }
            }
        });
    }

    public void abrirImagen(View view){

        Intent activityImagen = new Intent(this, ActivityImagenes.class);
        activityImagen.putExtra("nombre", txtnombre.getText().toString());
        activityImagen.putExtra("genero", genero);

        startActivity(activityImagen);
    }

    public void abrirFrase(View view){

        Intent activityFrase = new Intent(this, ActivityFrases.class);
        activityFrase.putExtra("nombre", txtnombre.getText().toString());
        activityFrase.putExtra("genero", genero);

        startActivity(activityFrase);
    }

    public void setDatos(View view){

        if(!TextUtils.isEmpty("Nombre: "  + editnombre.getText().toString())){
            txtgenero.setText("Género: " + genero);
            txtnombre.setText("Nombre: " + editnombre.getText().toString());
            vImagen.setEnabled(true);
            vfrase.setEnabled(true);
            editnombre.setText("");
        }
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