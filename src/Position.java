public class Position {
    private final int row;
    private final int col;

    public Position(int col, int row) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public String toString(){
        return "("+getCol()+","+getRow()+")";
    }
}
