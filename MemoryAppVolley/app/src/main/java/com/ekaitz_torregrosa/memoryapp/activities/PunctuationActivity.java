package com.ekaitz_torregrosa.memoryapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ekaitz_torregrosa.memoryapp.fragments.DataFragment;
import com.ekaitz_torregrosa.memoryapp.fragments.DetailsFragment;
import com.ekaitz_torregrosa.memoryapp.R;

public class PunctuationActivity extends AppCompatActivity implements DataFragment.DataListener {

    boolean isMultiPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punctuation);
        setMultiPanel();

        ActionBar actionbar = getSupportActionBar();
        actionbar.show();
    }

    @Override
    public void sendData(int puntos, int movimientos, String tiempo, String usuario, String dificultad) {
        if (isMultiPanel){
            DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
            detailsFragment.renderData(puntos, movimientos, tiempo, usuario, dificultad);
        }else {
            Intent intent = new Intent(this, PunctuationDetailsActivity.class);
            intent.putExtra("puntos", puntos);
            intent.putExtra("movimientos", movimientos);
            intent.putExtra("tiempo", tiempo);
            intent.putExtra("usuario", usuario);
            intent.putExtra("dificultad", dificultad);

            startActivity(intent);
        }
    }

    private void setMultiPanel(){
        isMultiPanel = (getSupportFragmentManager().findFragmentById(R.id.detailsFragment)!=null);
    }
}
