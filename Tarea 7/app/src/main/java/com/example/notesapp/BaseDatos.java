package com.example.notesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    String crear = "Create table notas(id integer primary key autoincrement, titulo text, cuerpo text)";
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
            sqLiteDatabase.execSQL("Drop table if exists notas");
            sqLiteDatabase.execSQL(crear);
    }

    // Devuelve la conexion a la base de datos
    private SQLiteDatabase getDb (){
        db = getWritableDatabase();
        return db;
    }

    // Insertar una nota
    public boolean onInsertNota(Nota nota){

        if(this.getDb() != null){

            try{
                ContentValues registro = new ContentValues();
                registro.put("titulo", nota.getTitulo());
                registro.put("cuerpo", nota.getCuerpo());

                db.insert("notas", null, registro);
                return true;
            }
            catch (Exception e){
                return false;
            }
        }
        return false;
    }

    // Obtener una nota por id
    public Nota getNotaById(int id){

        if(this.getDb() != null){
            try{
                String[]x = new String[]{String.valueOf(id)};
                String query = "select id, titulo, cuerpo from notas where id = ?";
                Cursor data = db.rawQuery(query, x);

                Nota nota = new Nota();

                if(data.moveToFirst()){

                    nota.setId(data.getInt(0));
                    nota.setTitulo(data.getString(1)) ;
                    nota.setCuerpo(data.getString(2)) ;
                }

                return nota;
            }
            catch (Exception e){
                return null;
            }
        }
        return null;
    }

    // Obtener todas las notas
    public ArrayList<Nota> getAllNotas(){

        if(this.getDb() != null){
            try{
                String query = "select id, titulo, cuerpo from notas";
                Cursor data = db.rawQuery(query, null);

                ArrayList<Nota> listaNotas = new ArrayList<>();

                if(data.moveToFirst()){

                    do{
                        Nota nota = new Nota();
                        nota.setId(data.getInt(0));
                        nota.setTitulo(data.getString(1)) ;
                        nota.setCuerpo(data.getString(2)) ;

                        listaNotas.add(nota);
                    }
                    while (data.moveToNext());
                }
                return listaNotas;
            }
            catch (Exception e){
                System.out.println("Un error aqiu");
                return null;
            }
        }
        return null;
    }

    // Actualizar una nota
    public boolean onUpdateNota (Nota nota){

        if(this.getDb() != null){

            try{
                ContentValues registro = new ContentValues();
                registro.put("titulo", nota.getTitulo());
                registro.put("cuerpo", nota.getCuerpo());

                String[] args = new String[]{String.valueOf(nota.getId())};

                db.update("notas",  registro, "id = ?", args);
                return true;
            }
            catch (Exception e){
                return false;
            }
        }
        return false;
    }

    // Eliminar una nota
    public boolean onDeleteNota(int id){
        if(this.getDb() != null){

            try{
                String[] args = new String[]{String.valueOf(id)};
                db.delete("notas", "id = ?", args);
                return true;
            }
            catch (Exception e){
                return false;
            }
        }
        return false;
    }
}