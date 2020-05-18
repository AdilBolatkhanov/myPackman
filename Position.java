
public class Position {
    private int x;
    private int y;
    //Stores the position (x and y coordinates)

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    //getter methods
    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }
    //setter methods
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    //check whether two positions are equal
    public boolean equals(Position position){
        if (this.x == position.getX() && this.y == position.getY()){
            return true;
        }else{
            return false;
        }
    }
}
