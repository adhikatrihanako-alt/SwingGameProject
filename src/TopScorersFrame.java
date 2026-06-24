import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TopScorersFrame extends JFrame {
    private JTable table;
    private PlayerService playerService;

    public TopScorersFrame() {
        playerService = new PlayerService();

        setTitle("Top 5 Scorers");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columns = {"Username", "Wins", "Losses", "Draws", "Score"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        ArrayList<Player> players = playerService.getTopFiveScorers();

        for (Player player : players) {
            Object[] row = {
                    player.getUsername(),
                    player.getWins(),
                    player.getLosses(),
                    player.getDraws(),
                    player.getScore()
            };

            model.addRow(row);
        }

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnClose = new JButton("Close");
        add(btnClose, BorderLayout.SOUTH);

        btnClose.addActionListener(e -> this.dispose());
    }
}
