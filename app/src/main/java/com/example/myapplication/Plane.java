package com.example.myapplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Plane {
    Bitmap[] plane = new Bitmap[6];
    int planeX,planeY,velocity,planeFrame;
    Random random;
    public Plane(Context context) {
        plane[0]= BitmapFactory.decodeResource(context.getResources(),R.drawable.frame_0);
        plane[1]=BitmapFactory.decodeResource(context.getResources(),R.drawable.frame_1);
        plane[2]=BitmapFactory.decodeResource(context.getResources(),R.drawable.frame_2);
        plane[3]=BitmapFactory.decodeResource(context.getResources(),R.drawable.frame_3);
        plane[4]=BitmapFactory.decodeResource(context.getResources(),R.drawable.frame_4);
        plane[5]=BitmapFactory.decodeResource(context.getResources(),R.drawable.frame_5);
        random = new Random();
        reset_position();
    }
  int  get_planeWidth(){
        return  plane[0].getWidth();
    }
  Bitmap get_planFrame(){
        return  plane[planeFrame];
  }
  void reset_position(){
      planeX = -random.nextInt(1200);
      planeY = random.nextInt(300);
      velocity = 8 + random.nextInt(13);
      planeFrame =0;
  }
}
