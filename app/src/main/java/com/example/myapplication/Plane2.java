package com.example.myapplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Plane2  extends Plane{
    Bitmap[] plane2 = new Bitmap[6];
    public Plane2(Context context) {
        super(context);
        plane2[0]=BitmapFactory.decodeResource(context.getResources(),R.drawable.frame1);
        plane2[1]=BitmapFactory.decodeResource(context.getResources(),R.drawable.frame2);
        plane2[2]=BitmapFactory.decodeResource(context.getResources(),R.drawable.frame3);
        plane2[3]=BitmapFactory.decodeResource(context.getResources(),R.drawable.frame5);
        plane2[4]=BitmapFactory.decodeResource(context.getResources(),R.drawable.frame6);
        plane2[5]=BitmapFactory.decodeResource(context.getResources(),R.drawable.frame7);

        random = new Random();
        reset_position();
    }
    int  get_planeWidth(){
        return plane2[0].getWidth();
    }
    Bitmap get_planFrame(){
        return  plane2[planeFrame];
    }
    void reset_position(){
        planeX = GameView.width +random.nextInt(1000);
        planeY = random.nextInt(200);
        velocity = 5 + random.nextInt(21);
        planeFrame =0;
    }
}
