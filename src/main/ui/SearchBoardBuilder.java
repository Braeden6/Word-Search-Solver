package ui;

import model.SearchBoard;

import javax.swing.*;

public class SearchBoardBuilder extends JFrame {
    public static final int START_SIZE = 8;
    public static final int INTERVAL = 5;

    private SearchBoard board;
    private JTextField inputWidth;
    private JTextField inputHeight;
    private JPanel inputPanel;
    private DisplaySearchBoard display;

    public SearchBoardBuilder() {
        super("Word Search Solver");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(340,200);
        setLocationRelativeTo(null);
        setLayout(null);
        inputDisplay();
        setVisible(true);
    }


    // MODIFIES: this
    // EFFECTS: adds display that allows user to enter width and height of board with a submit button
    private void inputDisplay() {
        inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setLocation(0,0);
        inputPanel.setSize(500, 500);

        JLabel widthLabel = new JLabel("                   Width");
        widthLabel.setSize(20 * START_SIZE,4 * START_SIZE);
        widthLabel.setLocation(0,0);

        JLabel heightLabel = new JLabel("                       Height");
        heightLabel.setSize(20 * START_SIZE,4 * START_SIZE);
        heightLabel.setLocation(20 * START_SIZE,0);

        inputWidth = new JTextField();
        inputWidth.setSize(20 * START_SIZE,4 * START_SIZE);
        inputWidth.setLocation(0,4 * START_SIZE);

        inputHeight = new JTextField();
        inputHeight.setSize(20 * START_SIZE,4 * START_SIZE);
        inputHeight.setLocation(20 * START_SIZE,4 * START_SIZE);

        JButton submit = new JButton("Submit");
        submit.setSize(20 * START_SIZE,4 * START_SIZE);
        submit.setLocation(10 * START_SIZE,8 * START_SIZE);
        submit.addActionListener(e -> createGame());

        inputPanel.add(widthLabel);
        inputPanel.add(heightLabel);
        inputPanel.add(inputWidth);
        inputPanel.add(inputHeight);
        inputPanel.add(submit);

        add(inputPanel);
    }

    // MODIFIES: this
    // EFFECTS: gets input from input boxes and created a search board and display
    private void createGame() {
        int width;
        int height;
        try{
            width = Integer.parseInt(inputWidth.getText());
        } catch (Exception e) {
            return;
        }
        try {
            height = Integer.parseInt(inputHeight.getText());
        } catch (Exception e) {
            return;
        }
        board = new SearchBoard(width,height);
        display =  new DisplaySearchBoard(board, this);
        remove(inputPanel);
        addTimer();
    }

    // Set up timer
    // MODIFIES: none
    // EFFECTS:  initializes a timer that updates game each
    //           milliseconds
    private void addTimer() {
        Timer timer = new Timer(INTERVAL, ae -> {
            display.repaint();
        });
        timer.start();
    }
}
