

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;

import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Game1 extends Application {
    private Map map;
    private MyBotPlayer bot;
    private Food food;
    private Circle playersBall;
	private static String mapTextFile;
	
	//get the map text file as command-line argument
	public static void main(String[] args){
		mapTextFile = args[0];
		Application.launch(args); 
	}

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) throws FileNotFoundException {
        map = new Map(mapTextFile);               //Creates the map
        bot = new MyBotPlayer(map);
        food = new Food(map, bot);
        bot.feed(food);
        bot.eat();

        Scene scene = new Scene(map);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Packman");
        primaryStage.show();
    }
}
