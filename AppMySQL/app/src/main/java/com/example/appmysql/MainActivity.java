package com.example.appmysql;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String url = "http://192.168.56.1/android/eventos.php";
    RecyclerView listaEventosView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaEventosView = findViewById(R.id.listaEventos);
        listaEventosView.setLayoutManager(new LinearLayoutManager(this));

        this.getEventos(null);
    }

    public void onClickNuevoEvento(View view){
        Intent ventana = new Intent(MainActivity.this, InsertEventoActivity.class );
        startActivity(ventana);
    }


    public void getEventos (View view){

        String accion = "leer";

        if(view != null){

            if(view.getId() == R.id.btnHoy){
                accion = "leerhoy";
            }
            else{
                accion = "leer";
            }
        }

        ArrayList<Evento> listaEventos = new ArrayList<>();

        String finalAccion = accion;
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("respuesta", response);
                if(!response.isEmpty()){
                    try{
                        JSONArray array = new JSONArray(response);

                        for(int i = 0; i < array.length(); i++){

                            JSONObject cursor = array.getJSONObject(i);

                            Evento evento = new Evento();
                            evento.setTitulo(cursor.getString("titulo"));
                            evento.setDescripcion( cursor.getString("descripcion"));
                            evento.setFecha(cursor.getString("fecha")) ;
                            evento.setId(cursor.getInt("id"));

                            listaEventos.add(evento);

                        }
                        EventoListAdapter adaptador = new EventoListAdapter(listaEventos);
                        listaEventosView.setAdapter(adaptador);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("accion", finalAccion);

                return parametros;
            }
        };

        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(req);
    }


    class EventoListAdapter extends RecyclerView.Adapter<EventoListAdapter.EventoView> {

        ArrayList<Evento>listaEventos;

        public EventoListAdapter(ArrayList<Evento> listaEventos){
            this.listaEventos = listaEventos;
        }

        @NonNull
        @Override
        public EventoView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_layout, null, false);
            Log.d("respuesta asd", "Nada que ver aqui" );
            return new EventoView(view);
        }

        @Override
        public void onBindViewHolder(@NonNull EventoView holder, int position) {
            holder.tituloText.setText(listaEventos.get(position).getTitulo());
            holder.fechaEvento.setText(listaEventos.get(position).getFecha());
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public int getItemCount() {
            return listaEventos.size();
        }

        public class EventoView extends RecyclerView.ViewHolder {

            TextView tituloText, fechaEvento;

            public EventoView(@NonNull View itemView) {
                super(itemView);
                tituloText = itemView.findViewById(R.id.tituloEvento);
                fechaEvento = itemView.findViewById(R.id.fechaEvento);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent ventana = new Intent(MainActivity.this, ViewEventActivity.class);
                        ventana.putExtra("id", listaEventos.get(getAdapterPosition()).getId());
                        ventana.putExtra("titulo", listaEventos.get(getAdapterPosition()).getTitulo());
                        ventana.putExtra("descripcion", listaEventos.get(getAdapterPosition()).getDescripcion());
                        ventana.putExtra("fecha", listaEventos.get(getAdapterPosition()).getFecha());
                        startActivity(ventana);
                    }
                });
            }
        }
    }
}