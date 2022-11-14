package com.example.ejercicioinventado01;

import android.content.Intent;
import android.os.Bundle;

import com.example.ejercicioinventado01.Adapters.PrendaAdapter;
import com.example.ejercicioinventado01.Modelos.Prenda;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import com.example.ejercicioinventado01.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Prenda> prendasList;
    private ActivityResultLauncher<Intent> launcherCrearPrendas;

    private PrendaAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        prendasList = new ArrayList<>();
        inicializarLaunchers();


        adapter = new PrendaAdapter(prendasList, R.layout.prenda_model_card, this);
        layoutManager = new GridLayoutManager(this, 1);
        binding.contentMain.contenedor.setAdapter(adapter);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherCrearPrendas.launch(new Intent(MainActivity.this,PrendaActivityAdd.class));
            }
        });
    }

    private void inicializarLaunchers() {
        launcherCrearPrendas = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        if (result.getData().getExtras() != null) {
                            if (result.getData().getExtras().getSerializable("PRENDA") != null) {

                                Prenda prenda = (Prenda) result.getData().getExtras().getSerializable("PRENDA");
                                prendasList.add(prenda);
                                adapter.notifyItemInserted(prendasList.size()-1);

                            } else {
                                Toast.makeText(MainActivity.this, "NO HAY DATOS", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "NO HAY BUNDLE EN EL INTENT", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "NO HAY INTENT", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "VENTANA CANCELADA", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}