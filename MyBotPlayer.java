
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyBotPlayer implements BotPlayer{
    private Circle ball; //Creates the player as a ball on the board
    private Map map;       //board on which we place the player
    private Position position;        //start position of player
    private int unit;              // size of one cell (in pixels) in the board, need in order to create construct circle(ball) and move ball
    private static int size;              // size of map (board). Number of columns/rows
    private int[][] data;  //content of board
    private Position foodPosition;                 //stores position of food in order to know destination
    private Food food;


    //construct a bot from map
    public MyBotPlayer(Map map){
        this.map = map;
        position = map.getStartPosition();
        unit = map.getUnit();
        size = map.getSize();
        ball = new Circle((double)position.getX() * unit + unit / 2.0D, (double)position.getY() * unit + unit / 2.0D, unit / 2.0D);
        ball.setFill(Color.RED);
        this.map.getChildren().add(ball);
        data = map.getMap();
    }

    //implement the feed class defined in BotPlayer interface
    // Passes the initialized food to the bot player
    public void feed(Food food){
        this.food = food;
        foodPosition = position;
    }

    //in the food class position of food changes from time to time, so in this way we get the new position of food
    public void setFoodPosition(Position pos){
        this.foodPosition = pos;
    }


    //implement the eat method defined in BotPlayer interface
    // In a map without any walls
    // eats all food elements
    // by choosing the shortest path
    public void eat(){
        new Thread(() ->{
            while (true) {
				if (this.food.getPosition() != null){
					this.foodPosition = this.food.getPosition();
				}
                ArrayList<Position> line = this.findLine(position, foodPosition);
                for (Position pos : line) {
                    try {
                        if (!pos.equals(position)) {
                            if (pos.getX() > position.getX()) {
                                Platform.runLater(() -> {
                                    this.moveRight();
                                });
                                Thread.sleep(400);
                            }
                            if (pos.getX() < position.getX()) {
                                Platform.runLater(() -> this.moveLeft());

                                Thread.sleep(400);
                            }
                            if (pos.getY() < position.getY()) {
                                Platform.runLater(() -> this.moveUp());
                                Thread.sleep(400);
                            }
                            if (pos.getY() > position.getY()) {
                                Platform.runLater(() -> this.moveDown());
                                Thread.sleep(400);
                            }
                            position = pos;
                        }
                    } catch (InterruptedException e) {
                    }
                }

            }
        }).start();

    }



    //implement the find method defined in BotPlayer interface
    // In a customized map
    // finds a valid path to food and eats it
    public void find(){
    }





    //bresenham's line algorithm
    //Construct a line from bot position to food position
    //return the coordinates of line
    public static ArrayList<Position> findLine(Position botPosition, Position foodPosit){
        ArrayList<Position> line = new ArrayList<>();
        int x0 = botPosition.getX();
        int y0 = botPosition.getY();
        int x1 = foodPosit.getX();
        int y1 = foodPosit.getY();

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = x0 < x1 ? 1: -1;
        int sy = y0 < y1 ? 1: -1;

        int err = dx - dy;
        int e2;

        int currentX = x0;
        int currentY = y0;

        while (true){
            line.add(new Position(currentX, currentY));

            if (currentX == x1 && currentY == y1){
                break;
            }

            e2 = 2 * err;
            if (e2 > -1 * dy){
                err = err - dy;
                currentX = currentX +sx;
            }

            if (e2 < dx){
                err = err + dx;
                currentY = currentY + sy;
            }
        }
        return line;
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
