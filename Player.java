

import javafx.scene.shape.Circle;

public interface Player {
    // Interface for the Player, with basic commands

    // methods for controlling player in the map
    public abstract void moveRight();
    public abstract void moveLeft();
    public abstract void moveUp();
    public abstract void moveDown();

    //method for getting position of player
    public abstract Position getPosition();

    //method for getting player as a ball, in order to access ball outside of class which implement this interface
    public abstract Circle getBall();
}
