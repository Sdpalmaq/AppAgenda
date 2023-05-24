package com.example.appagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.appagenda.Adapters.ContactosAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    RecyclerView recycler_view;

    Contactos contactos;
    ContactosAdapter contactosAdapter;

    ArrayList<Contacto> listaArrayContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        txtBuscar = findViewById(R.id.txtBuscar);

        contactos = new Contactos(this, "agenda.db", 1);
        listaArrayContactos = new ArrayList<>();

        contactosAdapter = new ContactosAdapter(contactos.mostrartContactos());
        recycler_view.setAdapter(contactosAdapter);

        txtBuscar.setOnQueryTextListener(this);

    }

    public void cmdAddActivity_onClick(View v){
        //lanzar el activity de Add nuevo contacto

        //1.Intanciamos un objeto de la clase Intent

        Intent i = new Intent(this, AddActivity.class);

        startActivity(i);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        contactosAdapter.filtrado(newText);
        return false;
    }
}