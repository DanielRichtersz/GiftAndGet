package danielrichtersz.LoginClientFrame;

import danielrichtersz.HttpClient.HttpClientGateway;
import danielrichtersz.PostCollectionFrame.ActionGroupFrame;

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

        JButton btn_Login = new JButton("Send test post request");
        btn_Login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //TODO: Send httprequesst and show homepage timeline
                HttpClientGateway httpClientGateway = new HttpClientGateway();

                String userData = httpClientGateway.LoginUser(tf_username.getText());

                if (userData != null || !userData.isEmpty()) {
                    setVisible(false);
                    ActionGroupFrame actionGroupFrame = new ActionGroupFrame();
                    actionGroupFrame.setVisible(true);
                }
            }
        });

        GridBagConstraints gridBagConstraints_btn_Login = new GridBagConstraints();
        gridBagConstraints_btn_Login.insets = new Insets(0, 0, 5, 5);
        gridBagConstraints_btn_Login.gridx = 2;
        gridBagConstraints_btn_Login.gridy = 2;
        contentPane.add(btn_Login, gridBagConstraints_btn_Login);

        jLabel_tf_username_errormsg = new JLabel("Errormsg:");
        GridBagConstraints gbConstraints_jLabel_username_errormsg = new GridBagConstraints();
        gbc_lblBody.insets = new Insets(0, 0, 5, 5);
        gbc_lblBody.gridx = 0;
        gbc_lblBody.gridy = 0;
        contentPane.add(jLabel_tf_username_errormsg, gbConstraints_jLabel_username_errormsg);

        JButton btn_RegisterUser = new JButton("Register");
        btn_RegisterUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //TODO: Send httprequesst and show homepage timeline
                HttpClientGateway httpClientGateway = new HttpClientGateway();
                httpClientGateway.SendPostRequest();
            }
        });

        GridBagConstraints gridBagConstraints_btn_RegisterUser = new GridBagConstraints();
        gridBagConstraints_btn_Login.insets = new Insets(0, 0, 5, 5);
        gridBagConstraints_btn_Login.gridx = 2;
        gridBagConstraints_btn_Login.gridy = 2;
        contentPane.add(btn_RegisterUser, gridBagConstraints_btn_Login);
    }
}
