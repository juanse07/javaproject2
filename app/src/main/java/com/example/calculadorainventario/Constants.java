package com.example.calculadorainventario;

import android.content.Context;

public class Constants {


        private static Spreferences sPreferences;

        public static final Spreferences getSP(Context context){
            if(sPreferences == null){
                sPreferences = new Spreferences(context);
            }
            return sPreferences;
        }

    }
