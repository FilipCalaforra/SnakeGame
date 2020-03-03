package com.company;

/**
 * Class that contains the information about the game board
 * and the snake
 */
public class BoardElement {

   private int row, col;
   private CellType cellType;

    /**
     * Group of (FINAL) Constants for BoardElements(cells)
     */
   public enum CellType{
       EMPTY,
       FOOD,
       SNAKE_NODE;

   }
   private BoardElement(int row, int col){
       this.row = row;
       this.col = col;
   }
   public CellType getCellType(){return cellType;}
   public void setCellType(CellType cellType){this.cellType = cellType;}
   public int getRow(){return row;}
   public int getCol(){return col;}
}
