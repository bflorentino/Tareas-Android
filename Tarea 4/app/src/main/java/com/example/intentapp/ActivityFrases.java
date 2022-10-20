package com.example.intentapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class ActivityFrases extends AppCompatActivity {

    TextView gen, nom, frase;
    int numeroFrase;
    String fraseAMostrar;

    String[] frasesMujeres = {"Saber lo que hay que hacer elimina el miedo. — Rosa Parks",
            "No llegué allí deseándolo o esperándolo, sino trabajando por ello. — Estée Lauder",
            "Rodéate de personas que reten tus límites, no de personas que limiten tus retos. — Pilar Jericó",
            "Lo que decimos tiene impacto en nuestras emociones. Cambia el “tengo un problema” por “tengo nuevos retos”. — Vilma Núñez",
            "Cuando todo el mundo está en silencio, incluso una sola voz se vuelve poderosa. — Malala Yousafzai",
            "La pregunta no es quién me va a dejar; es quién va a detenerme. — Ayn Rand",
            "A veces tienes que olvidar lo que sientes y recordar lo que mereces. — Frida Kahlo",
            "Lo normal no es algo a lo que aspirar, es algo de lo que debes huir. — Jodie Foster",
            "Lo más difícil es la decisión de actuar, el resto es meramente tenacidad. — Amelia Earhart",
            "El poder no te es dado. Tienes que tomarlo. — Beyoncé Knowles",
            "Siempre hice algo para lo que no estaba preparada. Creo que así es la manera de crecer. —  Marissa Mayer",
            "Memoria selectiva para recordar lo bueno, prudencia lógica para no arruinar el presente y optimismo desafiante para encarar el futuro. — Isabel Allende",
            "Nunca puedes dejar huellas que duren si siempre estás caminando de puntillas. —  Leymah Gbowee",
            "‘Hecho’ es mejor que ‘perfecto’. — Sheryl Sandberg",
            "Quien no se mueve, no siente las cadenas. — Rosa Luxemburgo",};

    String[] frasesHombres = {"«Si supiera que el mundo se acaba mañana, yo, hoy todavía, plantaría un árbol» Martin LutherKing",
            "«Hay hombres que luchan un día y son buenos. Hay otros que luchan un año y son mejores. Hay quienes luchan muchos años, y son muy buenos. Pero hay los que, luchan toda la vida, esos son los imprescindibles».Bertolt Brecht",
            "«Sé bondadoso con todos cuando subas; los encontrarás a todos cuando bajes» Eduardo Punset",
            "«Los hombres que valen la pena cruzan el mar de la vida nadando. Los demás se contentan con bañarse en la orilla» Anónimo",
            "«Nunca, alcanzarás el cielo si tienes miedo de volar» Anónimo",
            "«Casi, todo lo que realice será insignificante, pero es muy importante que lo haga» Mahatma Gandhi",
            "«Después de escalar una montaña muy alta, descubrimos que hay muchas otras montañas por escalar» Nelson Mándela",
            "«Un Estado está mejor gobernado por un hombre bueno que por una buena ley» Aristóteles",
            "«No, hay camino para la paz, la paz es el camino» Mahatma Gandhi",
            "«Un hombre valiente puede caer, pero no cederá» Anónimo",
            "«El, único símbolo de superioridad que conozco es la bondad» Ludwig van Beethoven",
            "«El futuro tiene muchos nombres. Para los débiles es lo inalcanzable. Para los temerosos, lo desconocido. Para los valientes es la oportunidad» Anónimo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frases);

        Bundle data = getIntent().getExtras();

        String nombre = data.getString("nombre");
        String genero = data.getString("genero");

         gen = findViewById(R.id.gen);
         nom = findViewById(R.id.nombre);
         frase = findViewById(R.id.frase);

         gen.setText("Género: " + genero);
         nom.setText(nombre);

        if(Objects.equals(genero, "Hombre")){
            numeroFrase = (int) Math.round(Math.random() * frasesHombres.length);
            fraseAMostrar = frasesHombres[numeroFrase];

        }else{
            numeroFrase = (int) Math.round(Math.random() * frasesMujeres.length);
            fraseAMostrar = (frasesMujeres[numeroFrase]);
        }
        frase.setText(fraseAMostrar);
    }

    public void compartir(View view){
        Intent shareFrase = new Intent(Intent.ACTION_SEND);
        shareFrase.putExtra(Intent.EXTRA_TEXT, fraseAMostrar);
        shareFrase.setType("text/plain");

        // Verify that the intent will resolve to an activity
        if (shareFrase.resolveActivity(getPackageManager()) != null){
            startActivity(Intent.createChooser(shareFrase, null));
        }
    }

    public void retornar(View view){
        finish();
    }

    public void salir(View view){

        AlertDialog.Builder preguntaSalir =new AlertDialog.Builder(this);

        preguntaSalir.setTitle("Salir de la aplicación")
                .setMessage("¿Realmente deseas salir de la aplicación?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAffinity();
                        System.exit(0);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();
    }
}