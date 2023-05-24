package com.example.appagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    Contactos contactos;

    EditText txtId, txtNombre, txtTelefono, txtDireccion, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        contactos = new Contactos(this, "agenda.db", 1);
        txtId =findViewById(R.id.txtIdAct);
        txtNombre = findViewById(R.id.txtNombreAct);
        txtDireccion = findViewById(R.id.txtDireccionAct);
        txtTelefono = findViewById(R.id.txtTelefonoAct);
        txtEmail = findViewById(R.id.txtEmailAct);

    }

    public void cmdAdd_onClick (View v)
    {

        Contacto con = contactos.Create(
                txtId.getText().toString(),
                txtNombre.getText().toString(),
                txtTelefono.getText().toString(),
                txtDireccion.getText().toString(),
                txtEmail.getText().toString()
        );

        if(con !=null) {
            Toast.makeText(this, "CONTACTO INSERTADO OK", Toast.LENGTH_SHORT).show();
            verRegistro();
        }else {
            Toast.makeText(this, "ERROR !! CONTACTO NO INSERTADO", Toast.LENGTH_SHORT).show();
        }
    }

    public void cmdCacel_onClick(View v)
    {
        verRegistro();
    }

    //VOLVER AL PRINCIPIO
    private void verRegistro()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}