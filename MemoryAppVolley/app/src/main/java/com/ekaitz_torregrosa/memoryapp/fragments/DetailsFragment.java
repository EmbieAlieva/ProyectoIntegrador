package com.ekaitz_torregrosa.memoryapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ekaitz_torregrosa.memoryapp.R;


public class DetailsFragment extends Fragment {


    TextView textViewUsuario;
    TextView textViewDificultad;
    TextView textViewTiempo;
    TextView textViewMovimientos;
    TextView textViewPuntos;

    public DetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        textViewUsuario= (TextView) view.findViewById(R.id.textViewUsuario);
        textViewDificultad= (TextView) view.findViewById(R.id.textViewDificultad);
        textViewTiempo= (TextView) view.findViewById(R.id.textViewTiempo);
        textViewMovimientos= (TextView) view.findViewById(R.id.textViewMovimientos);
        textViewPuntos= (TextView) view.findViewById(R.id.textViewPuntos);


        // Inflate the layout for this fragment
        return view;
    }

    public void renderData(int puntos, int movimientos, String tiempo, String usuario, String dificultad){
        textViewPuntos.setText(String.valueOf(puntos));
        textViewMovimientos.setText(String.valueOf(movimientos));
        textViewTiempo.setText(tiempo);
        textViewUsuario.setText(usuario);
        switch (dificultad){
            case "Fácil":
                dificultad = String.valueOf(getResources().getString(R.string.easy));
                break;
            case "Media":
                dificultad = String.valueOf(getResources().getString(R.string.medium));
                break;
            case "Difícil":
                dificultad = String.valueOf(getResources().getString(R.string.hard));
                break;
            default:
                break;
        }
        textViewDificultad.setText(dificultad);
    }
}