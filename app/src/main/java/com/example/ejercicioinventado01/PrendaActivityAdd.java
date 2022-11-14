package com.example.ejercicioinventado01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
                if(prenda != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PRENDA",prenda);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(PrendaActivityAdd.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private Prenda crearPrenda() {
        if(binding.spColorPrendaAdd.getSelectedItemPosition() != 0 &&
        !binding.txtNombrePrendaAdd.getText().toString().isEmpty() &&
        !binding.txtTallaPrendaAdd.getText().toString().isEmpty() &&
        !binding.txtEstacionPrendaAdd.getText().toString().isEmpty()){
            System.out.println("eeee");
            return new Prenda(binding.txtNombrePrendaAdd.getText().toString(),Integer.parseInt(binding.txtTallaPrendaAdd.getText().toString()), binding.txtEstacionPrendaAdd.getText().toString(), binding.spColorPrendaAdd.getSelectedItem().toString());
        }
        return null;
    }
}