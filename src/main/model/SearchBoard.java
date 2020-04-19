package model;


// The class the has the value of the board
public class SearchBoard {

    private Character[][] board;
    private int width;
    private int height;


    public SearchBoard(int width, int heigth) {
        board = new Character[width][heigth];
        this.width = width;
        this.height = heigth;
    }

    //setter
    public void setLocation(Character letter, int x, int y) {
        board[x][y] = letter;
    }

    //getter
    public Character getValue(int x, int y) {
        return board[x][y];
    }

    //getter
    public int getWidth() {
        return width;
    }

    //getter
    public int getHeight() {
        return height;
    }
}
