package com.example.appagenda;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText txtIdAct, txtNombreAct, txtTelefonoAct, txtDireccionAct, txtEmailAct;
    Button cmdSave;

    Contactos contactos;

    boolean correcto = false;

    Contacto contacto;
    String id ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtIdAct = findViewById(R.id.txtIdAct);
        txtNombreAct = findViewById(R.id.txtNombreAct);
        txtTelefonoAct = findViewById(R.id.txtTelefonoAct);
        txtDireccionAct = findViewById(R.id.txtDireccionAct);
        txtEmailAct = findViewById(R.id.txtEmailAct);
        cmdSave = findViewById(R.id.cmdSaveAct);

        if(savedInstanceState == null ){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = "";
            }else{
                id = extras.getString("Id");

            }
        }else{
            id = (String) savedInstanceState.getSerializable("Id");
        }

        contactos = new Contactos(this, "agenda.db", 1);

        contacto = contactos.Read_One(id);

        if(contacto != null){
            txtIdAct.setText(contacto.Id);
            txtNombreAct.setText(contacto.Nombre);
            txtTelefonoAct.setText(contacto.Telefono);
            txtDireccionAct.setText(contacto.Direccion);
            txtEmailAct.setText(contacto.Email);
        }

        cmdSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNombreAct.getText().toString().equals("") && !txtTelefonoAct.getText().toString().equals(""))
                {
                    correcto = contactos.Update(
                            id,
                            txtNombreAct.getText().toString(),
                            txtTelefonoAct.getText().toString(),
                            txtDireccionAct.getText().toString(),
                            txtEmailAct.getText().toString()
                            );
                    if (correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    }else{
                        Toast.makeText(EditarActivity.this, "ERROR !! CONTACTO NO INSERTADO", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR REGISTRO", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void verRegistro()
    {
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("Id", id);
        startActivity(intent);
    }
}