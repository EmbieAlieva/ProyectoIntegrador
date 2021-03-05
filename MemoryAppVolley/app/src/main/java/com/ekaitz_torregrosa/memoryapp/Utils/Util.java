package com.ekaitz_torregrosa.memoryapp.Utils;

import com.ekaitz_torregrosa.memoryapp.R;
import com.ekaitz_torregrosa.memoryapp.models.Card;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<Card> getDummyData() {
        return new ArrayList<Card>() {{
            add(new Card(R.drawable.anchor, R.drawable.anchor_emparejada,1,1));
            add(new Card(R.drawable.avion, R.drawable.avion_emparejada,2,2));
            add(new Card(R.drawable.bicycle, R.drawable.bicycle_emparejada,3,3));
            add(new Card(R.drawable.cloud, R.drawable.cloud_emparejada, 4, 4));
            add(new Card(R.drawable.face, R.drawable.face_emparejada,5,5));
            add(new Card(R.drawable.flower,R.drawable.flower_emparejada, 6, 6));
            add(new Card(R.drawable.maleta,R.drawable.maleta_emparejada, 7, 7));
            add(new Card(R.drawable.school,R.drawable.school_emparejada, 8, 8));
            add(new Card(R.drawable.shop, R.drawable.shop_emparejada, 9, 9));
            add(new Card(R.drawable.sun , R.drawable.sun_emparejada,10,10));
            add(new Card(R.drawable.tea, R.drawable.tea_emparejada,11, 11));
            add(new Card(R.drawable.thunder, R.drawable.thunder_emparejada,12,12));
        }};
    }
    public static List<Card> getDummyData2() {
        return new ArrayList<Card>() {{
            add(new Card(R.drawable.anchor, R.drawable.anchor_emparejada,13,1));
            add(new Card(R.drawable.avion, R.drawable.avion_emparejada,14,2));
            add(new Card(R.drawable.bicycle, R.drawable.bicycle_emparejada,15,3));
            add(new Card(R.drawable.cloud, R.drawable.cloud_emparejada, 16, 4));
            add(new Card(R.drawable.face, R.drawable.face_emparejada,17,5));
            add(new Card(R.drawable.flower,R.drawable.flower_emparejada, 18, 6));
            add(new Card(R.drawable.maleta,R.drawable.maleta_emparejada, 19, 7));
            add(new Card(R.drawable.school,R.drawable.school_emparejada, 20, 8));
            add(new Card(R.drawable.shop, R.drawable.shop_emparejada, 21, 9));
            add(new Card(R.drawable.sun , R.drawable.sun_emparejada,22,10));
            add(new Card(R.drawable.tea, R.drawable.tea_emparejada,23, 11));
            add(new Card(R.drawable.thunder, R.drawable.thunder_emparejada,24,12));
        }};
    }
}
