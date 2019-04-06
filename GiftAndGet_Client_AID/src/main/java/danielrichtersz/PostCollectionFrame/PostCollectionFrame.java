package danielrichtersz.PostCollectionFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PostCollectionFrame extends JFrame {

    private JPanel contentPane;

    public PostCollectionFrame() {
        setTitle("GiftAndGet");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 684, 619);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);


        //TODO: Show collection of posts
        JLabel lblBody = new JLabel("Name of collection (Subreddit, Multireddit)");
        GridBagConstraints gbc_lblBody = new GridBagConstraints();
        gbc_lblBody.insets = new Insets(0, 0, 5, 5);
        gbc_lblBody.gridx = 0;
        gbc_lblBody.gridy = 0;
        contentPane.add(lblBody, gbc_lblBody);
    }
}