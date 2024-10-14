import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_Panel = new JPanel();
    JPanel button_Panel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;

    TicTacToe() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setTitle("Tic Tac Game");
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 255, 0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("TIC TAC TOE");
        textField.setOpaque(true);

        button_Panel.setLayout(new GridLayout(3, 3));
        button_Panel.setBackground(new Color(150, 150, 150));

        title_Panel.setLayout(new BorderLayout());
        title_Panel.setBounds(0, 0, 800, 100);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_Panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_Panel.add(textField);
        frame.add(title_Panel, BorderLayout.NORTH);
        frame.add(button_Panel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                // Check if the button is already used
                if (buttons[i].getText().equals("")) {
                    if (player1_turn) {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textField.setText("O turn");
                        check();
                    } else {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textField.setText("X turn");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn() {
        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textField.setText("X turn");
        } else {
            player1_turn = false;
            textField.setText("O turn");
        }
    }

    public void check() {
        // Check X win conditions
        if (buttons[0].getText().equals("X") &&
                buttons[1].getText().equals("X") &&
                buttons[2].getText().equals("X")) {
            xWins(0, 1, 2); // Top row
        } else if (buttons[3].getText().equals("X") &&
                buttons[4].getText().equals("X") &&
                buttons[5].getText().equals("X")) {
            xWins(3, 4, 5); // Middle row
        } else if (buttons[6].getText().equals("X") &&
                buttons[7].getText().equals("X") &&
                buttons[8].getText().equals("X")) {
            xWins(6, 7, 8); // Bottom row
        } else if (buttons[0].getText().equals("X") &&
                buttons[3].getText().equals("X") &&
                buttons[6].getText().equals("X")) {
            xWins(0, 3, 6); // Left column
        } else if (buttons[1].getText().equals("X") &&
                buttons[4].getText().equals("X") &&
                buttons[7].getText().equals("X")) {
            xWins(1, 4, 7); // Middle column
        } else if (buttons[2].getText().equals("X") &&
                buttons[5].getText().equals("X") &&
                buttons[8].getText().equals("X")) {
            xWins(2, 5, 8); // Right column
        } else if (buttons[0].getText().equals("X") &&
                buttons[4].getText().equals("X") &&
                buttons[8].getText().equals("X")) {
            xWins(0, 4, 8); // Diagonal (top-left to bottom-right)
        } else if (buttons[2].getText().equals("X") &&
                buttons[4].getText().equals("X") &&
                buttons[6].getText().equals("X")) {
            xWins(2, 4, 6); // Diagonal (top-right to bottom-left)
        }

        // Check O win conditions
        else if (buttons[0].getText().equals("O") &&
                buttons[1].getText().equals("O") &&
                buttons[2].getText().equals("O")) {
            oWins(0, 1, 2); // Top row
        } else if (buttons[3].getText().equals("O") &&
                buttons[4].getText().equals("O") &&
                buttons[5].getText().equals("O")) {
            oWins(3, 4, 5); // Middle row
        } else if (buttons[6].getText().equals("O") &&
                buttons[7].getText().equals("O") &&
                buttons[8].getText().equals("O")) {
            oWins(6, 7, 8); // Bottom row
        } else if (buttons[0].getText().equals("O") &&
                buttons[3].getText().equals("O") &&
                buttons[6].getText().equals("O")) {
            oWins(0, 3, 6); // Left column
        } else if (buttons[1].getText().equals("O") &&
                buttons[4].getText().equals("O") &&
                buttons[7].getText().equals("O")) {
            oWins(1, 4, 7); // Middle column
        } else if (buttons[2].getText().equals("O") &&
                buttons[5].getText().equals("O") &&
                buttons[8].getText().equals("O")) {
            oWins(2, 5, 8); // Right column
        } else if (buttons[0].getText().equals("O") &&
                buttons[4].getText().equals("O") &&
                buttons[8].getText().equals("O")) {
            oWins(0, 4, 8); // Diagonal (top-left to bottom-right)
        } else if (buttons[2].getText().equals("O") &&
                buttons[4].getText().equals("O") &&
                buttons[6].getText().equals("O")) {
            oWins(2, 4, 6); // Diagonal (top-right to bottom-left)
        }
    }

    public void xWins(int a, int b, int c) {
        // Highlight the winning buttons
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        // Display a message to the player
        textField.setText("X Wins! 🎉");

        // Disable all buttons to end the game
        disableButtons();
    }

    public void oWins(int a, int b, int c) {
        // Highlight the winning buttons
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        // Display a message to the player
        textField.setText("O Wins! 🎉");

        // Disable all buttons to end the game
        disableButtons();
    }

    public void disableButtons() {
        // Disable all buttons so no more moves can be made
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
        }
    }
}
