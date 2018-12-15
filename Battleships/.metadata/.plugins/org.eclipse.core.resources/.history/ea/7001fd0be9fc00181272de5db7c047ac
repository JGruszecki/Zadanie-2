package battleships.model;

public class Field {
    private boolean alreadyClicked;
    private boolean hit;
    private Ship ship;
    
    public Field(){
        this.alreadyClicked = false;
        this.hit = false;
        this.ship = null;
    }

    public Field(Ship ship){
    	this.alreadyClicked = false;
        this.ship = ship;
    }

    public boolean checkIfClicked() {
    	return alreadyClicked;
    }

    public void setAsClicked(boolean clicked) {
    	this.alreadyClicked = clicked;
    }

    public Ship getShip() {
    	return ship;
    }
    
    public void setShip(Ship ship) {
        this.ship = ship;
    }
    
    public void setHit(boolean hit) {
    	this.hit = hit;
    }
    
    public boolean getHit() {
    	return hit;
    }

}