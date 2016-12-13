package com.example.berkgur.catchtheball;

import android.content.Intent;
import android.graphics.Point;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.MotionEvent;//this has to be imported to motion
import java.util.Timer;
import java.util.TimerTask;


public class main extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView box;
    private ImageView orange;
    private ImageView pink;
    private ImageView black;
    private ImageView southpipe;
    private ImageView northpipe;
    private ImageView triangle;

    //size
    private int frameHeight;
    private int boxSize;

    private int screenWidth;
    private int screenHeight;

    //position
    //private int boxY;
    private int orangeX;
    private int orangeY;

    private int southpipeX;
    private int southpipeY;

    private int northpipeX;
    private int northpipeY;

    private int triangleX;
    private int triangleY;

    private int pinkX;
    private int pinkY;
    private int blackX;
    private int blackY;

    private int score=0;


    //private int lowerBoundry=frameHeight-boxSize;


    //POSITION
    private int boxY;


    //INITIALIZECLASS

    private Handler handler = new Handler();
    private Timer timer=new Timer();

    //Status check
    private boolean action_flg=false;
    //we dont want timer the start onCreate() so we need it to be set to start at frrst clck
    private boolean start_flg=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreLabel =(TextView)findViewById(R.id.scoreLabel);
        startLabel =(TextView)findViewById(R.id.startLabel);
        box=(ImageView)findViewById(R.id.box);
        orange=(ImageView)findViewById(R.id.orange);
        pink=(ImageView)findViewById(R.id.pink);
        black=(ImageView)findViewById(R.id.black);
        southpipe=(ImageView)findViewById(R.id.southpipe);
        northpipe=(ImageView)findViewById(R.id.northpipe);

        triangle=(ImageView)findViewById(R.id.triangle);



        //get screen size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size=new Point();
        disp.getSize(size);



        screenWidth = size.x;
        screenHeight=size.y;

        box.setX(100);
        //Move to out of the screen
        orange.setX(-80);
        orange.setY(-80);

        triangle.setX(-80);
        triangle.setY(-80);

        southpipe.setX(-80);
        southpipe.setX(-80);

        pink.setX(-80);
        pink.setY(-80);
        black.setX(-80);
        black.setY(-80);

        triangle.setRotation(triangle.getRotation()+1);

        scoreLabel.setText("Score : 0");



        //TEMPORARY
        //startLabel.setVisibility(View.INVISIBLE);
        ///boxY=500;
