package com.example.myapplication;

import static com.example.myapplication.GameView.height;
import static com.example.myapplication.GameView.width;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

public class Tank {
    static int tankX,tankY,tankH,tankW;
    Bitmap tank;
    public Tank(Context context) {
        tank = BitmapFactory.decodeResource(context.getResources(),R.drawable.cannon);
        tankX= ( width /2) -  (tank.getWidth() /2);
        tankY = height-tank.getHeight();
        tankH=tank.getHeight();
        tankW=tank.getWidth();



    }
    Bitmap getTank(){
        return  tank;
    }

}
