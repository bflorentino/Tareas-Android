package com.example.appmysql;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ViewEventActivity extends AppCompatActivity {

    EditText descripcionText, tituloText, fechaText;
    private int dia, mes, anio;
    String url = "http://192.168.56.1/android/eventos.php";
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);

        Bundle data = getIntent().getExtras();
        id = data.getInt("id");
        System.out.println(id);
        String descripcion = data.getString("descripcion");
        String titulo = data.getString("titulo");
        String fecha = data.getString("fecha");

        tituloText = findViewById(R.id.verTituloText);
        descripcionText = findViewById(R.id.verDescripcionText);
        fechaText = findViewById(R.id.verFechaText);

        tituloText.setText(titulo);
        descripcionText.setText(descripcion);
        fechaText.setText(fecha);
    }

    public void onSelectDate(View view){
        final Calendar calendar = Calendar.getInstance();

        dia = calendar.get(Calendar.DAY_OF_MONTH);
        anio = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayMonth) {
                fechaText.setText(year+"-"+(month+ 1)+"-"+dayMonth);
            }
        }, anio, mes, dia);
        datePickerDialog.show();
    }

    public void onClickEdit(View view){

        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response,Toast.LENGTH_LONG ).show();
                Log.d("response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_LONG ).show();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Evento evento = new Evento();
                evento.setTitulo(tituloText.getText().toString().trim());
                evento.setDescripcion(descripcionText.getText().toString().trim());
                evento.setFecha(fechaText.getText().toString());
                evento.setId(id);

                Map<String, String> parametros = new HashMap<>();

                parametros.put("accion" , "editar");
                parametros.put("titulo" , evento.getTitulo());
                parametros.put("descripcion" , evento.getDescripcion());
                parametros.put("fecha" , evento.getFecha());
                parametros.put("id" , String.valueOf(evento.getId()));

                return parametros;
            }
        };

        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(req);
    }

    public void onClickDeleteNota(View view){

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        alerta.setTitle("Eliminar Evento");
        alerta.setMessage("¿Estás seguro de que deseas eliminar este evento de tu lista?");
        alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                eliminarEvento();
            }
        });
        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        alerta.create();
        alerta.show();
    }

    public void eliminarEvento(){

        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response,Toast.LENGTH_LONG ).show();
                Log.d("respuesta", response);
                listar();

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
                parametros.put("accion", "eliminar");
                parametros.put("id", String.valueOf(id));

                return parametros;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(req);
    }

    public void listar()
    {
        Intent ventana = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(ventana);
    }
}