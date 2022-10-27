package com.example.foodlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class General extends AppCompatActivity {

    String tiposComida [] = {"Frutas", "Granos", "Lacteos", "Proteinas", "Vegetales"};

    int tipoComidaFotos [] = {R.drawable.frutas,
            R.drawable.granos,
            R.drawable.lacteos,
            R.drawable.proteinas,
            R.drawable.vegetales
    };

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        ComidaTipoAdapter adapter = new ComidaTipoAdapter();
        spinner = findViewById(R.id.spinner2);
        spinner.setAdapter(adapter);

    }

    public void onClickComidaDescripcion(View view){

        Intent ventana = new Intent(this, TipoComida.class);
        ventana.putExtra("tipoComida", spinner.getSelectedItem().toString());
        startActivity(ventana);
    }

    public void onClickVolverGeneral(View view){
        finish();
    }

    class ComidaTipoAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return tiposComida.length;
        }

        @Override
        public Object getItem(int i) {
            return tiposComida[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(General.this);
            view = inflater.inflate(R.layout.item_food_spinner, null);
            ImageView tipoFoto = view.findViewById(R.id.imagenTipo);
            TextView nombreComida = view.findViewById(R.id.nombreComida);
            tipoFoto.setImageResource(tipoComidaFotos[i]);
            nombreComida.setText(tiposComida[i]);

            return view;

        }
    }
}