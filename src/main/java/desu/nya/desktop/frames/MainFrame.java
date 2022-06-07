package desu.nya.desktop.frames;

import desu.nya.desktop.entities.Player;
import desu.nya.desktop.screen.ScreeShotMaker;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class MainFrame extends JFrame implements ActionListener {

    private final JLabel screenShot;
    private final JList<Player> playerList;
    private final ScreeShotMaker screenShotMaker;

    public MainFrame(ScreeShotMaker screenShotMaker) {
        super("DKP Participant List Catcher");
        this.screenShotMaker = screenShotMaker;
        this.playerList = new JList<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,300);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(400,  300);
        screenShot = new JLabel();
        screenShot.setSize(400, 300);

        panel.add(screenShot, BorderLayout.WEST);
        panel.add(listPanel(), BorderLayout.EAST);
        setContentPane(panel);
    }

    private JPanel listPanel() {
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setSize(200, 200);

        playerList.setSize(200, 200);
        listPanel.add(playerList, BorderLayout.NORTH);
        listPanel.add(buttonPanel(), BorderLayout.SOUTH);

        return listPanel;
    }

    private JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setSize(200, 20);
        JButton button = new JButton("Take a screenshot");
        button.setActionCommand("makeScreenShot");
        button.addActionListener(this);
        button.setSize(100, 18);
        buttonPanel.add(button, BorderLayout.WEST);

        button = new JButton("Exctract");
        button.setActionCommand("extractMembers");
        button.addActionListener(this);
        button.setSize(100, 18);
        buttonPanel.add(button, BorderLayout.EAST);

        return buttonPanel;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "makeScreenShot":
                makeScreenShot();
                break;
            case "extractMembers":
                extractInfo();
                break;
        }

    }

    private void makeScreenShot() {
        ImageIcon icon = new ImageIcon(screenShotMaker.screenShot(100, 100, 400, 300));
        screenShot.setIcon(icon);
    }

    private void extractInfo() {
        DefaultListModel<Player> players = new DefaultListModel<>();
        players.addElement (new Player("Me"));
        playerList.setModel(players);
        playerList.updateUI();
    }
}
