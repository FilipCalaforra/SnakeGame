package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.stage.Stage;



public class Game extends Application {
    public static int width = 20;
    public static int heigth = 20;
    public static int cell_size = 25;
    public static int speed = 10;
    int foodX = 0;
    int foodY = 0;
    static boolean gameOver = false;


    Dir direction = Dir.LEFT;

    //Initialize Objects
    Snake snake = new Snake();
    VBox root = new VBox();


    @Override
    public void start(Stage primaryStage) throws Exception {
        try{

            Scene scene = new Scene(root,width*cell_size,heigth*cell_size);
            Canvas canvas = new Canvas(width*cell_size,heigth*cell_size);

            GraphicsContext gc = canvas.getGraphicsContext2D();

            new AnimationTimer(){
                long lastTick = 0;
                public void handle(long now){
                    if(lastTick == 0){
                        lastTick = now;
                        tick(gc);
                        return;
                    }
                    if(now - lastTick > 1000000000 / speed){
                        lastTick = now;
                        tick(gc);
                    }
                }
            }.start();


            //EVENT FOR CONTROLLING THE SNAKE WITH W-A-S-D KEYS INPUT
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key ->{
                if (key.getCode() == KeyCode.W) {
                    direction = Dir.UP;
                }
                if (key.getCode() == KeyCode.A) {
                    direction = Dir.LEFT;
                }
                if (key.getCode() == KeyCode.S) {
                    direction = Dir.DOWN;
                }
                if (key.getCode() == KeyCode.D) {
                    direction = Dir.RIGHT;
                }
            });

            root.getChildren().add(canvas);
            primaryStage.setTitle("SNAKE GAME");
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void tick(GraphicsContext gc){

        if(gameOver){
            gc.setFill(Color.RED);
            gc.setFont(new Font("",50));
            gc.fillText("GAME OVER",100,250);
            return;
        }
        for(int i = snake.getSnakeList().size() - 1; i>=1; i--){
            snake.getSnakeList().get(i).x = snake.getSnakeList().get(i-1).x;
            snake.getSnakeList().get(i).y = snake.getSnakeList().get(i-1).y;
        }
        switch(direction){
            case UP:
                snake.getSnakeList().get(0).y--;
                if(snake.getSnakeList().get(0).y<0){
                    gameOver=true;
                }break;
            case DOWN:
                snake.getSnakeList().get(0).y++;
                if(snake.getSnakeList().get(0).y>heigth){
                    gameOver=true;
                }break;
            case LEFT:
                snake.getSnakeList().get(0).x--;
                if(snake.getSnakeList().get(0).x<0){
                    gameOver=true;
                }break;
            case RIGHT:
                snake.getSnakeList().get(0).x++;
                if(snake.getSnakeList().get(0).x>width){
                    gameOver=true;
                }break;
        }
        /*
        if(foodX == snake.get(0).x && foodY == snake.get(0).y){
            snake.add(new Cell(-1,-1));



        }

         */
        //self destroy
        for(int i=1; i<snake.getSnakeList().size();i++){
            if(snake.getSnakeList().get(0).x == snake.getSnakeList().get(i).x && snake.getSnakeList().get(0).y==snake.getSnakeList().get(i).y){
                gameOver = true;

            }
        }
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,width*cell_size,heigth*cell_size);
        //snake
        for(Cell canvas : snake.getSnakeList()){
            gc.setFill(Color.DARKSEAGREEN);
            gc.fillRect(canvas.x*cell_size,canvas.y*cell_size,cell_size-1,cell_size-1);
            gc.setFill(Color.GREEN);
            gc.fillRect(canvas.x*cell_size,canvas.y*cell_size,cell_size-2,cell_size-2);
        }
    }
}

