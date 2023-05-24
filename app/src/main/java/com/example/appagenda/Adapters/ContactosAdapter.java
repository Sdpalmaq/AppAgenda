package com.example.appagenda.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appagenda.Contacto;
import com.example.appagenda.Contactos;
import com.example.appagenda.R;
import com.example.appagenda.VerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.ViewHolder> {

    ArrayList<Contacto> listaContactos;
    ArrayList<Contacto> listaOriginal;


    public

    Context context;
    View view;

    public ContactosAdapter(ArrayList<Contacto> listaContactos) {

        this.listaContactos = listaContactos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaContactos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombre_tv.setText(listaContactos.get(position).Nombre);
        holder.telefono_tv.setText(listaContactos.get(position).Telefono);
        holder.direccion_tv.setText(listaContactos.get(position).Direccion);
        holder.email_tv.setText(listaContactos.get(position).Email);

    }

    public void filtrado(String txtBuscar)
    {
        int longitud = txtBuscar.length();
        if(longitud == 0){
            listaContactos.clear();
            listaContactos.addAll(listaOriginal);
        }else{
            List<Contacto> collection = listaContactos.stream().
                    filter(i -> i.Nombre.toLowerCase().contains(txtBuscar.toLowerCase()))
                    .collect(Collectors.toList());
            listaContactos.clear();
            listaContactos.addAll(collection);

        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return listaContactos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre_tv, telefono_tv, direccion_tv, email_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre_tv = itemView.findViewById(R.id.nombre_tv);
            telefono_tv = itemView.findViewById(R.id.telefono_tv);
            direccion_tv = itemView.findViewById(R.id.direccion_tv);
            email_tv = itemView.findViewById(R.id.email_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = view.getContext();
                    Intent intent  = new Intent(context, VerActivity.class);
                    intent.putExtra("Id", listaContactos.get(getAdapterPosition()).Id);
                    context.startActivity(intent);
                }
            });
        }
    }
}
