package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class GameView extends View {
    Bitmap background ;
   static int width,height;
    Handler handler;
    Runnable runnable;
    final long updateMills = 30;
    ArrayList<Plane> planes ;
    ArrayList<Plane2> plane2s;
    Plane2 plane2;

    public GameView(Context context) {
        super(context);
        background= BitmapFactory.decodeResource(getResources(),R.drawable.background);
        Point size = new Point();
        ((Activity )getContext()).getWindowManager().getDefaultDisplay().getSize(size);
        width = size.x;
        height = size.y;
        planes = new ArrayList<>();
        plane2s = new ArrayList<>();

        for (int i=0; i<2; i++){
            Plane2 plane2 = new Plane2(context);
            Plane plane = new Plane(context);
            planes.add(plane);
            plane2s.add(plane2);
        }
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };



    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        @SuppressLint("DrawAllocation")
        Rect rect = new Rect(0,0,width,height);
        canvas.drawBitmap(background,null,rect,null);

      try {for (int i = 0;i<plane2s.size();i++){
          plane2 = plane2s.get(i);
          canvas.drawBitmap(plane2.get_planFrame(),plane2.planeX,plane2.planeY,null);
          plane2.planeFrame++;
          if (plane2.planeFrame>5){
              plane2.planeFrame=0;
          }
          plane2.planeX -=plane2.velocity;
          if (plane2.planeX < -plane2.get_planeWidth()){
              plane2.reset_position();
          }

      }

          for (int i =0; i<planes.size();i++){
              Plane plane = planes.get(i);
              canvas.drawBitmap(plane.get_planFrame(),plane.planeX,plane.planeY,null);
              plane.planeFrame++;
              if (plane.planeFrame>5){
                  plane.planeFrame=0;
              }
              plane.planeX +=plane.velocity;

              if (plane.planeX > width+plane.get_planeWidth()){
                  plane.reset_position();
              }
          }
      }catch (Exception e){
          Toast.makeText(GameView.this.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
      }



        handler.postDelayed(runnable,updateMills);

    }
}
