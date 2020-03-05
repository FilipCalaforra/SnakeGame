package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.Random;
import java.util.TimerTask;

/**
 * @author Casper Sejrup, Filip Calaforra, Kuba Nowak
 *
 * This is a program for a snake game, very simple made indeed.
 */
public class Game extends Application {
    public static int width = 20;
    public static int heigth = 20;
    public static int cell_size = 25;
    public static int speed = 5;
    static boolean gameOver = false;
    static int foodcolor = 0;

    Dir direction = Dir.LEFT;

    //Initialize Objects
    VBox root = new VBox();
    Food food = new Food();
    Random rand = new Random();
    Snake snake = new Snake();
    Timer timer = new Timer();
    Scene scene = new Scene(root,width*cell_size,heigth*cell_size);
    Canvas canvas = new Canvas(width*cell_size,heigth*cell_size);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            generateFood();
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
            initBoard(primaryStage);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

  
    private void initBoard(Stage primaryStage){
        root.getChildren().add(canvas);
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void generateFood(){

        start: while(true){
            food.setFoodX(rand.nextInt(width));
            food.setFoodY(rand.nextInt(heigth));
            for(Cell canvas : snake.getSnakeList()){
                if(canvas.x == food.getFoodX() && canvas.y==food.getFoodY()){
                    continue start;
                }
            }
            foodcolor = rand.nextInt(5);
            speed++;
            System.out.println(speed);
            break;
        }
    }
    public void tick(GraphicsContext gc){
        if(gameOver){
            gc.setFill(Color.MEDIUMBLUE);
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
        //SNAKE EATING FOOD THEN RECALLING GENEREATEFOOD()
        if(food.getFoodX() == snake.getSnakeList().get(0).x && food.getFoodY() == snake.getSnakeList().get(0).y ){
            snake.getSnakeList().add(new Cell(-1,-1));
            generateFood();
        }

        //self destroy
        for(int i=1; i<snake.getSnakeList().size();i++){
            if(snake.getSnakeList().get(0).x == snake.getSnakeList().get(i).x && snake.getSnakeList().get(0).y==snake.getSnakeList().get(i).y){
                gameOver = true;
            }
        }
        //BlackGround
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,width*cell_size,heigth*cell_size);

        //score
        gc.setFill(Color.CYAN);
        gc.setFont(new Font("arial",20));
        gc.fillText("Score: " +(speed-6),10,30);
       //Length of Snake

        gc.setFill(Color.CYAN);
        gc.setFont(new Font("arial",10));
        gc.fillText("Length: " + snake.getSnakeList().size(),50, 40);


        //random foodcolor
        Color fc = Color.WHITE;

        switch(foodcolor){
            case 0: fc = Color.PURPLE;
                break;
            case 1: fc = Color.LIGHTBLUE;
                break;
            case 2: fc = Color.YELLOWGREEN;
                break;
            case 3: fc = Color.GREEN;
                break;
            case 4: fc = Color.ORANGE;
                break;
            case 5: fc = Color.DARKCYAN;
                break;
        }
        gc.setFill(fc);
        gc.fillOval(food.getFoodX()*cell_size,food.getFoodY()*cell_size,cell_size,cell_size);

        //snake
        for(Cell canvas : snake.getSnakeList()){
            gc.setFill(Color.DARKSEAGREEN);
            gc.fillRect(canvas.x*cell_size,canvas.y*cell_size,cell_size-1,cell_size-1);
            gc.setFill(Color.GREEN);
            gc.fillRect(canvas.x*cell_size,canvas.y*cell_size,cell_size-2,cell_size-2);
        }
    }
}

