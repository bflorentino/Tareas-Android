package com.example.recyclerviewapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    RecyclerView listaComidasView;
    ArrayList<Comida> comidas;
    BaseDatos connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connection = new BaseDatos(this, "Comidas_Keeper", null, 3);

        comidas = connection.getAllFood();
        System.out.println(comidas);

        listaComidasView = findViewById(R.id.listaComida);
        listaComidasView.setLayoutManager(new LinearLayoutManager(this));

        ComidaListAdapter adaptador = new ComidaListAdapter(comidas);
        listaComidasView.setAdapter(adaptador);
    }

    public void onClickInsertar(View view){
        Intent ventana = new Intent(MainActivity.this, InsertarActivity.class );
        startActivity(ventana);
    }

    class ComidaListAdapter extends RecyclerView.Adapter<ComidaListAdapter.ComidaView> {

        ArrayList<Comida>listaComidas;

        public ComidaListAdapter(ArrayList<Comida> listaComidas){
            this.listaComidas = listaComidas;
        }

        @NonNull
        @Override
        public ComidaView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_layout, null, false);
            return new ComidaView(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ComidaView holder, int position) {
            System.out.println(holder.nombreText);
            System.out.println(listaComidas.get(position).getNombre());
            holder.nombreText.setText(listaComidas.get(position).getNombre());
            holder.tipoText.setText(listaComidas.get(position).getTipo());
            holder.comidaImage.setImageResource(listaComidas.get(position).getImagenValor());
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public int getItemCount() {
            return listaComidas.size();
        }

        public class ComidaView extends RecyclerView.ViewHolder {

            TextView nombreText;
            TextView tipoText;
            ImageView comidaImage;

            public ComidaView(@NonNull View itemView) {
                super(itemView);
                nombreText = itemView.findViewById(R.id.nombreComida);
                tipoText = itemView.findViewById(R.id.tipoComida);
                comidaImage = itemView.findViewById(R.id.imagenValorTipo);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent ventana = new Intent(view.getContext(),ViewComidaActivity.class);
                        Toast.makeText(getApplicationContext(),listaComidas.get(getAdapterPosition()).getNombre() ,Toast.LENGTH_LONG ).show();
                        ventana.putExtra("id", listaComidas.get(getAdapterPosition()).getId());
                        startActivity(ventana);
                    }
                });
            }
        }
    }
}