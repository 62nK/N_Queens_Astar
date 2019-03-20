public class Square {

    // Parameters
    private char coordX;
    private int coordY;

    // Constructors
    public Square(char coordX, int coordY){
        this.coordX = coordX;
        this.coordY = coordY;
    }
    public void randLocation(){
        coordX = (char)(Math.random()* Constants.WIDTH+'A');
        coordY = (int)(Math.random()* Constants.HEIGHT);
    }

    // Getters
    public char getCoordX(){
        return coordX;
    }
    public int getCoordY() {
        return coordY;
    }
    public int getStandardCoordX(){ return coordX-'A';}
    public int getStandardCoordY(){ return coordY-1;}
    public int getIndex(){
        return getStandardCoordY()*Constants.BOARD_SIZE_X+getStandardCoordX();
    }

    // Condition Check
    public boolean isValid(){
        if(coordX<'A' || coordX>= 'A'+Constants.BOARD_SIZE_X)
            return false;
        if(coordY<1 || coordY> Constants.BOARD_SIZE_Y)
            return false;
        return true;
    }

    public boolean equals(Square square2){
    if(coordY==square2.getCoordY() && coordX==square2.getCoordX())
        return true;
    else
        return false;
    }
    public boolean isOnSameVertical(Square square){
        if(coordX==square.getCoordX())
            return true;
        else
            return false;
    }
    public boolean isOnSameHorizontal(Square square){
        if(coordY==square.getCoordY())
            return true;
        else
            return false;
    }
    public boolean isOnSameForwardDiagonal(Square square){
        if((coordX-square.coordX)==-(coordY-square.coordY))
            return true;
        else
            return false;
    }
    public boolean isOnSameBackDiagonal(Square square){
        if(coordX-square.coordX==(coordY-square.coordY))
            return true;
        else
            return false;
    }

    // To String
    public String toString(){
        return "("+coordX+", "+coordY+")";
    }
    public String toString(String title){
        return title+"="+toString();
    }

    // Service Methods
    public void copySquare(Square source){
        coordX = source.getCoordX();
        coordY = source.getCoordY();
    }

}

