package classes;

public class Coordinate{
    int row;
    int col;

    @Override
    public String toString() {
        return "{" +
            " row='" + getRow() + "'" +
            ", col='" + getCol() + "'" +
            "}";
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }

    

}