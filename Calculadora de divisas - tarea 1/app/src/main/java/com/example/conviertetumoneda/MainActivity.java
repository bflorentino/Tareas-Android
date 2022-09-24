package com.example.conviertetumoneda;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextUtils;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView calcScreen = null;
    TextView labelName = null;
    EditText textName = null;
    Button dopUsd, usdDop, dopEur, eurDop = null;
    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcScreen = findViewById(R.id.calc_values);
        labelName  = findViewById(R.id.label_name);
        textName   = findViewById(R.id.text_name);
        dopUsd     = findViewById(R.id.dop_usd);
        usdDop     = findViewById(R.id.usd_dop);
        dopEur     = findViewById(R.id.dop_eur);
        eurDop     = findViewById(R.id.eur_dop);
    }

    public void onClickNumber(View view) {
        Button number = (Button)view;
        String currentText = calcScreen.getText().toString();

        // Evitar que se pueda agregar más de un punto al valor de la pantalla(.)
        if(TextUtils.equals(number.getText().toString(), ".")
                && currentText.contains("."))
        {
            return;
        }
        // Cuando el punto es el primer caracter introducido en la pantalla
        if(TextUtils.isEmpty(currentText) &&
                TextUtils.equals(number.getText().toString(), "."))
        {
            calcScreen.setText("0.");
            return;
        }
        // Concatenar el valor que esta en la pantalla con el valor del boton numérico
        calcScreen.setText(currentText.concat(number.getText().toString()));
    }

    public void onClickClear(View view){
        calcScreen.setText("");
    }

    public void setName(View view) {

        // Chequea si el campo de nombre no está vacio para setear el nombre y habilitar los botones
        if(!TextUtils.isEmpty(textName.getText().toString())){
            labelName.setText(textName.getText().toString());
            textName.setText("");
            dopUsd.setEnabled(true);
            usdDop.setEnabled(true);
            dopEur.setEnabled(true);
            eurDop.setEnabled(true);
        }
    }

    public void onClickConvert(View view){

        double value = Double.parseDouble(calcScreen.getText().toString());
        String result = "";

        switch (view.getId()){
            case R.id.dop_usd:
                result = dopToUsd(value);
                break;
            case R.id.usd_dop:
                result = usdToDop(value);
                break;
            case R.id.dop_eur:
                result = dopToEur(value);
                break;
            case R.id.eur_dop:
                result = eurToDop(value);
                break;
        }
        calcScreen.setText(result);
    }

    // Metodos para la conversión de moneda
    public String dopToUsd(double value){
        return decimalFormat.format(value / 53.65);
    }
    public String usdToDop(double value){
        return decimalFormat.format(value * 53.65);
    }
    public String dopToEur(double value){
        return decimalFormat.format(value / 56);
    }
    public String eurToDop(double value){
        return decimalFormat.format(value * 56);
    }
}