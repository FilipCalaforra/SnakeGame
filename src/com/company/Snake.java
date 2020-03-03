package com.company;
import java.util.LinkedList;

public class Snake {
    private LinkedList<BoardElement> snakePartList = new LinkedList<>();
    private BoardElement snakeHead;

    public Snake (BoardElement initPos){
        snakeHead = initPos;
        snakePartList.add(snakeHead);
    }

    public void growing(){ snakePartList.add(snakeHead); }

    public void moving(BoardElement nextCell){
        BoardElement snakeTail = snakePartList.removeLast();
        snakeTail.setCellType(BoardElement.CellType.EMPTY);
        snakeHead = nextCell;
        snakePartList.addFirst(snakeHead);
    }


    public LinkedList<BoardElement> getSnakePartList() { return snakePartList; }

    public void setSnakePartList(LinkedList<BoardElement> snakePartList) { this.snakePartList = snakePartList; }

    public BoardElement getSnakeHead() { return snakeHead; }

    public void setSnakeHead(BoardElement snakeHead) { this.snakeHead = snakeHead; }

}
