package ui;

import model.SearchBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DisplaySearchBoard extends JPanel {

    public static final int SQUARE_SIZE = 100;
    public static final int SIGNAL_SELECTED_GRID_NULL = -1;

    private SearchBoard board;
    private int locationX;
    private int locationY;
    private SearchBoardBuilder gameDisplay;

    public DisplaySearchBoard(SearchBoard b, SearchBoardBuilder frame) {
        locationX = SIGNAL_SELECTED_GRID_NULL;
        gameDisplay = frame;
        board = b;
        setSize(board.getWidth() * SQUARE_SIZE,board.getHeight() * SQUARE_SIZE);
        gameDisplay.setSize(board.getWidth() * SQUARE_SIZE,board.getHeight() * SQUARE_SIZE + 35);
        gameDisplay.setResizable(false);
        gameDisplay.setLocationRelativeTo(null);
        gameDisplay.add(this, BorderLayout.CENTER);
        addMouseListener(new MouseHandler());
        addKeyListener(new KeyHandler());
        requestFocus();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawSelectedGrid(g);
        drawLetter(g);
    }

    // MODIFIES: this
    // EFFECTS: draws the grid for the word search game
    private void drawGrid(Graphics g) {
        g.setColor(Color.black);
        for (int i = 1; i <= board.getWidth(); i++) {
            g.drawLine(SQUARE_SIZE * i, 0 , SQUARE_SIZE * i, board.getHeight() * SQUARE_SIZE);
        }
        for (int i = 1; i <= board.getHeight(); i++) {
            g.drawLine(0, SQUARE_SIZE * i , board.getWidth() * SQUARE_SIZE, SQUARE_SIZE * i);
        }
    }

    // MODIFIES: this
    // EFFECTS: highlights in blue the grid that is has been selected
    private void drawSelectedGrid(Graphics g) {
        g.setColor(Color.BLUE);
        if (locationX != SIGNAL_SELECTED_GRID_NULL) {
            g.fillRect( locationX * SQUARE_SIZE, locationY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }

    // MODIFIES: this
    // EFFECTS: draws every char on the board to the panel
    private void drawLetter(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Monospaced", Font.BOLD, (int) (SQUARE_SIZE * 1.5)));
        for (int w = 0; w < board.getWidth(); w++) {
            for (int h = 0; h < board.getHeight(); h++) {
                if (board.getValue(w,h) != null) {
                    g.drawString(board.getValue(w,h).toString(), w * SQUARE_SIZE, (h + 1) * SQUARE_SIZE);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the location of the grid being selected
    public void gridSelected(Point location) {
        locationX = location.x / SQUARE_SIZE;
        locationY = location.y / SQUARE_SIZE;
    }

    // EFFECTS: gets location when mouse has been clicked
    private class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent me) {
            gridSelected(me.getPoint());
        }
    }

    // EFFECTS: gets the char from the keyboard that has entered
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            board.setLocation(Character.toUpperCase(e.getKeyChar()),locationX,locationY);
        }
    }

}
