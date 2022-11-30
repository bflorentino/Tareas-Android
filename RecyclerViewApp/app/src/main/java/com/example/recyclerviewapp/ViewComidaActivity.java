package com.example.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ViewComidaActivity extends AppCompatActivity {

    BaseDatos connection;
    EditText nombreText, tipoText;
    Comida comida;
    ImageView imagenComida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comida);

        Bundle data = getIntent().getExtras();
        int id = data.getInt("id");

        connection = new BaseDatos(this, "Comidas_Keeper", null, 3);

        comida = connection.getFoodById(id);

        nombreText = findViewById(R.id.verNombreText);
        tipoText = findViewById(R.id.verTipoText);
        imagenComida = findViewById(R.id.verImagenValorTipo);

        nombreText.setText(comida.getNombre());
        tipoText.setText(comida.getTipo());
        imagenComida.setImageResource(comida.getImagenValor());
    }

    public void onClickUpdateNota(View view){

        if(!TextUtils.isEmpty(tipoText.getText().toString()) || !TextUtils.isEmpty(nombreText.getText().toString())){

            comida.setNombre(nombreText.getText().toString());
            comida.setTipo(tipoText.getText().toString());

            if(connection.onUpdateFood(comida)){
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

        alerta.setTitle("Eliminar Comida");
        alerta.setMessage("¿Estás seguro de que deseas eliminar esta comida?");
        alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(connection.onDeleteFood(comida.getId())){
                    Toast.makeText(getApplicationContext(), "La comida ha sido eliminada",Toast.LENGTH_LONG ).show();
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