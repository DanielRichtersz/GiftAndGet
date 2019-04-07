package danielrichtersz.RegisterClientFrame;

import danielrichtersz.HttpClient.HttpClientGateway;
import danielrichtersz.PostClientFrame.ActionGroupsOverviewFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterClientFrame extends JFrame {

    private JPanel contentPane;

    private JTextField tf_registerUsername;
    private JTextField tf_registerEmail;
    private JTextField tf_registerPassword;

    private JLabel lblError;

    public RegisterClientFrame() {
        setTitle("GiftAndGet");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 684, 619);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        //-------------REGISTER NEW USER
        //-------------
        //EMAIL TEXTFIELD
        //-------------
        JLabel lblEmail = new JLabel("Email:");
        GridBagConstraints gbc_lblEmail = new GridBagConstraints();
        gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
        gbc_lblEmail.gridx = 0;
        gbc_lblEmail.gridy = 0;
        contentPane.add(lblEmail, gbc_lblEmail);

        tf_registerEmail = new JTextField();
        GridBagConstraints gridBagConstraints_tf_registerEmail = new GridBagConstraints();
        gridBagConstraints_tf_registerEmail.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints_tf_registerEmail.insets = new Insets(0, 0, 5, 5);
        gridBagConstraints_tf_registerEmail.gridx = 1;
        gridBagConstraints_tf_registerEmail.gridy = 0;
        contentPane.add(tf_registerEmail, gridBagConstraints_tf_registerEmail);
        tf_registerEmail.setColumns(10);

        //-------------
        //USERNAME TEXTFIELD
        //-------------
        JLabel lblUsername = new JLabel("Username:");
        GridBagConstraints gbc_lblUsername = new GridBagConstraints();
        gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
        gbc_lblUsername.gridx = 0;
        gbc_lblUsername.gridy = 0;
        contentPane.add(lblUsername, gbc_lblUsername);

        tf_registerUsername = new JTextField();
        GridBagConstraints gridBagConstraints_tf_registerUsername = new GridBagConstraints();
        gridBagConstraints_tf_registerUsername.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints_tf_registerUsername.insets = new Insets(0, 0, 5, 5);
        gridBagConstraints_tf_registerUsername.gridx = 1;
        gridBagConstraints_tf_registerUsername.gridy = 0;
        contentPane.add(tf_registerUsername, gridBagConstraints_tf_registerUsername);
        tf_registerUsername.setColumns(10);

        //-------------
        //PASSWORD TEXTFIELD
        //-------------
        JLabel lblPassword = new JLabel("Password:");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.gridx = 0;
        gbc_lblPassword.gridy = 0;
        contentPane.add(lblPassword, gbc_lblPassword);

        tf_registerPassword = new JTextField();
        GridBagConstraints gridBagConstraints_registerPassword = new GridBagConstraints();
        gridBagConstraints_registerPassword.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints_registerPassword.insets = new Insets(0, 0, 5, 5);
        gridBagConstraints_registerPassword.gridx = 1;
        gridBagConstraints_registerPassword.gridy = 0;
        contentPane.add(tf_registerPassword, gridBagConstraints_registerPassword);
        tf_registerPassword.setColumns(10);

        //-------------
        //REGISTER BUTTON
        //-------------
        JButton btn_RegisterUser = new JButton("Register");
        btn_RegisterUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //TODO: Send httprequesst and show homepage timeline
                if (!tf_registerEmail.getText().isEmpty() ||
                        !tf_registerUsername.getText().isEmpty() ||
                        !tf_registerPassword.getText().isEmpty()) {

                    HttpClientGateway httpClientGateway = new HttpClientGateway();
                    String response = httpClientGateway.RegisterNewUser(
                            tf_registerEmail.getText(),
                            tf_registerUsername.getText(),
                            tf_registerPassword.getText());

                    if (response != null) {

                       String userAccountUsername = extractUsernameFromResponse(response);

                        if (userAccountUsername != null) {
                            ActionGroupsOverviewFrame actionGroupsOverviewFrame = new ActionGroupsOverviewFrame(userAccountUsername);
                            actionGroupsOverviewFrame.setVisible(true);

                            setVisible(false);
                        }
                        else {
                            lblError.setText(response);
                            lblError.setVisible(true);
                        }
                    }
                    else {
                        lblError.setText("Something went wrong while creating user, please try again or contact customer support");
                        lblError.setVisible(true);
                    }
                }
                else {
                    lblError.setText("Please fill in all fields");
                    lblError.setVisible(true);
                }
            }
        });

        lblError = new JLabel("Error:");
        GridBagConstraints gbc_lblError = new GridBagConstraints();
        gbc_lblError.insets = new Insets(0, 0, 5, 5);
        gbc_lblError.gridx = 0;
        gbc_lblError.gridy = 0;
        contentPane.add(lblError, gbc_lblError);
        lblError.setVisible(false);

        GridBagConstraints gridBagConstraints_btn_RegisterUser = new GridBagConstraints();
        gridBagConstraints_btn_RegisterUser.insets = new Insets(0, 0, 5, 5);
        gridBagConstraints_btn_RegisterUser.gridx = 2;
        gridBagConstraints_btn_RegisterUser.gridy = 2;
        contentPane.add(btn_RegisterUser, gridBagConstraints_btn_RegisterUser);
    }

    private String extractUsernameFromResponse(String response) {

        int start = response.indexOf("\"username\":") + 12;
        int end = response.indexOf("\",", start);

        if (start == -1 || end == -1) {
            return null;
        }
        return response.substring(start, end);
    }
}
