package com.example.myapplication;

import static com.example.myapplication.GameView.height;
import static com.example.myapplication.GameView.width;
import static com.example.myapplication.Tank.tankH;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bullet {
    int bulletX,bulletY,bulletW,bulletH;
    Bitmap bullet;
    Tank tank;


    Bullet(Context context){
        tank = new Tank(context);
        bullet = BitmapFactory.decodeResource(context.getResources(),R.drawable.bulet);
        bulletX = width /2 - bullet.getWidth()/2;
        bulletY = height -tankH;

    }
    Bitmap getBullet(){
        return bullet;
    }
    int get_width(){
        return bullet.getWidth();
    }
    int get_height(){
        return  bullet.getHeight();
    }

}
