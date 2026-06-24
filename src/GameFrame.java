import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;

    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();

        setTitle("Tic-Tac-Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        lblStatus = new JLabel("Your turn: X", SwingConstants.CENTER);
        add(lblStatus, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        buttons = new JButton[9];

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;

            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));

            buttons[i].addActionListener(e -> handlePlayerMove(index));

            boardPanel.add(buttons[i]);
        }

        add(boardPanel, BorderLayout.CENTER);
    }

    private void handlePlayerMove(int index) {
        boolean validMove = gameLogic.makeMove(index, 'X');

        if (!validMove) {
            JOptionPane.showMessageDialog(this, "Cell ini sudah terisi!");
            return;
        }

        buttons[index].setText("X");

        if (gameLogic.checkWinner('X')) {
            finishGame("WIN");
            return;
        }

        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        int computerIndex = gameLogic.computerMove();
        buttons[computerIndex].setText("O");

        if (gameLogic.checkWinner('O')) {
            finishGame("LOSE");
            return;
        }

        if (gameLogic.isDraw()) {
            finishGame("DRAW");
        }
    }

    private void finishGame(String result) {
        playerService.updateStatistics(currentPlayer, result);

        JOptionPane.showMessageDialog(this, "Game result: " + result);

        MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
        menuFrame.setVisible(true);
        this.dispose();
    }
}
