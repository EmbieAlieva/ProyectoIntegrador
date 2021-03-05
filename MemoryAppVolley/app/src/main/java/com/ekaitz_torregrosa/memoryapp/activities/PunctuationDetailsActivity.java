package com.ekaitz_torregrosa.memoryapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ekaitz_torregrosa.memoryapp.R;
import com.ekaitz_torregrosa.memoryapp.fragments.DetailsFragment;

import java.util.Date;

public class PunctuationDetailsActivity extends AppCompatActivity {
    private int puntos;
    private int movimientos;
    private String tiempo;
    private String usuario;
    private String dificultad;
    private String fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);



        if (getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();

            puntos = bundle.getInt("puntos");
            movimientos = bundle.getInt("movimientos");
            tiempo = bundle.getString("tiempo");
            usuario = bundle.getString("usuario");
            dificultad = bundle.getString("dificultad");
            fecha = bundle.getString("fecha");






        }

        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        detailsFragment.renderData(puntos, movimientos, tiempo, usuario, dificultad);
    }
}

