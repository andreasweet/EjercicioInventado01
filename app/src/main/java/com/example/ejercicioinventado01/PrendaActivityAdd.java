package com.example.ejercicioinventado01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.ejercicioinventado01.Modelos.Prenda;
import com.example.ejercicioinventado01.databinding.ActivityPrendaAddBinding;

public class PrendaActivityAdd extends AppCompatActivity {


    private ActivityPrendaAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenda_add);

        binding = ActivityPrendaAddBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnCrearPrenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prenda prenda = crearPrenda();
            }
        });

    }

    private Prenda crearPrenda() {
        if(binding.spColorPrendaAdd.getSelectedItemPosition() != 0 &&
        !binding.txtNombrePrendaAdd.getText().toString().isEmpty() &&
        !binding.txtTallaPrendaAdd.getText().toString().isEmpty() &&
        !binding.txtEstacionPrendaAdd.getText().toString().isEmpty()){

            return new Prenda(binding.txtNombrePrendaAdd.getText().toString(),Integer.parseInt(binding.txtTallaPrendaAdd.getText().toString()), binding.txtEstacionPrendaAdd.getText().toString(), binding.spColorPrendaAdd.getSelectedItem().toString());
        }
        return null;
    }
}