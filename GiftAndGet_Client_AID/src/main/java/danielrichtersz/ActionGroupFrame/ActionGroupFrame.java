package danielrichtersz.ActionGroupFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ActionGroupFrame extends JFrame {

    private JPanel contentPane;

    public ActionGroupFrame() {
        setTitle("GiftAndGet");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 684, 619);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        //TODO: Show collection of posts
        JLabel lblAGName = new JLabel("Name of actiongroup:");
        GridBagConstraints gridBagConstraints_AGName = new GridBagConstraints();
        gridBagConstraints_AGName.insets = new Insets(0, 0, 5, 5);
        gridBagConstraints_AGName.gridx = 0;
        gridBagConstraints_AGName.gridy = 0;
        contentPane.add(lblAGName, gridBagConstraints_AGName);

        JLabel lblAGDescription = new JLabel("Description of actiongroup:");
        GridBagConstraints gridBagConstraints_AGDescription = new GridBagConstraints();
        gridBagConstraints_AGDescription.insets = new Insets(0, 0, 5, 5);
        gridBagConstraints_AGDescription.gridx = 0;
        gridBagConstraints_AGDescription.gridy = 0;
        contentPane.add(lblAGDescription, gridBagConstraints_AGDescription);
    }
}
