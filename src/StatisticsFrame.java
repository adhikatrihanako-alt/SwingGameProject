import javax.swing.*;
import java.awt.*;

public class StatisticsFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;

    public StatisticsFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();

        Player latestPlayer = playerService.getPlayerById(currentPlayer.getId());

        setTitle("My Statistics");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        if (latestPlayer != null) {
            add(new JLabel("Username: " + latestPlayer.getUsername(), SwingConstants.CENTER));
            add(new JLabel("Wins: " + latestPlayer.getWins(), SwingConstants.CENTER));
            add(new JLabel("Losses: " + latestPlayer.getLosses(), SwingConstants.CENTER));
            add(new JLabel("Draws: " + latestPlayer.getDraws(), SwingConstants.CENTER));
            add(new JLabel("Score: " + latestPlayer.getScore(), SwingConstants.CENTER));
        } else {
            add(new JLabel("Failed to load statistics", SwingConstants.CENTER));
        }

        JButton btnClose = new JButton("Close");
        add(btnClose);

        btnClose.addActionListener(e -> this.dispose());
    }
}
