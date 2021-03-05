package com.ekaitz_torregrosa.memoryapp.adapters;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ekaitz_torregrosa.memoryapp.R;
import com.ekaitz_torregrosa.memoryapp.models.Card;

import java.util.ArrayList;
import java.util.List;


public class RecyclerBoardAdapter extends RecyclerView.Adapter<RecyclerBoardAdapter.RecyclerDataHolder> implements View.OnClickListener {

    private List<Card> listCards;
    private ArrayList<Card> cartasVolteadas = new ArrayList<>();
    private CardClickListener cardClickListener;
    private int numColums;
    static int MARGIN_SIZE = 10;


    //Constructor listCards, numColums, ClickListener
    public RecyclerBoardAdapter(List<Card> listCards, int numColums, CardClickListener cardClickListener) {
        this.listCards = listCards;
        this.numColums = numColums;
        this.cardClickListener = cardClickListener;
    }

    @Override
    public RecyclerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Anchura de la carta = anchura del padre / numColums - 2 veces el MARGINSIZE (para dejar marjen a cada lado)
        int cardWidth = parent.getWidth() / numColums - (2 * MARGIN_SIZE);
        //Altura de la carta = altura del padre / las filas que hay - 2 veces el MARGINSIZE (para dejar marjen arriba y abajo)
        int cardHeigth = parent.getHeight() / (listCards.size()/numColums) - (2 * MARGIN_SIZE);
        int cardSizeLength = Math.min(cardWidth, cardHeigth);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.findViewById(R.id.cardView).getLayoutParams();
        layoutParams.width = cardSizeLength;
        layoutParams.height = cardSizeLength;
        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE);

        view.setOnClickListener(this);
        return new RecyclerDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDataHolder holder, int position) {
        holder.assignData(position);

    }

    @Override
    public int getItemCount() {
        return listCards.size();
    }


    @Override
    public void onClick(View view) {

    }

    //Interfaz onCardClick para implementar al declarar el adapter en el GameActivity
    public interface CardClickListener{
        public void onCardClicked(int position);
    }

    public class RecyclerDataHolder extends RecyclerView.ViewHolder {

        ImageButton imagen;

        public RecyclerDataHolder(@NonNull View itemView) {
            super(itemView);
            imagen = (ImageButton) itemView.findViewById(R.id.imageButton);

        }

        public void assignData(int position) {
            //¿Está emparejada?
            if (listCards.get(position).isEmparejada()){
                //Si esta emparejada pone la imagen emparejada
                this.imagen.setImageResource(listCards.get(position).getImageEmparejada());
            }else{
                //If ternario para poner la imagen voteada o una por defecto
                this.imagen.setImageResource(listCards.get(position).isVolteada() ? listCards.get(position).getImage() : R.drawable.background );
            }

            //ImageButton OnClick
            this.imagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Si no esta emparejada ni volteada llama a la interfaz onClick
                    if (!listCards.get(position).isEmparejada() && !listCards.get(position).isVolteada()){
                        cardClickListener.onCardClicked(position);

                    }


                }
            });


        }

    }
}
