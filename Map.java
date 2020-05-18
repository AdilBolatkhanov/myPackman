

import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map extends Pane {
    //Extends the Pane class

    private int unit = 30;         //size of one cell (in pixels)
    private int size;             //size of map (number of columns/rows)
    private int[][] map;            // Keeps the data in a two-dimensional array
    private Position start;          //starting point for the player (ball)

    //Constructs a map from a given text file;
    public Map(String textFile) throws FileNotFoundException {
        File source = new File(textFile);
        if (!source.exists()){
            System.out.println("Source file "+ textFile + " does not exits");
            System.exit(1);
        }
        try (
                Scanner input = new Scanner(source);
                ){
            size = input.nextInt();
            map = new int[size][size];
            input.nextLine();
            int currentRow = 0;
            while (input.hasNext()){
                String row = input.nextLine();
                String[] content = row.split(" ");
                for (int column =0; column < content.length; column++){
                    int number = Integer.parseInt(content[column]);
                    if (number == 2){                                      //2 - for the starting point of the player
                        start = new Position(column, currentRow);
                    }
                    map[currentRow][column] = number;
                }
                currentRow++;
            }
        }
        this.constructMap();
    }

    //Fills the map
    //Draws the border lines
    //Draws the walls
    // 0 - for empty cell, 1 - for wall, and 2 - for the starting point of the player
    public  void constructMap(){
        int x =0;
        int y= 0;
        for (int row = 0; row < map.length ; row++ ){
            for (int column = 0; column < map[0].length; column++){
                Rectangle rectangle = new Rectangle(x ,y , unit, unit);
                if (map[row][column] == 1){
                    rectangle.setFill(Color.BLACK);
                }else{
                    rectangle.setFill(Color.WHITE);
                    rectangle.setStroke(Color.BLACK);
                }
                this.getChildren().add(rectangle);
                x += unit;
            }
            x = 0;
            y += unit;
        }
    }

    //getter methods
    public int getUnit(){
        return unit;
    }

    public int getSize(){
        return size;
    }

    public int[][] getMap(){
        return map;
    }

    public Position getStartPosition(){
        return start;
    }

}
