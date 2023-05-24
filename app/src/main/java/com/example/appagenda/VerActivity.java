package com.example.appagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtIdAct, txtNombreAct, txtTelefonoAct, txtDireccionAct, txtEmailAct;
    Button cmdSave, cmdDelete;
    AlertDialog.Builder builder;


    FloatingActionButton fabEditar;

    Contactos contactos;

    Contacto contacto;
    String id = "";

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
        fabEditar = findViewById(R.id.fabeditar);
        cmdDelete = findViewById(R.id.cmdDelete);
        builder = new AlertDialog.Builder(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = "";
            } else {
                id = extras.getString("Id");

            }
        } else {
            id = (String) savedInstanceState.getSerializable("Id");
        }

        contactos = new Contactos(this, "agenda.db", 1);

        contacto = contactos.Read_One(id);

        if (contacto != null) {
            txtIdAct.setText(contacto.Id);
            txtNombreAct.setText(contacto.Nombre);
            txtTelefonoAct.setText(contacto.Telefono);
            txtDireccionAct.setText(contacto.Direccion);
            txtEmailAct.setText(contacto.Email);
            cmdSave.setVisibility(View.INVISIBLE);
            txtIdAct.setInputType(InputType.TYPE_NULL);
            txtNombreAct.setInputType(InputType.TYPE_NULL);
            txtTelefonoAct.setInputType(InputType.TYPE_NULL);
            txtDireccionAct.setInputType(InputType.TYPE_NULL);
            txtEmailAct.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("Id", id);
                startActivity(intent);
            }
        });

        cmdDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Alerta !!")
                        .setMessage("Estas seguro de eliminar este contacto")
                        .setCancelable(true)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean resultado = contactos.Delete(id);
                                if(resultado == true) {
                                    Toast.makeText(VerActivity.this, "REGISTRO BORRADO OK ", Toast.LENGTH_SHORT).show();
                                    verRegistro();

                                }else {
                                    Toast.makeText(VerActivity.this, "ERROR: REGISTRO NO BORRADO OK ", Toast.LENGTH_SHORT).
                                            show();

                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                            }
                        })
                        .show();
            }
        });

    }

    public void cmdCacl_onClick(View v)
    {
        verRegistro();
    }

    private void verRegistro()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
