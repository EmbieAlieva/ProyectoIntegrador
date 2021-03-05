package com.ekaitz_torregrosa.memoryapp.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ekaitz_torregrosa.memoryapp.R;
import com.ekaitz_torregrosa.memoryapp.Utils.Util;
import com.ekaitz_torregrosa.memoryapp.adapters.RecyclerBoardAdapter;
import com.ekaitz_torregrosa.memoryapp.dialogs.UserNameDialog;
import com.ekaitz_torregrosa.memoryapp.models.Card;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity implements UserNameDialog.UserNameDialogListener {

    RecyclerView recyclerViewBoard;
    TextView textViewNumMoves, textViewNumPairs, textViewTemporizador;
    List<Card> listCards, listCards2, listCards3, selectedCards;
    int numColums = 2;
    int numPairs = 4;
    int numPairsAcert = 0;
    int numMoves = 0;
    int temporizador = 30000;
    int cntSegAscen = 0;
    String dificultad = "Fácil";
    String cuentaAtrasIni = "00:30";
    RecyclerBoardAdapter adapter;
    CountDownTimer countDownTimer;
    boolean terminado;

    private static String URL = "https://servermemoryapp.herokuapp.com/punctuations";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        recyclerViewBoard = (RecyclerView) findViewById(R.id.recyclerViewBoard);
        textViewNumMoves = (TextView) findViewById(R.id.textViewNumMoves);
        textViewNumPairs = (TextView) findViewById(R.id.textViewNumPairs);
        textViewTemporizador = (TextView) findViewById(R.id.textViewTemporizador);

        textViewNumPairs.setText(String.valueOf(getResources().getString(R.string.pairs) + numPairsAcert + "/" + numPairs));
        textViewNumMoves.setText(String.valueOf(getResources().getString(R.string.moves) + numMoves));

        listCards = new ArrayList<>();
        selectedCards = new ArrayList<>();
        //LLamar función para generar la lista de Cards random
        getRndList(numPairs);

    }

    //Seleccionar dificultades
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optionEasy:
                dificultad = "Fácil";
                numPairs = 4;
                numColums = 2;
                temporizador = 30000;
                cuentaAtrasIni = "00:30";
                break;
            case R.id.optionMedium:
                dificultad = "Media";
                numPairs = 9;
                numColums = 3;
                temporizador = 60000;
                cuentaAtrasIni = "01:00";
                break;
            case R.id.optionHard:
                dificultad = "Difícil";
                numPairs = 12;
                numColums = 4;
                temporizador = 90000;
                cuentaAtrasIni = "01:30";
                break;
            case R.id.reinicio:
                reiniciarCards();
                terminado = false;
                break;
            default:
                break;
        }

        //Parar temporizador
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        //Reiniciar las cartas
        reiniciarCards();
        numPairsAcert = 0;
        numMoves = 0;
        textViewNumPairs.setText(String.valueOf(getResources().getString(R.string.pairs) + numPairsAcert + "/" + numPairs));
        textViewNumMoves.setText(String.valueOf(getResources().getString(R.string.moves) + numMoves));
        textViewTemporizador.setText(cuentaAtrasIni);

        getRndList(numPairs);
        return super.onOptionsItemSelected(item);
    }

    // Funcion para obtener una lista de cartas aleatorias
    private void getRndList(int numPairs) {
        listCards = (ArrayList<Card>) Util.getDummyData();
        listCards2 = (ArrayList<Card>) Util.getDummyData2();
        //Desordenar la lista1
        Collections.shuffle(listCards);

        //Sublista para obtener solo un número de parejas
        listCards = (List<Card>) listCards.subList(0, numPairs);
        listCards3 = new ArrayList<>();
        //Rellenar la lisla con las parejas
        for (Card card : listCards) {
            listCards3.add(searchCard(card.getNumPareja()));
        }
        //Juntar las dos listas
        listCards.addAll(listCards3);
        //Desordenar la lista final
        Collections.shuffle(listCards);

        recyclerViewBoard.setHasFixedSize(true);
        recyclerViewBoard.setLayoutManager(new GridLayoutManager(this, numColums));
        adapter = new RecyclerBoardAdapter(listCards, numColums, new RecyclerBoardAdapter.CardClickListener() {
            //Interfaz onclick, Al hacer click en cada carta, Recibe posición
            @Override
            public void onCardClicked(int position) {

                if (selectedCards.size() == 0) {
                    //Temporizador de x segundos pasados por parametro, cada segundo
                    countDownTimer = new CountDownTimer(temporizador, 1000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                            //Formato del tiempo
                            NumberFormat f = new DecimalFormat("00");
                            long min = (millisUntilFinished / 60000) % 60;
                            long sec = (millisUntilFinished / 1000) % 60;

                            textViewTemporizador.setText(f.format(min) + ":" + f.format(sec));
                            cntSegAscen++;
                        }

                        @Override
                        public void onFinish() {
                            //Tiempo terminado
                            Toast.makeText(getApplicationContext(), String.valueOf(getResources().getString(R.string.derrota)), Toast.LENGTH_LONG).show();
                            terminado = true;
                        }
                    }.start();
                }
                //Si se ha terminado el tiempo ya no llama a validar y voltear (el metodo onclick no hace nada)
                if (!terminado) {
                    if (selectedCards.size() == 2) {
                        validarCards(selectedCards);
                    }

                    voltearCards(position);
                }
                //Notificar los cambios
                adapter.notifyDataSetChanged();
            }
        });

        recyclerViewBoard.setAdapter(adapter);

    }

    //Buscar la carta por el número de pareja pasado como parametro. Devuelve una Card
    private Card searchCard(int numPareja) {
        Card cardPareja = null;
        for (Card card : listCards2) {
            if (card.getNumPareja() == numPareja) {
                cardPareja = card;
            }
        }
        return cardPareja;
    }

    //Voltear las cartas
    private void voltearCards(int position) {
        Card card = listCards.get(position);
        card.setVolteada(true);
        //Añadir la carta volteada a una lista para despues validarla
        selectedCards.add(card);

        /*Llamar a funcion para ver si están todas volteadas
         * parar el temporizador, sacar mensaje de victoria
         * validar la última pareja*/
        if (todasVolteadas()) {
            countDownTimer.cancel();
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.victoria), Toast.LENGTH_LONG).show();
            validarCards(selectedCards);
            openDialog();
        }
    }

    //Sacar la alerta dialog para introducir el username
    private void openDialog() {
        UserNameDialog userNameDialog = new UserNameDialog();
        userNameDialog.show(getSupportFragmentManager(), "username dialog");
    }

    //Comprobar si todas las cartas están volteadas. Debuelve boolean
    private boolean todasVolteadas() {
        boolean volteadas = true;
        for (Card card : listCards) {
            if (!card.isVolteada()) {
                volteadas = false;
            }
        }
        return volteadas;
    }

    //Validar cartas. Devuelve una List<Card>
    private void validarCards(List<Card> selectedCards) {
        //¿La carta 1 y 2 son iguales?
        if (selectedCards.get(0).getNumPareja() == selectedCards.get(1).getNumPareja()) {
            //Las cartas son iguales
            //Bucle para poner las cartas iguales Emparejada a true
            for (Card card : listCards) {
                if (card.getNumPareja() == selectedCards.get(0).getNumPareja()) {
                    card.setEmparejada(true);
                }
            }
            //Sumar numero de parejas acertadas
            numPairsAcert++;
        } else {
            //Las cartas no coinciden
            for (Card card : listCards) {
                //Voltear todas las cartas que tengan el numero de pareja igual a la premera carta o a la segunda carta (Las cartas y sus parejas)
                if (card.getNumPareja() == selectedCards.get(0).getNumPareja() || card.getNumPareja() == selectedCards.get(1).getNumPareja()) {
                    card.setVolteada(false);
                }
            }
        }
        //Sumar un movimiento (Independiente de si son iguales o no)
        numMoves++;

        textViewNumPairs.setText(String.valueOf(getResources().getString(R.string.pairs) + numPairsAcert + "/" + numPairs));
        textViewNumMoves.setText(String.valueOf(getResources().getString(R.string.moves) + numMoves));

        //Eliminar las dos cartas de la lista selectedCards
        selectedCards.remove(1);
        selectedCards.remove(0);

    }

    //Menú dificultades
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    //Reiniciar las cartas
    private void reiniciarCards() {
        //Recorrer las cartas y setEmparejada(false)
        for (int i = 0; i < listCards.size(); i++) {
            listCards.get(i).setEmparejada(false);
        }
        //Borrar todos los elementos de la lista selectedCards
        selectedCards.clear();
    }

    @Override
    public void applyText(String username) {
        voleyPost(username);
    }
    //Put con volley a API REST
    private void voleyPost(String username) {
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("name", username);
            object.put("dificulty", dificultad);
            object.put("time", conversorSegundos());
            object.put("moves", numMoves);
            object.put("points", calcularPuntos());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, object, response -> {
            Toast.makeText(getApplicationContext(),String.valueOf(getResources().getString(R.string.puntuacionregistrada)),Toast.LENGTH_LONG).show();
        }, error -> {
            VolleyLog.d("Error", "Error: " + error.getMessage());
            Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_LONG).show();
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);


    }

    //Calcular la puntuación al finalizar partida
    public int calcularPuntos() {
        int puntos = 0;
        if (dificultad.equals("Fácil")) {
            puntos = (50 / cntSegAscen) * numPairs;
        } else if (dificultad.equals("Media")) {
            puntos = (80 / cntSegAscen) * numPairs;
        } else {
            puntos = (100 / cntSegAscen) * numPairs;
        }
        return puntos;
    }

    //Convertir de segundos a minutos:segundos
    private String conversorSegundos() {
        String tiempoTotal = "";
        NumberFormat f = new DecimalFormat("00");
        long min = (cntSegAscen / 60) % 60;
        long sec = cntSegAscen % 60;

        tiempoTotal = f.format(min) + ":" + f.format(sec);
        return tiempoTotal;
    }
}