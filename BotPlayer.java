

// The interface for the MyBotPlayer class to implement
public interface BotPlayer extends Player{
	
	// Passes the initialized food to the bot player
	public abstract void feed(Food f);
	
	// In a map without any walls
	// eats all food elements
	// by choosing the shortest path
	public abstract void eat();
	
	// In a customized map
	// finds a valid path to food and eats it 
	public abstract void find();
}