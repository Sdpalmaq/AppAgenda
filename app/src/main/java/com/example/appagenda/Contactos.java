package com.example.appagenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Contactos {

    private DBHellper dbhellper;

    private SQLiteDatabase db;

    public Contactos(Context contexto, String dbName, int version){
        dbhellper = new DBHellper(contexto, dbName, null, version);
    }

    public Contacto Create(String id, String nombre, String telefono, String direccion, String email){
        db = dbhellper.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("id",id);
        row.put("nombre",nombre);
        row.put("telefono",telefono);
        row.put("direccion",direccion);
        row.put("email",email);

        long qty = db.insert("contactos", null, row);

        if (qty > 0){
            Contacto data = new Contacto();
            data.Id = id;
            data.Nombre = nombre;
            data.Telefono = telefono;
            data.Direccion = direccion;
            data.Email = email;

            return data;
        }else {
            return null;
        }
    }

    public ArrayList<Contacto> mostrartContactos()
    {
        db = dbhellper.getReadableDatabase();

        ArrayList<Contacto> listaContacto = new ArrayList<>();
        Contacto contacto = null;

        Cursor cr = db.rawQuery("SELECT id, nombre, telefono, direccion, email FROM contactos ORDER BY nombre", null);

        if(cr.moveToFirst()){
            do{
                contacto = new Contacto();
                contacto.Id = cr.getString(0);
                contacto.Nombre = cr.getString(1);
                contacto.Telefono = cr.getString(2);
                contacto.Direccion = cr.getString(3);
                contacto.Email = cr.getString(4);

                listaContacto.add(contacto);
                System.out.println("FO"+cr.getString(0));
            } while (cr.moveToNext());
        }
         cr.close();
         return listaContacto;

    }

    public Contacto Read_One(String id){
        db = dbhellper.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT id, nombre, telefono, direccion, email FROM contactos WHERE id = '" + id + "'",
                null);

        if (cr.getCount() > 0 ){

            Contacto contacto = new Contacto();

            cr.moveToNext();
            contacto.Id = cr.getString(0);
            contacto.Nombre = cr.getString(1);
            contacto.Telefono = cr.getString(2);
            contacto.Direccion = cr.getString(3);
            contacto.Email = cr.getString(4);

            return contacto;
        }else{
            return null;
        }
    }

    public Contacto[] Read_All(){
        db = dbhellper.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT id, nombre, telefono, direccion, email FROM contactos ORDER BY nombre", null);

        if (cr.getCount() > 0 ){
            Contacto[] datos = new Contacto[cr.getCount()];
            Contacto contacto;
            int i = 0;

            while ( cr.moveToNext()) {
                contacto = new Contacto();
                contacto.Id = cr.getString(0);
                contacto.Nombre = cr.getString(1);
                contacto.Telefono = cr.getString(2);
                contacto.Direccion = cr.getString(3);
                contacto.Email = cr.getString(4);
            }
            return datos;
        }else{
            return null;
        }
    }

    public Contacto[] Read_ByNombre(String find){
        db = dbhellper.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT id, nombre, telefono, direccion, email FROM contactos WHERE nombre LIKE '%" + find + "%' ORDER BY nombre", null);

        if (cr.getCount() > 0 ){
            Contacto[] datos = new Contacto[cr.getCount()];
            Contacto contacto;
            int i = 0;

            while ( cr.moveToNext()) {
                contacto = new Contacto();
                contacto.Id = cr.getString(0);
                contacto.Nombre = cr.getString(1);
                contacto.Telefono = cr.getString(2);
                contacto.Direccion = cr.getString(3);
                contacto.Email = cr.getString(4);
            }
            return datos;
        }else{
            return null;
        }
    }

    public boolean Update(String id, String nombre, String telefono, String direccion, String email){
        db = dbhellper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("id", id);
        row.put("nombre", nombre);
        row.put("telefono", telefono);
        row.put("direccion", direccion);
        row.put("email", email);

        int qty = db.update("contactos", row, "id= '" + id + "'", null);

        return qty > 0;

    }

    public boolean Delete(String id){
        db = dbhellper.getWritableDatabase();

        int qty = db.delete("contactos", "id= '" + id + "'", null);
        return qty > 0;
    }
}
