package com.company;
import java.util.LinkedList;
//TODO INSERT SNAKE CODE HERE FROM MAIN
public class Snake {
    LinkedList<Cell> snakeList = new LinkedList<>();


    public Snake(){
        //Initiliaze first blocks of snake body

        snakeList.add(new Cell(Game.width/2,Game.heigth/2));
        snakeList.add(new Cell(Game.width/2,Game.heigth/2));
        snakeList.add(new Cell(Game.width/2,Game.heigth/2));

    }

    public LinkedList<Cell> getSnakeList(){return snakeList;}
    public void setSnakeList(LinkedList<Cell> snakeList){this.snakeList = snakeList;}
}
