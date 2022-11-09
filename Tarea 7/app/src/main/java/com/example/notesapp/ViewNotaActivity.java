package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ViewNotaActivity extends AppCompatActivity {

    BaseDatos connection;
    EditText tituloText, cuerpoText;
    Nota nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_nota);

        Bundle data = getIntent().getExtras();
        int id = data.getInt("id");

        connection = new BaseDatos(this, "Notas_Keeper", null, 1);

        nota = connection.getNotaById(id);

        tituloText = findViewById(R.id.verTituloText);
        cuerpoText = findViewById(R.id.verCuerpoText);

        tituloText.setText(nota.getTitulo());
        cuerpoText.setText(nota.getCuerpo());
    }

    public void onClickUpdateNota(View view){

        if(!TextUtils.isEmpty(cuerpoText.getText().toString()) || !TextUtils.isEmpty(tituloText.getText().toString())){

            nota.setTitulo(tituloText.getText().toString());
            nota.setCuerpo(cuerpoText.getText().toString());

            if(connection.onUpdateNota(nota)){
                Toast.makeText(getApplicationContext(), "Los datos fueron actualizados correctamente",Toast.LENGTH_LONG ).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Hubo un error al conectar con la base de datos",Toast.LENGTH_LONG ).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Asegurese de llenar los campos.",Toast.LENGTH_LONG ).show();
        }
    }

    public void onClickDeleteNota(View view){

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        alerta.setTitle("Eliminar Nota");
        alerta.setMessage("¿Estás seguro de que deseas eliminar esta nota?");
        alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(connection.onDeleteNota(nota.getId())){
                    Toast.makeText(getApplicationContext(), "La nota ha sido eliminada",Toast.LENGTH_LONG ).show();
                    listar();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Hubo un error al conectar con la base de datos",Toast.LENGTH_LONG ).show();
                }
            }
        });
        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        alerta.create();
        alerta.show();
    }

    public void listar()
    {
        Intent ventana = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(ventana);
    }
}