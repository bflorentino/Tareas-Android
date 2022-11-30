package com.example.recyclerviewapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    String crear = "Create table comida(id integer primary key autoincrement, nombre text, tipo text, imagenValor int)";
    SQLiteDatabase db;

    public BaseDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(crear);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists comida");
        sqLiteDatabase.execSQL(crear);
    }

    // Devuelve la conexion a la base de datos
    private SQLiteDatabase getDb (){
        db = getWritableDatabase();
        return db;
    }

    // Insertar una comida
    public boolean onInsertFood(Comida comida){

        if(this.getDb() != null){

            try{
                ContentValues registro = new ContentValues();
                registro.put("nombre", comida.getNombre());
                registro.put("tipo", comida.getTipo());
                registro.put("imagenValor", comida.getImagenValor());

                db.insert("comida", null, registro);
                return true;
            }
            catch (Exception e){
                return false;
            }
        }
        return false;
    }

    // Obtener una comida por id
    public Comida getFoodById(int id){

        if(this.getDb() != null){
            try{
                String[]x = new String[]{String.valueOf(id)};
                String query = "select id, nombre, tipo, imagenValor from comida where id = ?";
                Cursor data = db.rawQuery(query, x);

                Comida comida = new Comida();

                if(data.moveToFirst()){

                    comida.setId(data.getInt(0));
                    comida.setNombre(data.getString(1)) ;
                    comida.setTipo(data.getString(2)) ;
                    comida.setImagenValor(data.getInt(3)); ;
                }

                return comida;
            }
            catch (Exception e){
                return null;
            }
        }
        return null;
    }

    // Obtener todas las comidas
    public ArrayList<Comida> getAllFood(){

        if(this.getDb() != null){
            try{
                String query = "select id, nombre, tipo, imagenValor from comida";
                Cursor data = db.rawQuery(query, null);

                ArrayList<Comida> listaComidas = new ArrayList<>();

                if(data.moveToFirst()){

                    do{
                        Comida comida = new Comida();
                        comida.setId(data.getInt(0));
                        comida.setNombre(data.getString(1)) ;
                        comida.setTipo(data.getString(2)) ;
                        comida.setImagenValor(data.getInt(3)) ;

                        listaComidas.add(comida);
                        System.out.println(comida.getNombre());
                    }
                    while (data.moveToNext());
                }
                return listaComidas;
            }
            catch (Exception e){
                System.out.println("Un error aqiu");
                return null;
            }
        }
        return null;
    }

    // Actualizar una Comida
    public boolean onUpdateFood (Comida comida){

        if(this.getDb() != null){

            try{
                ContentValues registro = new ContentValues();
                registro.put("nombre", comida.getNombre());
                registro.put("tipo", comida.getTipo());
                registro.put("imagenValor", comida.getImagenValor());

                String[] args = new String[]{String.valueOf(comida.getId())};

                db.update("comida",  registro, "id = ?", args);
                return true;
            }
            catch (Exception e){
                return false;
            }
        }
        return false;
    }

    // Eliminar una comida
    public boolean onDeleteFood(int id){
        if(this.getDb() != null){

            try{
                String[] args = new String[]{String.valueOf(id)};
                db.delete("comida", "id = ?", args);
                return true;
            }
            catch (Exception e){
                return false;
            }
        }
        return false;
    }
}