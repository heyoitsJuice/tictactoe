/**
 * Homework #11 CS 191
 * @author Justin Clenista
 */

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Swing extends JFrame implements ActionListener{
    // Instance Variables
    private JPanel board;
    private JButton gridPosition[][];
    private JFrame windowLabel;
    private JLabel defaultPlayer;
    private boolean turn;
    private char[][] shapes;

    public Swing(){
        // Constructs a frame for the window in which the game will be held
        windowLabel = new JFrame();
        windowLabel.setTitle("Tic-Tac-Toe (Noughts and Crosses)");
        windowLabel.setVisible(true);

        // Designates which player's move it is within the frame
        defaultPlayer = new JLabel("First Player to Move.:");
        defaultPlayer.setVisible(true);

        windowLabel.add(defaultPlayer, BorderLayout.NORTH);
        // Sets the first player's turn as a valid move
        turn = true;

        // Creates a board that will be integrated into the frame
        board = new JPanel(new  GridLayout(3,3));
        board.setVisible(true);

        // Makes each space on the grid an interactable object (button)
        gridPosition = new JButton[3][3];
        shapes = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                gridPosition[i][j] = new JButton("");
                gridPosition[i][j].setEnabled(true);
                gridPosition[i][j].setPreferredSize(new Dimension(250,250));
                gridPosition[i][j].addActionListener(this);
                gridPosition[i][j].setVisible(true);
                gridPosition[i][j].setActionCommand(i + " " + j);
                board.add(gridPosition[i][j]);
                shapes[i][j] = '.';
            }
        }

        // Creates a container for the layout of the board so each space on the grid can only hold one interaction
        windowLabel.add(board, BorderLayout.CENTER);
    }
    // Methods and Logic

    private boolean winner(char place, char[][] board){
        for(int col = 0; col < 3; col++){
            int counter = 0;
            for(int row = 0; row < 3; row++){
                if(board[row][col] == place){
                    counter++;
                }
                else{
                    counter = 0;
                }
            }
            if(counter == 3){
                return true;
            }
        }

        for(int row = 0; row < 3; row++){
            int counter = 0;
            for(int col = 0; col < 3; col++){
                if(board[row][col] == place){
                    counter++;
                }
                else{
                    counter = 0;
                }
            }
            if(counter == 3){
                return true;
            }
        }

        // Checks if true, else returns false
        if (board[1][1] == place && board[0][0] == place && board[2][2] == place){
            return true;
        }


        if (board[1][1] == place && board[0][2] == place && board[2][0] == place){
            return true;
        }

        return false;
    }

    private boolean tie(char[][] board){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == '.'){
                    return false;
                }
            }
        }
        return true;
    }

    public void actionPerformed(ActionEvent e){
            /*
            if(e.getSource() == position[0][0])setButton(0,0);
            if(e.getSource() == position[0][1])setButton(0,1);
            if(e.getSource() == position[0][2])setButton(0,2);
            if(e.getSource() == position[1][0])setButton(1,0);
            if(e.getSource() == position[1][1])setButton(1,1);
            if(e.getSource() == position[1][2])setButton(1,2);
            if(e.getSource() == position[2][0])setButton(2,0);
            if(e.getSource() == position[2][1])setButton(2,1);
            if(e.getSource() == position[2][2])setButton(2,2);
            */

        String action = e.getActionCommand();
        if(action.length() == 3){
            Scanner input = new Scanner(action);
            String rows = input.next();
            String cols = input.next();
            int row = Integer.parseInt(rows);
            int col = Integer.parseInt(cols);

            JButton gridButton = gridPosition[row][col];
            if(gridButton.isEnabled()){
                if(turn){
                    defaultPlayer.setText("Player 1 to move.");
                    gridButton.setText("X");
                    shapes[row][col] = 'X';
                    gridButton.setEnabled(false);
                    turn = false;
                }

                else{
                    defaultPlayer.setText("Player 2 to move.");
                    gridButton.setText("O");
                    shapes[row][col] = 'O';
                    gridButton.setEnabled(true);
                    turn = true;
                }
            }

            if(winner('X', shapes)){
                defaultPlayer.setText("Player 1 wins the game!");
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        gridPosition[i][j].setEnabled(false);
                    }
                }
            }

            if(winner('O', shapes)){
                defaultPlayer.setText("Player 2 wins the game!");
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        gridPosition[i][j].setEnabled(false);
                    }
                }
            }

            if(tie(shapes)){
                defaultPlayer.setText("The game ends in a draw/tie.");
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        gridPosition[i][j].setEnabled(false);
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        Swing tictactoe = new Swing();
        tictactoe.setVisible(true);
    }
}
