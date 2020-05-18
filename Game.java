

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Game extends Application {
    //JavaFX application

    private Map map;
    private Player player;
    private Food food;
    private Circle playersBall;
    //player like a ball in the map. It's object on which we register and handle the key event
	private static String mapTextFile;
	
	//get the map text file as command-line argument
	public static void main(String[] args){
		mapTextFile = args[0];
		Application.launch(args); 
	}
	
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) throws FileNotFoundException {
        map = new Map(mapTextFile);               //Creates the map
        player = new MyPlayer(map);                        // Creates the player
        food = new Food(map, player);                     //Creates food instance
        playersBall = player.getBall();


        //register, handle the key pressed event on ball object
        playersBall.setOnKeyPressed(e ->{
            switch (e.getCode()){
                case DOWN: player.moveDown(); break;         //Controls key events
                case UP: player.moveUp();break;
                case RIGHT: player.moveRight();break;
                case LEFT: player.moveLeft();break;
            }
        });

        Scene scene = new Scene(map);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Packman");
        primaryStage.show();

        playersBall.requestFocus();  //player (ball) is focused to receive key event

    }
}
