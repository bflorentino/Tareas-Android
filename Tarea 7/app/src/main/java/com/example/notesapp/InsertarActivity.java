package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarActivity extends AppCompatActivity {

    EditText tituloText, cuerpoText;
    BaseDatos connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        tituloText = findViewById(R.id.insertarTituloText);
        cuerpoText = findViewById(R.id.insertarCuerpoText);

        connection = new BaseDatos(this, "Notas_Keeper", null, 1);
    }

    public void onClickInsertNota(View view) {

        if(!TextUtils.isEmpty(cuerpoText.getText().toString()) || !TextUtils.isEmpty(tituloText.getText().toString())){

            Nota notaAinsertar = new Nota(tituloText.getText().toString(), cuerpoText.getText().toString());

            if(connection.onInsertNota(notaAinsertar)){
                Toast.makeText(getApplicationContext(), "Los datos fueron insertados correctamente",Toast.LENGTH_LONG ).show();
                tituloText.setText("");
                cuerpoText.setText("");
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