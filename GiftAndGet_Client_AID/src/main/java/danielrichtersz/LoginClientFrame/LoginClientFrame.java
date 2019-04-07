package danielrichtersz.LoginClientFrame;

import danielrichtersz.HttpClient.HttpClientGateway;
import danielrichtersz.PostCollectionFrame.ActionGroupFrame;
import danielrichtersz.RegisterClientFrame.RegisterClientFrame;

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

        //-------------USER LOGIN
        //-------------
        //USERNAME TEXTFIELD
        //-------------
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

        jLabel_tf_username_errormsg = new JLabel("Errormsg:");
        jLabel_tf_username_errormsg.setVisible(false);
        GridBagConstraints gbConstraints_jLabel_username_errormsg = new GridBagConstraints();
        gbConstraints_jLabel_username_errormsg.insets = new Insets(0, 0, 5, 5);
        gbConstraints_jLabel_username_errormsg.gridx = 0;
        gbConstraints_jLabel_username_errormsg.gridy = 0;
        contentPane.add(jLabel_tf_username_errormsg, gbConstraints_jLabel_username_errormsg);

        //-------------
        //LOGIN BUTTON
        //-------------
        JButton btn_Login = new JButton("Login");
        btn_Login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //TODO: Send httprequesst and show homepage timeline
                if (tf_username.getText().isEmpty()) {
                    jLabel_tf_username_errormsg.setText("Please fill in all textfields");
                    jLabel_tf_username_errormsg.setVisible(true);
                }
                else {
                    HttpClientGateway httpClientGateway = new HttpClientGateway();
                    String userData = httpClientGateway.LoginUser(tf_username.getText());

                    if (userData != null && !userData.isEmpty()) {
                        jLabel_tf_username_errormsg.setVisible(false);
                        setVisible(false);
                        ActionGroupFrame actionGroupFrame = new ActionGroupFrame();
                        actionGroupFrame.setVisible(true);
                    }
                    else {
                        jLabel_tf_username_errormsg.setText("Incorrect login");
                        jLabel_tf_username_errormsg.setVisible(true);
                    }
                }
            }
        });

        GridBagConstraints gridBagConstraints_btn_Login = new GridBagConstraints();
        gridBagConstraints_btn_Login.insets = new Insets(0, 0, 5, 5);
        gridBagConstraints_btn_Login.gridx = 2;
        gridBagConstraints_btn_Login.gridy = 2;
        contentPane.add(btn_Login, gridBagConstraints_btn_Login);

        JButton btn_Register = new JButton("Register new user");
        btn_Register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                RegisterClientFrame registerClientFrame = new RegisterClientFrame();
                registerClientFrame.setVisible(true);

                setVisible(false);
            }
        });

        GridBagConstraints gridBagConstraints_btn_Register = new GridBagConstraints();
        gridBagConstraints_btn_Register.insets = new Insets(0, 0, 5, 5);
        gridBagConstraints_btn_Register.gridx = 4;
        gridBagConstraints_btn_Register.gridy = 4;
        contentPane.add(btn_Register, gridBagConstraints_btn_Register);


    }
}
