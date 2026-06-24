import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    private Player currentPlayer;

    public MainMenuFrame(Player player) {
        this.currentPlayer = player;

        setTitle("Main Menu");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        JLabel lblWelcome = new JLabel("Welcome, " + currentPlayer.getUsername(), SwingConstants.CENTER);

        JButton btnStartGame = new JButton("Start Game");
        JButton btnStatistics = new JButton("My Statistics");
        JButton btnTopScorers = new JButton("Top 5 Scorers");
        JButton btnExit = new JButton("Exit");

        add(lblWelcome);
        add(btnStartGame);
        add(btnStatistics);
        add(btnTopScorers);
        add(btnExit);

        btnStartGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(currentPlayer);
            gameFrame.setVisible(true);
            this.dispose();
        });

        btnStatistics.addActionListener(e -> {
            StatisticsFrame statisticsFrame = new StatisticsFrame(currentPlayer);
            statisticsFrame.setVisible(true);
        });

        btnTopScorers.addActionListener(e -> {
            TopScorersFrame topFrame = new TopScorersFrame();
            topFrame.setVisible(true);
        });

        btnExit.addActionListener(e -> System.exit(0));
    }
}
