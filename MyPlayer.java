
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class MyPlayer implements Player{
    //Implements the Player interface

    private Circle ball; //Creates the player as a ball on the board
    private Map map;       //board on which we place the player
    private Position position;        //start position of player
    private int unit;              // size of one cell (in pixels) in the board, need in order to create construct circle(ball) and move ball
    private int size;              // size of map (board). Number of columns/rows
    private int[][] data;             //content of board

    //construct a player with specified map and place it to this map
    public MyPlayer(Map map){
        this.map = map;
        position = map.getStartPosition();
        unit = map.getUnit();
        size = map.getSize();
        ball = new Circle((double)position.getX() * unit + unit / 2.0D, (double)position.getY() * unit + unit / 2.0D, unit / 2.0D);
        ball.setFill(Color.RED);
        this.map.getChildren().add(ball);
        data = map.getMap();
    }

    //implement the moveRight defined in Player interface
    //moves the player to the right
    //considering that player does not go out of bounds or through the walls
    public void moveRight(){
        int oldY = position.getY();
        int newPosition = position.getX() + 1;
        if (newPosition != size) {
            //reverse the x and y coordinates, that in 2-D matrix will be correct
            //in order to check whether there are wall or not
            if (data[oldY][newPosition] != 1) {
                position.setX(newPosition);
                ball.setCenterX(ball.getCenterX() + unit);

            }
        }
    }

    //implement the moveLeft defined in Player interface
    //moves the player to the left
    //considering that player does not go out of bounds or through the walls
    public void moveLeft(){
        int newPosition = position.getX() -1;
        int oldY = position.getY();
        if (newPosition != -1) {
            //reverse the x and y coordinates, that in 2-D matrix will be correct
            //in order to check whether there are wall or not
            if (data[oldY][newPosition] != 1) {
                position.setX(newPosition);
                ball.setCenterX(ball.getCenterX() - unit);

            }
        }
    }

    //implement the moveUp defined in Player interface
    //moves the player to the up
    //considering that player does not go out of bounds or through the walls
    public void moveUp(){
        int newPosition = position.getY() - 1;
        int oldX = position.getX();
        if (newPosition != -1) {
            //reverse the x and y coordinates, that in 2-D matrix will be correct
            //in order to check whether there are wall or not
            if (data[newPosition][oldX] != 1) {
                position.setY(newPosition);
                ball.setCenterY(ball.getCenterY() - unit);

            }
        }
    }

    //implement the moveDown defined in Player interface
    //moves the player to the down
    //considering that player does not go out of bounds or through the walls
    public void moveDown(){
        int newPosition = position.getY() + 1;
        int oldX = position.getX();
        if (newPosition != size) {
            //reverse the x and y coordinates, that in 2-D matrix will be correct
            //in order to check whether there are wall or not
            if (data[newPosition][oldX] != 1) {
                position.setY(newPosition);
                ball.setCenterY(ball.getCenterY() + unit);

            }
        }
    }
    //implementing the method getPosition in the Player interface
    public Position getPosition(){
        return position;
    }

    //implement the getBall method defined in Player interface
    public Circle getBall(){
        return ball;
    }

}
