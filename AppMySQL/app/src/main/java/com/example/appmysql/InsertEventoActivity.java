package com.example.appmysql;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class InsertEventoActivity extends AppCompatActivity {

    EditText textTitulo, textDescripcion, textFecha;
    private int dia, mes, anio;

    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_evento);

        url = "http://192.168.56.1/android/eventos.php";

        textFecha = findViewById(R.id.insertarFecha);
        textTitulo = findViewById(R.id.insertarTituloText);
        textDescripcion = findViewById(R.id.insertarCuerpoText);
    }

    public void onSelectDate(View view){
        final Calendar calendar = Calendar.getInstance();

        dia = calendar.get(Calendar.DAY_OF_MONTH);
        anio = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayMonth) {
                textFecha.setText(year+"-"+(month+ 1)+"-"+dayMonth);
            }
        }, anio, mes, dia);
        datePickerDialog.show();
    }

    public void onClickInsertar(View view){

        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response,Toast.LENGTH_LONG ).show();
                textTitulo.setText("");
                textDescripcion.setText("");
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
                evento.setTitulo(textTitulo.getText().toString().trim());
                evento.setDescripcion(textDescripcion.getText().toString().trim());
                evento.setFecha(textFecha.getText().toString());

                Map<String, String> parametros = new HashMap<>();

                parametros.put("accion" , "insertar");
                parametros.put("titulo" , evento.getTitulo());
                parametros.put("descripcion" , evento.getDescripcion());
                parametros.put("fecha" , evento.getFecha());

                return parametros;
            }
        };

        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(req);
    }
}