package com.company;

import javafx.scene.shape.Circle;
import org.w3c.dom.ls.LSOutput;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Food  {
    int max = 14;
    int min = 0;
    int timeInS = 0;
    int foodX = 0;
    int foodY = 0;

    public int getFoodX() { return foodX; }

    public void setFoodX(int foodX) { this.foodX = foodX; }

    public int getFoodY() { return foodY; }

    public void setFoodY(int foodY) { this.foodY = foodY; }
    Timer appletime = new Timer();
    TimerTask appleisthere = new TimerTask() {
        @Override
        public void run() {
            timeInS++;

        }

    };
    Apple apple = new Apple();
    public void food()
    {
        appleisthere.run();
        if(timeInS==10) {
            Random r = new Random();
            int posY = r.nextInt(((max-min)+1)+min);
            int posX = r.nextInt(((max-min)+1)+min);

            timeInS=0;
            apple.Apple(posX*40,posY*40);
        }
    }


}