///////////////////////////////////
        /*timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });

            }
        },0,20);    this creates the timer onCreate which makes the bird fall right away, we want the bird to fall after the first click*/





    }


    public void changePos(){

        hitCheck();



        //DELETE OUT THE ORANGE FOR NOW TO TEST PIPE
       //orange
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        orangeX -=12;
        if(orangeX<0) {
            orangeX = screenWidth + 20; //ornage
            orangeY = (int) Math.floor(Math.random() * (frameHeight - orange.getHeight()));
        }//random y value

        orange.setX(orangeX);
        orange.setY(orangeY);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        blackX -=16;
        if(blackX<0) {
            blackX = screenWidth + 10; //ornage
            blackY = (int) Math.floor(Math.random() * (frameHeight - black.getHeight()));
        }//random y value

        black.setX(blackX);
        black.setY(blackY);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        pinkX -=36;
        if(pinkX<0) {
            pinkX = screenWidth + 1000; //ornage
            pinkY = (int) Math.floor(Math.random() * (frameHeight - pink.getHeight()));
        }//random y value

        pink.setX(pinkX);
        pink.setY(pinkY);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        triangleX -=6;
        if(triangleX<0) {
            triangleX = screenWidth + 25; //ornage
            triangleY = (int) Math.floor(Math.random() * (frameHeight - triangle.getHeight()));
        }//random y value

        triangle.setX(triangleX);
        triangle.setY(triangleY);





//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        southpipeX-=12;
        if(southpipeX<0){
            southpipeX=screenWidth +20; //ornage
            //southpipeY=(int)Math.floor(Math.random()*(frameHeight-southpipe.getHeight()));//random y value

        }
        southpipe.setX(southpipeX);
        southpipe.setY(southpipeY);


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        northpipeX-=12;
        if(northpipeX<0){
            northpipeX=screenWidth +20; //ornage
            //northpipeY=(int)Math.floor(Math.random()*(frameHeight-southpipe.getHeight()));//random y value
            northpipeY=1200;
        }
        northpipe.setX(northpipeX);
        northpipe.setY(northpipeY);


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        //Move Box




        if (action_flg==true)
        {
            //Touching
            boxY-=20;
        }
        else{
            //Release
            boxY+=20;
        }
        //check box position
        if(boxY<0)
        {
            boxY=0;
        }

        if(boxY>frameHeight-boxSize+100)
        {
            //boxY=frameHeight-boxSize;
            timer.cancel();
            timer=null;
            Intent intent= new Intent(getApplicationContext(),results.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }
        box.setY(boxY);

        scoreLabel.setText("Score : "+score);



    }
    public void hitCheck(){
        int orangeCenterX=orangeX+orange.getWidth()/2;
        int orangeCenterY=orangeY+orange.getHeight()/2;

        if(0<=orangeCenterX && orangeCenterX <= boxSize && boxY <= orangeCenterY && orangeCenterY <=boxY+boxSize)
        {
            orangeX= -10;
            score+=10;
        }

        int pinkCenterX=pinkX+pink.getWidth()/2;
        int pinkCenterY=pinkY+pink.getHeight()/2;

        if(0<=pinkCenterX && pinkCenterX <= boxSize && boxY <= pinkCenterY && pinkCenterY <=boxY+boxSize)
        {
            pinkX= -10;
            score+=20;
        }

        int blackCenterX=blackX+black.getWidth()/2;
        int blackCenterY=blackY+black.getHeight()/2;

        if(0<=blackCenterX && blackCenterX <= boxSize && boxY <= blackCenterY && blackCenterY <=boxY+boxSize)
        {
           timer.cancel();
            timer=null;

            Intent intent= new Intent(getApplicationContext(),results.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }


        int southpipeCenterX=southpipeX+southpipe.getWidth()/2;
        int southpipeCenterY=southpipeY+southpipe.getHeight()/2;

        if(0<=southpipeCenterX && southpipeCenterX <= boxSize && boxY <= southpipeCenterY && southpipeCenterY <=boxY+boxSize)
        {

            timer.cancel();
            timer=null;

            Intent intent= new Intent(getApplicationContext(),results.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);

        }

        int northpipeCenterX=northpipeX+northpipe.getWidth()/2;
        int northpipeCenterY=northpipeY+northpipe.getHeight()/2;

        if(0<=northpipeCenterX && northpipeCenterX <= boxSize && boxY <= northpipeCenterY && northpipeCenterY <=boxY+boxSize)
        {

            timer.cancel();
            timer=null;

            Intent intent= new Intent(getApplicationContext(),results.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);

        }

    }
    public boolean onTouchEvent(MotionEvent me)
    {

        if(start_flg==false)
        {
            start_flg=true;



            FrameLayout frame =(FrameLayout) findViewById(R.id.frame);
            frameHeight=frame.getHeight();

            boxY=(int)box.getY();
            boxSize=box.getHeight();




            startLabel.setVisibility(View.GONE);


            timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                        triangle.setRotation(triangle.getRotation()+10);
                        black.setRotation(black.getRotation()+10);
                    }
                });

            }
        },0,20);

        } else
        {
            if(me.getAction()==MotionEvent.ACTION_DOWN)
            {
                //boxY-=20;//touch the screen and the box moves upwards
                action_flg=true;
            }else if(me.getAction()==MotionEvent.ACTION_UP)
            {
                action_flg=false;

            }

        }


        return true;
    }
}
