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



    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {

                try{
                    int cantidad100= calcular(Integer.parseInt(txtCantidad.getText().toString()),100);
                    int cantidad50 = calcular(Integer.parseInt(txtCantidad.getText().toString()),50);
                    int cantidad20 = calcular(Integer.parseInt(txtCantidad.getText().toString()),20);
                    int cantidad10 = calcular(Integer.parseInt(txtCantidad.getText().toString()),10);
                    int cantidad5 =  calcular(Integer.parseInt(txtCantidad.getText().toString()),5);

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

    private class SwipeListener implements View.OnTouchListener{

        GestureDetector gestureDetector;

        SwipeListener(View view) {
            int threshold = 100;
            int velocity_threshold = 100;

        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDown(MotionEvent e){
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){

                float xDiff = e2.getX() - e1.getX();
                float yDiff = e2.getY() - e1.getY();

                if(Math.abs(xDiff) > Math.abs(yDiff)){

                    if(Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold){

                        if(xDiff > 0 && view.getId() == R.id.lsalir){
                            System.exit(0);
                        }
                        else if(xDiff < 0 && view.getId() == R.id.lborrar){
                                txtCantidad.setText("");
                                labelCantidad.setText("");
                            }
                        return true;
                        }
                    }
                else{
                    if(Math.abs(yDiff) > threshold && Math.abs(velocityY) > velocity_threshold
                        && view.getId()==R.id.table1){

                        Drawable d;

                        if(xDiff > 0 ){
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

    public int calcular(int cant, int division) {
        return cant / division;
    }
}