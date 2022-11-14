package com.example.ejercicioinventado01.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicioinventado01.Modelos.Prenda;
import com.example.ejercicioinventado01.R;

import java.util.List;

public class PrendaAdapter extends RecyclerView.Adapter<PrendaAdapter.ProductoVH> {

    private List<Prenda> objects;
    private int resource;
    private Context context;

    public PrendaAdapter(List<Prenda> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //detecta la creacion de una nueva card

        View prendaView = LayoutInflater.from(context).inflate(resource,null);
        prendaView.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return new ProductoVH(prendaView);
    }
    @Override
    public void onBindViewHolder(@NonNull ProductoVH holder, int position) {
        //salta cuando se enlaza la card a su contenido
        Prenda prenda = objects.get(position);
        holder.lblNombre.setText(prenda.getNombre());
        holder.lblTalla.setText(String.valueOf(prenda.getTalla()));
        holder.lblEstacion.setText(prenda.getEstacion());
        holder.lblColor.setText(prenda.getColor());
        String color = prenda.getColor();
        switch(color){
            case "Rojo":
                holder.itemView.setBackgroundColor(Color.parseColor("#FFC62828"));
                break;
            case "Blanco":
                holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

                break;
            case "Lila":
                holder.itemView.setBackgroundColor(Color.parseColor("#FFD49DF6"));
                break;
            case "Rosa":
                holder.itemView.setBackgroundColor(Color.parseColor("#FFF6A7CA"));
                break;
            case "Gris":
                holder.itemView.setBackgroundColor(Color.parseColor("#FF958B8F"));
                break;
        }


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmarDelete(prenda, holder.getAdapterPosition()).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarPrenda(prenda, holder.getAdapterPosition()).show();
            }
        });
    }

    private AlertDialog confirmarDelete(Prenda p, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Seguro que quieres borrar!!");
        builder.setCancelable(false);
        builder.setNegativeButton("SALIR", null);
        builder.setPositiveButton("ELIMINAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                objects.remove(p);
                notifyItemRemoved(position);
            }
        });
        return builder.create();
    }

    private AlertDialog editarPrenda(Prenda prenda, int posicion){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(prenda.getNombre());
        builder.setCancelable(false);

        View cuerpoAlert = LayoutInflater.from(context).inflate(R.layout.activity_prenda_add,null);
        EditText txtNombre = cuerpoAlert.findViewById(R.id.txtNombrePrendaAdd);
        txtNombre.setVisibility(View.GONE);
        EditText btnCrear = cuerpoAlert.findViewById(R.id.btnCrearPrenda);
        btnCrear.setVisibility(View.GONE);
        EditText txtTalla = cuerpoAlert.findViewById(R.id.txtTallaPrendaAdd);
        txtTalla.setText(prenda.getTalla());
        EditText txtEstacion = cuerpoAlert.findViewById(R.id.txtEstacionPrendaAdd);
        txtEstacion.setText(prenda.getEstacion());
        Spinner spColor = cuerpoAlert.findViewById(R.id.spColorPrendaAdd);
        for (int i = 0; i <spColor.getCount() ; i++) {
            if(prenda.getColor() == spColor.getItemAtPosition(i)){
                spColor.setSelection(i);
            }
        }

        builder.setNegativeButton("CANCELAR", null);
        builder.setPositiveButton("MODIFICAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!txtTalla.getText().toString().isEmpty() &&
                        !txtEstacion.getText().toString().isEmpty()
                         && spColor.getSelectedItemPosition() != 0){
                    prenda.setTalla(Integer.parseInt(txtTalla.getText().toString()));
                    prenda.setEstacion(txtEstacion.getText().toString());
                    prenda.setColor(spColor.getSelectedItem().toString());
                }
            }
        });
        return builder.create();
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ProductoVH extends RecyclerView.ViewHolder{

        TextView lblNombre;
        TextView lblTalla;
        TextView lblEstacion;
        TextView lblColor;
        ImageButton btnDelete;


        public ProductoVH(@NonNull View itemView) {
            super(itemView);
            lblNombre = itemView.findViewById(R.id.lblNombrePrendaCard);
            lblTalla = itemView.findViewById(R.id.lblTallaPrendaCard);
            lblEstacion = itemView.findViewById(R.id.lblEstacionPrendaCard);
            lblColor = itemView.findViewById(R.id.lblColorPrendaCard);
            btnDelete = itemView.findViewById(R.id.btnDeleteCard);
        }
    }
}
