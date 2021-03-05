package com.ekaitz_torregrosa.memoryapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ekaitz_torregrosa.memoryapp.R;
import com.ekaitz_torregrosa.memoryapp.adapters.RecyclerPuntuacionesAdapter;
import com.ekaitz_torregrosa.memoryapp.models.PuntuacionBD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class DataFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerPuntuacionesAdapter recyclerDataAdapter;
    private DataListener callback;

    private static String URL = "https://servermemoryapp.herokuapp.com/punctuations";
    List<PuntuacionBD> jsonPuntuaciones = new ArrayList<>();

    public DataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            callback = (DataListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + "should implement DataListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewPuntuaciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        volleyGet();

        recyclerDataAdapter = new RecyclerPuntuacionesAdapter(jsonPuntuaciones);
        recyclerView.setAdapter(recyclerDataAdapter);

        recyclerDataAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.sendData(
                        jsonPuntuaciones.get(recyclerView.getChildAdapterPosition(view)).getPuntos(),
                        jsonPuntuaciones.get(recyclerView.getChildAdapterPosition(view)).getMovimientos(),
                        jsonPuntuaciones.get(recyclerView.getChildAdapterPosition(view)).getTiempo(),
                        jsonPuntuaciones.get(recyclerView.getChildAdapterPosition(view)).getUsuario(),
                        jsonPuntuaciones.get(recyclerView.getChildAdapterPosition(view)).getDificultad()
                );
            }
        });

        return view;
    }

    public void volleyGet() {

        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, response -> {

            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    PuntuacionBD puntuacion = new PuntuacionBD(
                            jsonObject.getInt("points"),
                            jsonObject.getInt("moves"),
                            jsonObject.getString("time"),
                            jsonObject.getString("name"),
                            jsonObject.getString("dificulty")
                    );
                    
                    jsonPuntuaciones.add(puntuacion);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Collections.sort(jsonPuntuaciones, new Comparator<PuntuacionBD>() {
                @Override
                public int compare(PuntuacionBD o1, PuntuacionBD o2) {
                    return new Integer(o2.getPuntos()).compareTo(new Integer(o1.getPuntos()));
                }
            });
            recyclerDataAdapter.notifyDataSetChanged();

        }, error -> error.printStackTrace());


        requestQueue.add(jsonArrayRequest);

    }

    public interface DataListener {
        public void sendData(int puntos, int movimientos, String tiempo, String usuario, String dificultad);
    }

}