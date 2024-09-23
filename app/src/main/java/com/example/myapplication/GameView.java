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
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Random;

public class GameView extends View {
    Bitmap background ;
    int width,height,planX,planY,velocity,planFrame,planWidth;
    Bitmap[] plan = new Bitmap[6];
    Random random;
    Handler handler;
    Runnable runnable;
    final long updateMills = 30;

    public GameView(Context context) {
        super(context);
        background= BitmapFactory.decodeResource(getResources(),R.drawable.background);
        Point size = new Point();
        ((Activity )getContext()).getWindowManager().getDefaultDisplay().getSize(size);

        width = size.x;
        height = size.y;
        plan[0]=BitmapFactory.decodeResource(getResources(),R.drawable.frame_0);
        plan[1]=BitmapFactory.decodeResource(getResources(),R.drawable.frame_1);
        plan[2]=BitmapFactory.decodeResource(getResources(),R.drawable.frame_2);
        plan[3]=BitmapFactory.decodeResource(getResources(),R.drawable.frame_3);
        plan[4]=BitmapFactory.decodeResource(getResources(),R.drawable.frame_4);
        plan[5]=BitmapFactory.decodeResource(getResources(),R.drawable.frame_5);
        planX = -300;
        planY = 100;
        velocity = 15;
        planFrame =0;
        planWidth = plan[0].getWidth();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        random = new Random();


    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        @SuppressLint("DrawAllocation")
        Rect rect = new Rect(0,0,width,height);
        canvas.drawBitmap(background,null,rect,null);
        canvas.drawBitmap(plan[planFrame],planX,planY,null);
        planFrame++;
        if (planFrame>5){
            planFrame=0;
        }
        planX +=velocity;
        try {
            if (planX > width+planWidth){
                planX = -random.nextInt(500);
                planY = random.nextInt(200);
                velocity = 10 + random.nextInt(10);
            }

        }catch (Exception e){
            System.out.println("${e}");
            Log.d("hhh", String.valueOf(e));
        }
        handler.postDelayed(runnable,updateMills);

    }
}
