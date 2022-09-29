package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnKeyListener {

    EditText txtCantidad;
    TextView labelCantidad;
    SwipeListener swipeListener;
    LinearLayout swipeL;
    LinearLayout swipeR;
    TableLayout colorTable;
    RelativeLayout rl1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCantidad = findViewById(R.id.textCant);
        labelCantidad = findViewById(R.id.labelCant);
        swipeL = findViewById(R.id.lsalir);
        swipeR = findViewById(R.id.lborrar);
        colorTable = findViewById(R.id.table1);
        rl1 =findViewById(R.id.rl1);

//        swipeR.setOnTouchListener(this);
//        swipeL.setOnTouchListener(this);
        txtCantidad.setOnKeyListener(this);

        swipeListener = new SwipeListener(swipeL);
        swipeListener = new SwipeListener(swipeR);
        swipeListener = new SwipeListener(colorTable);
    }

    public int calcularBilletes(int cant, int division) {
        return cant / division;
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {

                try{
                    int cantidad100= calcularBilletes(Integer.parseInt(txtCantidad.getText().toString()),100);
                    int cantidad50 = calcularBilletes(Integer.parseInt(txtCantidad.getText().toString()),50);
                    int cantidad20 = calcularBilletes(Integer.parseInt(txtCantidad.getText().toString()),20);
                    int cantidad10 = calcularBilletes(Integer.parseInt(txtCantidad.getText().toString()),10);
                    int cantidad5 =  calcularBilletes(Integer.parseInt(txtCantidad.getText().toString()),5);

                    labelCantidad.setText("Billetes de 100: " + cantidad100 +
                                            "\nBilletes de 50: "+ cantidad50 +
                                            "\nBilletes de 20: " + cantidad20 +
                                            "\nCentavos de 10: " + cantidad10 +
                                            "\nCentavos de 5: " + cantidad5 );
                }
                catch(Exception e){
                    labelCantidad.setText("Introduzca un valor numérico correcto");
                }
            }
        }
        return true;
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

                // Si la diferencia en x es mayor a la diferencia en y es porque se
                // hizo swipe a la izquierda o derecha
                if(Math.abs(xDiff) > Math.abs(yDiff)){

                        // El caso de un swipe a la derecha
                        if(xDiff > 0 && view.getId() == R.id.lsalir){
                            System.exit(0);
                        }
                        // Swipe a la izquierda
                        else if(xDiff < 0 && view.getId() == R.id.lborrar){
                                txtCantidad.setText("");
                                labelCantidad.setText("");
                            }
                    }
                else{
                    // En caso de swipe hacia abajo o hacia arriba

                    if(view.getId()==R.id.table1){

                        Drawable d;

                        // Swipe hacia arriba
                        if(yDiff > 0 ){
                             d = getResources().getDrawable(R.drawable.gradient2);
                        }
                        else{
                             d = getResources().getDrawable(R.drawable.gradient);
                        }
                        rl1.setBackground(d);
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