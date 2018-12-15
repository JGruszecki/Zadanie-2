package battleships.model;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Board {
    public GridPane Board;
    public  ArrayList<ArrayList<Field>> gameBoard;
    public ArrayList<Ship> ships;
    public boolean isPlayerBoard;
    public Board(GridPane board){
        this.Board = board;
        gameBoard = new ArrayList<ArrayList<Field>>(10);
    }
    
    public abstract void createBoard();
    
    
    /**
     * 
     * @param ship
     * @param plane - rotation of the ship: vertical or horizontal
     * @return
     */
    public boolean addShip(Ship ship, boolean rotation) {
    	if(!this.canAddShip(ship.getX(), ship.getY(), ship.getSize(), rotation)) {
    		return false;
    	}
    	int x = ship.getX();
    	int y = ship.getY();
    	int size = ship.size;
    	if(rotation) {
    		for(int i = 0; i < size; i++) {
    			this.gameBoard.get(x).get(y).setShip(ship);
    			Node node = this.getNode(x, y);
    			Rectangle rect = (Rectangle) node;
    			if(isPlayerBoard)
    				rect.setFill(Color.GREEN);
    			x++;
    		}
    		x--;
    		ship.setEndX(x);
    		ship.setEndY(y);
    	}
    	else{
    		for(int i = 0; i < size; i++) {
    			this.gameBoard.get(x).get(y).setShip(ship);
    			Node node = this.getNode(x, y);
    			Rectangle rect = (Rectangle) node;
    			if(isPlayerBoard)
    				rect.setFill(Color.GREEN);
    			y++;
    		}
    		y--;
    		ship.setEndX(x);
    		ship.setEndY(y);
    	}
    	return true;
    }
    
    /**
     * Method used to check if the x and y parameters are viable for a ship
     * @param x
     * @param y
     * @param size - size of the ship
     * @param rotation - rotation of the ship : horizontal or vertical
     * @return
     */
    public boolean canAddShip(int x, int y, int size, boolean rotation) {
    	if(rotation && (x + size -1 > 9)) {
    		return false;
    	}
    	if(!rotation && (y + size -1 > 9)) {
    		return false;
    	}
    	if (!rotation) {
            for (int i = 0; i < size && i <= 9; i++) {
                if (gameBoard.get(x).get(y + i).getShip() != null)
                    return false;
            }
        }
        if (rotation) {
            for (int i = 0; i < size && i <= 9; i++) {
                if (gameBoard.get(x + i).get(y).getShip() != null)
                    return false;
            }
        }
    	return true;
    }
    
    void clearDisplayBoard() {
        GridPane board = this.Board;
        for (Node node : board.getChildren()) {
        	if(node instanceof Rectangle) {
            Integer x = GridPane.getColumnIndex(node);
            Integer y = GridPane.getRowIndex(node);
            if (x == null)
                GridPane.setColumnIndex(node, 0);
            if (y == null)
                GridPane.setRowIndex(node, 0); 
            Rectangle field = (Rectangle) node;
            field.setFill(Color.web("#b8bfc6"));
            }
        }
    }
    
    public Node getNode(int col, int row) {
        GridPane gridPane = this.Board;
        for (Node node : gridPane.getChildren()) {
            if (node == null) {
                return null;
            }
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
    
    public void checkField(int col, int row) {
        ArrayList<ArrayList<Field>> board = this.gameBoard;
        Ship ship = null;
        ship = board.get(col).get(row).getShip();
        Node node = this.getNode(col, row);
        Rectangle rect = (Rectangle) node;
        if (ship != null) { 
            rect.setFill(Color.AQUA);
            ship.hit();
            if(ship.getHealth() == 0) {
                for (int x = ship.getX(); x <= ship.getEndX(); x++) {
                    for (int y = ship.getY(); y <= ship.getEndY(); y++) {
                        Node node2 = this.getNode(x, y);
                        Rectangle rect2 = (Rectangle) node2;
                        rect2.setFill(Color.RED);
                    }

                }
            }
        }
        else
            rect.setFill(Color.WHITE);
        board.get(col).get(row).setAsClicked(true);
    }
    
    public void setFieldsState(boolean state) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                gameBoard.get(x).get(y).setAsClicked(state);
            }
        }
    }
    
    public void resetHits() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                gameBoard.get(x).get(y).setHit(false);
            }
        }
    }
    
    public void destroyAllShips() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
            		gameBoard.get(x).get(y).setShip(null);
            }
        }
    }

    
}
