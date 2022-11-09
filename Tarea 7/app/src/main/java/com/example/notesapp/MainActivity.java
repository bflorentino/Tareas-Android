package com.example.notesapp;

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
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    RecyclerView listaNotasView;
    ArrayList<Nota> notas;
    BaseDatos connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connection = new BaseDatos(this, "Notas_Keeper", null, 1);

        notas = connection.getAllNotas();
        System.out.println(notas);

        listaNotasView = findViewById(R.id.listaNotas);
        listaNotasView.setLayoutManager(new LinearLayoutManager(this));

        NotaListAdapter adaptador = new NotaListAdapter(notas);
        listaNotasView.setAdapter(adaptador);
    }

    public void onClickInsertar(View view){
        Intent ventana = new Intent(MainActivity.this, InsertarActivity.class );
        startActivity(ventana);
    }

class NotaListAdapter extends RecyclerView.Adapter<NotaListAdapter.NotaView> {

        ArrayList<Nota>listaNotas;

        public NotaListAdapter(ArrayList<Nota> listaNotas){
            this.listaNotas = listaNotas;
        }

    @NonNull
    @Override
    public NotaView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_layout, null, false);
        return new NotaView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaView holder, int position) {
            holder.tituloText.setText(listaNotas.get(position).getTitulo());
    }

    @Override
        public long getItemId(int i) {
            return 0;
        }

    @Override
    public int getItemCount() {
        return listaNotas.size();
    }

    public class NotaView extends RecyclerView.ViewHolder {

            TextView tituloText;

            public NotaView(@NonNull View itemView) {
            super(itemView);
            tituloText = itemView.findViewById(R.id.tituloNota);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ventana = new Intent(view.getContext(),ViewNotaActivity.class);
                    ventana.putExtra("id", listaNotas.get(getAdapterPosition()).getId());
                    startActivity(ventana);
                }
            });
        }
    }
  }
}