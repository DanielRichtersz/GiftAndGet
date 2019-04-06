package danielrichtersz.LoginClientFrame;

import danielrichtersz.PostCollectionFrame.PostCollectionFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginClientFrame extends JFrame {

    private JPanel contentPane;
    private JTextField tf_username;
    private JLabel jLabel_tf_username_errormsg;


    public LoginClientFrame() {
        setTitle("GiftAndGet");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 684, 619);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblBody = new JLabel("Username:");
        GridBagConstraints gbc_lblBody = new GridBagConstraints();
        gbc_lblBody.insets = new Insets(0, 0, 5, 5);
        gbc_lblBody.gridx = 0;
        gbc_lblBody.gridy = 0;
        contentPane.add(lblBody, gbc_lblBody);

        tf_username = new JTextField();
        GridBagConstraints gbc_tfSSN = new GridBagConstraints();
        gbc_tfSSN.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfSSN.insets = new Insets(0, 0, 5, 5);
        gbc_tfSSN.gridx = 1;
        gbc_tfSSN.gridy = 0;
        contentPane.add(tf_username, gbc_tfSSN);
        tf_username.setColumns(10);

        JButton btnQueue = new JButton("send loan request");
        btnQueue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String username = tf_username.getText();
                setVisible(false);
                PostCollectionFrame postCollectionFrame = new PostCollectionFrame();
                postCollectionFrame.setVisible(true);
                //TODO: Send httprequesst and show homepage timeline

            }
        });

        GridBagConstraints gbc_btnQueue = new GridBagConstraints();
        gbc_btnQueue.insets = new Insets(0, 0, 5, 5);
        gbc_btnQueue.gridx = 2;
        gbc_btnQueue.gridy = 2;
        contentPane.add(btnQueue, gbc_btnQueue);

        jLabel_tf_username_errormsg = new JLabel("Errormsg:");
        GridBagConstraints gbConstraints_jLabel_username_errormsg = new GridBagConstraints();
        gbc_lblBody.insets = new Insets(0, 0, 5, 5);
        gbc_lblBody.gridx = 0;
        gbc_lblBody.gridy = 0;
        contentPane.add(jLabel_tf_username_errormsg, gbConstraints_jLabel_username_errormsg);
    }
}
