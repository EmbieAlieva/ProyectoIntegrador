package com.ekaitz_torregrosa.memoryapp.adapters;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ekaitz_torregrosa.memoryapp.R;
import com.ekaitz_torregrosa.memoryapp.models.PuntuacionBD;

import java.util.List;

import io.realm.RealmResults;

public class RecyclerPuntuacionesAdapter extends RecyclerView.Adapter<RecyclerPuntuacionesAdapter.RecyclerDataHolder>
        implements View.OnClickListener  {

    private List<PuntuacionBD> listPuntuaciones;
    private View.OnClickListener listener;

    public RecyclerPuntuacionesAdapter(List<PuntuacionBD> listPuntuaciones) {
        this.listPuntuaciones = listPuntuaciones;
    }

    @NonNull
    @Override
    public RecyclerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_puntuaciones,null, false);
        view.setOnClickListener(this);

        return new RecyclerDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDataHolder holder, int position) {
        holder.assignData(listPuntuaciones.get(position), position);

    }
    @Override
    public int getItemCount() {

        return listPuntuaciones.size();
    }

    public void setOnClickListener(View.OnClickListener listener){

        this.listener=listener;
    }
    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public class RecyclerDataHolder extends RecyclerView.ViewHolder{

        TextView textViewPosicion;
        TextView textViewPuntos;
        TextView textViewUsuario;

        public RecyclerDataHolder(@NonNull View itemView) {
            super(itemView);
            textViewPosicion = (TextView)itemView.findViewById(R.id.textViewPosicion);
            textViewPuntos = (TextView)itemView.findViewById(R.id.textViewPuntos);
            textViewUsuario = (TextView)itemView.findViewById(R.id.textViewUsuario);

        }


        public void assignData(PuntuacionBD puntuacion, int position) {

            this.textViewPosicion.setText(String.valueOf(++position)+"º");
            this.textViewPuntos.setText(String.valueOf(puntuacion.getPuntos()));
            this.textViewUsuario.setText(puntuacion.getUsuario());


        }
    }
}
