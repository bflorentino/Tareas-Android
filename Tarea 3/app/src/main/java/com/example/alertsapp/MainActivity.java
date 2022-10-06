package com.example.alertsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] categorias = {"Música", "Calle", "Persona", "Carro"};
    List<Integer> checkedCategorias = new ArrayList<>();
    ImageView imgSeleccionada;
    EditText editSubject, editName, editInst;
    TextView textName, textSubject, textInst, myName;
    View alertViewInfo;
    View alertEditInfo;
    RelativeLayout rlprincipal;
    SwipeListener swipeListener;

    String nombre = "Bryan Xavier Florentino Montero";
    String materia = "Introducción al Desarrollo de aplicaciones móviles";
    String instituto = "Instituto Tecnológico de las Américas";

    int[] images = {R.drawable.musica,
                    R.drawable.calle,
                    R.drawable.persona,
                    R.drawable.carro,

                    R.drawable.callemusica,
                    R.drawable.personamusica,
                    R.drawable.carromusica,
                    R.drawable.personacalle,
                    R.drawable.carrocalle,
                    R.drawable.carropersona,
                    R.drawable.carropersonacalle,
                    R.drawable.carrocallemusica,
                    R.drawable.todos
                    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myName = findViewById(R.id.myname);
        myName.setText(nombre);

        rlprincipal = findViewById(R.id.layoutprincipal);
        imgSeleccionada = findViewById(R.id.imagenSel);

        swipeListener = new SwipeListener(rlprincipal);
    }

    public void onClick (View view){

        AlertDialog.Builder altImages = new AlertDialog.Builder(MainActivity.this);

        altImages.setTitle("Categorias de imagen")
                .setMultiChoiceItems(categorias, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int boxChecked, boolean isChecked) {
                                if(!isChecked){
                                    checkedCategorias.remove(boxChecked);
                                    return;
                                }
                                checkedCategorias.add(boxChecked);
                            }
                }).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch(checkedCategorias.size()){

                            case 1:
                                imgSeleccionada.setImageResource(images[checkedCategorias.get(0)]);
                                break;

                            case 2:
                                if(checkedCategorias.contains(0) && checkedCategorias.contains(1)){
                                    imgSeleccionada.setImageResource(images[4]);
                                    break;
                                }
                                if(checkedCategorias.contains(0) && checkedCategorias.contains(2)){
                                    imgSeleccionada.setImageResource(images[5]);
                                    break;
                                }
                                if(checkedCategorias.contains(0) && checkedCategorias.contains(3)){
                                    imgSeleccionada.setImageResource(images[6]);
                                    break;
                                }
                                if(checkedCategorias.contains(1) && checkedCategorias.contains(2)){
                                    imgSeleccionada.setImageResource(images[7]);
                                    break;
                                }
                                if(checkedCategorias.contains(1) && checkedCategorias.contains(3)){
                                    imgSeleccionada.setImageResource(images[8]);
                                    break;
                                }
                                if(checkedCategorias.contains(2) && checkedCategorias.contains(3)){
                                    imgSeleccionada.setImageResource(images[9]);
                                    break;
                                }
                            case 3:
                                if(checkedCategorias.contains(1) && checkedCategorias.contains(2) && checkedCategorias.contains(3) ){
                                    imgSeleccionada.setImageResource(images[10]);
                                    break;
                                }
                                if(checkedCategorias.contains(0) && checkedCategorias.contains(1) && checkedCategorias.contains(3)){
                                    imgSeleccionada.setImageResource(images[11]);
                                    break;
                                }
                                imgSeleccionada.setImageResource(images[12]);
                                break;
                            case 4:
                                imgSeleccionada.setImageResource(images[12]);
                                break;
                        }
                        checkedCategorias.clear();
                    }
                })
                .create()
                .show();
    }

    public void onClickViewInfo(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        alertViewInfo = getLayoutInflater().inflate(R.layout.alert_view_info, null);

        textName = alertViewInfo.findViewById(R.id.textname);
        textInst = alertViewInfo.findViewById(R.id.textinst);
        textSubject = alertViewInfo.findViewById(R.id.textsubject);

        textName.setText(nombre);
        textSubject.setText(materia);
        textInst.setText(instituto);

        builder.setView(alertViewInfo)
                .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();
    }

    public void onClickEditInfo(View view){

        alertEditInfo = getLayoutInflater().inflate(R.layout.alert_edit_info, null);

        editName = alertEditInfo.findViewById(R.id.newname);
        editSubject = alertEditInfo.findViewById(R.id.newsubject);
        editInst = alertEditInfo.findViewById(R.id.newinst);

        editName.setText(nombre);
        editSubject.setText(materia);
        editInst.setText(instituto);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setView(alertEditInfo);
        builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                nombre = editName.getText().toString();
                materia = editSubject.getText().toString();
                instituto = editInst.getText().toString();
                myName.setText(nombre);
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        builder.create();
        builder.show();
    }

    // Una clase privada para la implementación del swipe en la pantalla
    private class SwipeListener implements View.OnTouchListener{
        GestureDetector gestureDetector;
        SwipeListener(View view) {

            GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onDown(MotionEvent e){
                    return true;
                }

                // Metodo para manejar los gestos en la pantalla (manejo del swipe)
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){

                    float xDiff = e2.getX() - e1.getX(); // Diferencia en el eje x
                    float yDiff = e2.getY() - e1.getY(); // Diferencia en el eje y

                    if(Math.abs(xDiff) < Math.abs(yDiff)){
                        // En caso de swipe hacia arriba
                        if(view.getId()==R.id.layoutprincipal){
                            if(yDiff > 0 ){
                               System.exit(0);
                            }
                        }
                    }
                    return false;
                }
            };
            gestureDetector = new GestureDetector(listener);
            view.setOnTouchListener(this);
        }

        public boolean onTouch(View view, MotionEvent motionEvent){
            return gestureDetector.onTouchEvent(motionEvent);
        }
    }
}