package com.example.foodlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TipoComida extends AppCompatActivity {

            String infoComida [][] = {
            {String.valueOf(R.drawable.frutas1), "Banana", "Es una fruta color amarilla", },
            {String.valueOf(R.drawable.frutas2), "Manzana", "Una rica fruta color rojo"},
            {String.valueOf(R.drawable.frutas3), "Piña", "Una fruta con una moña, color naranja"},
            {String.valueOf(R.drawable.frutas4), "Pera", "Una fruta verde"},
            {String.valueOf(R.drawable.granos1), "Corn Flakes", "Un cereal hecho a partir del maíz"},
            {String.valueOf(R.drawable.granos2), "Arroz", "Un cereal principalmente usado para almorzar"},
            {String.valueOf(R.drawable.granos3), "Maíz", "Son semillas de color amarillo"},
            {String.valueOf(R.drawable.granos4), "Trigo", "Un cereal muy utilizado para hacer pan, por ejemplo"},
            {String.valueOf(R.drawable.lacteos1),  "Yogurt", "Un producto lacteo obtenido mediante la fermentación de leche"},
            {String.valueOf(R.drawable.lacteos2), "Leche", "Un producto lacteo, principalmente obtenido de la vaca"},
            {String.valueOf(R.drawable.lacteos3), "Queso", "Un producto lacteo solido, usado como embutido"},
            {String.valueOf(R.drawable.lacteos4), "Mantequilla", "Un Producto lacteo altamente grasoso en forma de emulsión"},
            {String.valueOf(R.drawable.proteinas1), "Huevos", "Un alimento producido por aves protegido por un cascarón"},
            {String.valueOf(R.drawable.proteinas2), "Carne de Cerdo", "Es un tipo de carne roja obtenida del cerdo"},
            {String.valueOf(R.drawable.proteinas3), "Pescado", "Productos comestibles que se obtienen desde el mar"},
            {String.valueOf(R.drawable.proteinas4), "Carne de Pollo", "Es una carne obtenida de los pollos"},
            {String.valueOf(R.drawable.vegetales1), "Tomate", "Un vegetal color rojo que se puede sembrar"},
            {String.valueOf(R.drawable.vegetales2), "Zanahorias", "Un vegetal alargado color naranja"},
            {String.valueOf(R.drawable.vegetales3), "Lechuga Repollada", "Un vegetal en hojas, color verde. Muy usado en ensaladas"},
            {String.valueOf(R.drawable.vegetales4), "Ajies", "Son pimientos picantes"}
    };

    String recetas [] = {
            "La banana es una fruta que debe ser sembrada para su cosecha.  es un fruto comestible, de varios tipos de grandes plantas herbáceas del género Musa.",
            "Te aconsejamos que elijas los meses de septiembre y marzo para consumir y disfrutar las manzanas, porque es en esta época del año cuando se recolectan y están más frescas. ",
            "Por lo general se suele cosechar entre los 15 a 24 meses después de sembrado. La piña debe de cosecharse cuando ya este madurar ya que una vez cosechada no lograr madurar más.",
            "Maduran a finales del verano u otoño dependiendo de la variedad. El peral promedio puede producir una cantidad notable de frutos desde los 4 a 6 años de edad.",
            "Los granos de maíz son partidos por la mitad y cocidos con un jarabe de malta, una vez cocidos se secan y se laminan hasta convertirse en hojuelas. Las hojuelas son tostadas y adicionadas con vitaminas antes de ser empacadas.",
            "Busca una olla mediana en buen estado para hacer el arroz. Agrega el arroz blanco, las 2 tazas de agua, la sal y el aceite vegetal, todo de una vez. Revuelve un poco sólo en una oportunidad y ya. Pon a fuego alto y deja que hierva durante unos minutos.",
            "El maíz se produce bien en suelos fértiles y profundos, con alto contenido de materia orgánica y con buen drenaje, una adecuada preparación del suelo, ayuda a controlar plagas y enfermedades y enriquecer el suelo.",
            "El cultivo de trigo requiere de suelos sueltos con buen drenaje y con un pH de entre 5,5 y 7 y no soporta los terrenos arenosos o turbosos con acidez elevada.",
            "Durante la fermentación de la leche, las bacterias se multiplican rápidamente, duplicando su población cada 20 minutos. Son estas bacterias las que van “rompiendo” la lactosa y generando ácido láctico. Esta acidez provoca la coagulación de las proteínas transformando la textura y el sabor de la leche, dando lugar al yogurt.",
            "La leche y sus derivados se encuentran en numerosos alimentos. Las formas obvias de la leche son la crema, el queso, la manteca, el helado y el yogur. La leche y sus derivados también pueden estar ocultos en alimentos ingeridos habitualmente.",
            "Hay una serie de fases que deben llevarse a cabo para la elaboración artesanal de un queso: coagulación, corte, desuerado, prensado, salado y maduración. El resultado final será un producto natural, sano y con un sabor único y especial.",
            "La mantequilla se obtiene por el batido de la nata. Una vez obtenida la nata, ésta es sometida a diversos procedimientos para la elaboración de la mantequilla, entre éstos, la normalización, neutralización, pasteurización y maduración de la nata, batido, lavado y amasado",
            "Los huevos que consumimos se producen en explotaciones ganaderas donde se crían las gallinas y que, de forma general, albergan también las instalaciones para la clasificación, envasado y almacenamiento de los huevos.",
            "El proceso de elaboración consiste en refrigerar las carnes, luego éstas se trocean y curan, se pican y mezclan y finalmente se embuten en tripas y se escaldan. Opcionalmente se puede ahumar.",
            "El pescado se transforma en diferentes subproductos a través de los procesos de lavado, higienización, fileteado, descabezado y extracción de la piel. El producto obtenido podrá ser reprocesado o bien fraccionado como filetes, enteros, enteros eviscerados y/o descabezados, destinados a consumo humano.",
            "A diferencia de las gallinas ponedoras, los pollos de carne se crían en el suelo y la yacija les aisla del frío, amortigua su peso y absorbe las deyecciones. Puede ser de distintos materiales: viruta de madera, serrín, paja picada, etc.",
            "El proceso para lograr un tomate fresco y sabroso comienza con la siembra de una semilla seleccionada no transgénica. Tras varios días de riego y cuidado intensivo la planta de tomate alcanza los 15 centímetros, momento en el cual se resiembra en un lugar aconcionado para su crecimiento.",
            "Las zanahorias pequeños dedos, generalmente están listas para cosecharse entre los 50 a 60 días. Para otras variedades se debe permitir que crezcan hasta que alcancen por lo menos 3/4 de pulgada de diámetro (de 60 a 70 días después de plantar.)",
            "La lechuga es uno de los pocos vegetales que tolera un poco de sombra y el lugar de siembra ideal es el que recibe pleno sol y sombra al final de la tarde, especialmente al acercarse el verano. Los suelos bien drenados y ricos en materia orgánica brindan la base perfecta para todos los tipos de lechugas.",
            "El ají, pimiento o chile se cultiva principalmente por trasplante, por lo cual se requiere un semillero, si se utiliza semilla propia, esta debe provenir de plantas sanas y vigorosas, al igual que el fruto para evitar diseminar enfermedades."
    };

    String itemsAPresentar [][] = new String[4][5];
    ListView list;
    TextView txtTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_comida);

        Bundle data = getIntent().getExtras();
        String tipoComida = data.getString("tipoComida");

        int pos1 = 0, pos2 = 0;

        switch(tipoComida) {

            case "Frutas":
                pos1 = 0;
                pos2 = 3;
                break;

            case "Granos":
                pos1 = 4;
                pos2 = 7;
                break;

            case "Lacteos":
                pos1 = 8;
                pos2 = 11;
                break;

            case "Proteinas":
                pos1 = 12;
                pos2 = 15;
                break;

            case "Vegetales":
                pos1 = 16;
                pos2 = 19;
        }

        int contador = 0;

        for(int i = pos1; i <= pos2; i++){
            itemsAPresentar[contador][0] = infoComida[i][0];
            itemsAPresentar[contador][1] = infoComida[i][1];
            itemsAPresentar[contador][2] = infoComida[i][2];
            itemsAPresentar[contador][3] = recetas[i];
            contador++;
        }

        txtTitulo = findViewById(R.id.textoTipo);
        txtTitulo.setText("Tipo Seleccionado: " + tipoComida);

        list = findViewById(R.id.listaComidas);
        ComidaDescripcion comidaAdapter = new ComidaDescripcion();
        list.setAdapter(comidaAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent ventana = new Intent(TipoComida.this, InfoComida.class);
                ventana.putExtra("imagen", itemsAPresentar[i][0]);
                ventana.putExtra("nombre", itemsAPresentar[i][1]);
                ventana.putExtra("tipoComida",  tipoComida);
                ventana.putExtra("receta", itemsAPresentar[i][3]);

                startActivity(ventana);
            }
        });
    }

    public void onClickVolverTipos(View view){
        finish();
    }

    class ComidaDescripcion extends BaseAdapter{

        @Override
        public int getCount() {
            return itemsAPresentar.length;
        }

        @Override
        public Object getItem(int i) {
            return itemsAPresentar[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater =  LayoutInflater.from(TipoComida.this);
            view = inflater.inflate(R.layout.listview2, null);

            ImageView foodImage = view.findViewById(R.id.imagenComida);
            TextView nombreComida = view.findViewById(R.id.comidaEspecificaNombre);
            TextView descComida = view.findViewById(R.id.comidaDescripcionText);

            foodImage.setImageResource(Integer.valueOf(itemsAPresentar[i][0]));
            nombreComida.setText(itemsAPresentar[i][1]);
            descComida.setText(itemsAPresentar[i][2]);

            return view;
        }
    }

}