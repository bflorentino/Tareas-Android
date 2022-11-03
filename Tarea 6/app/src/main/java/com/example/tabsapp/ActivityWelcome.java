package com.example.tabsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityWelcome extends AppCompatActivity {

    TextView txtBienvenida;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Bundle data = getIntent().getExtras();
        nombre = data.getString("nombre");
        txtBienvenida = findViewById(R.id.txtBienvenida);
        txtBienvenida.setText("Bienvienido " + nombre);
    }

    public void onClickVolver (View view){
        finish();
    }
}