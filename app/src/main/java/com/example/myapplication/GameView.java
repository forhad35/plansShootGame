package com.example.myapplication;

import static com.example.myapplication.Tank.tankH;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class GameView extends View {
  static  Bitmap background;
  Tank tank;
  boolean isShoot;
   static int width,height;
    Handler handler;
    Runnable runnable;
    Bullet bullet;
    final long updateMills = 30;
    ArrayList<Plane> planes ;
    ArrayList<Plane2> plane2s;
    List<Bullet> bullets,trash;
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
        bullet = new Bullet(context);
        tank= new Tank(context);
        bullets = new ArrayList<>();
        trash = new ArrayList<>();

        for (int i=0; i<2; i++){

            Plane plane = new Plane(context);
            planes.add(plane);
            Plane2 plane2 = new Plane2(context);
            plane2s.add(plane2);
        }
        handler = new Handler();
        runnable = this::invalidate;



    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        @SuppressLint("DrawAllocation")
        Rect rect = new Rect(0,0,width,height);
        canvas.drawBitmap(background,null,rect,null);

        canvas.drawBitmap(tank.getTank(), Tank.tankX, Tank.tankY,null);


            for (Bullet bullet1 : bullets){
                canvas.drawBitmap(bullet1.getBullet(),bullet1.bulletX,bullet1.bulletY,null);

                if (bullet1.bulletY < 0){
                    trash.add(bullet1);
                }
            }


for (Bullet bullet1 :trash){
    bullets.remove(bullet1);
}
      try {

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

      }catch (Exception e){
          Toast.makeText(GameView.this.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
      }



        handler.postDelayed(runnable,updateMills);

    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN  &&(width/2)+Tank.tankW /2> x  && (width/2)-Tank.tankW/2 < x&&height-tankH <y ) {
            isShoot = true;
            bullets.add(bullet);
            bullet.bulletY -= 50;


        }

        return true;
    }


}
