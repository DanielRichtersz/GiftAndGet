package danielrichtersz.ActionGroupsOverviewFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionGroupsOverviewFrame extends JFrame {

    private ActionGroupsOverviewRestGateway actionGroupsOverviewRestGateway;
    private ActionGroupsOverviewJMSGateway actionGroupsOverviewJMSGateway;

    private JPanel contentPane;

    private String username = "";

    public ActionGroupsOverviewFrame(String overviewOf) {
        this.username = overviewOf;
        actionGroupsOverviewRestGateway = new ActionGroupsOverviewRestGateway();
        actionGroupsOverviewJMSGateway = new ActionGroupsOverviewJMSGateway();
        setTitle("Overview of: " + overviewOf);

        initializeFrameActions();
        getCharities();
        showLoggedInUserInformation(overviewOf);
        registerCharityForm();
        registerActionGroupForm();
        actionGroupsOverviewJMSGateway.getAvailableGroups();

    }

    private void initializeFrameActions() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 684, 619);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
    }

    private void showLoggedInUserInformation(String overviewOf) {
        //----------LOGGED IN AS INFORMATION
        //TODO: Show collection of actiongroups
        JLabel lblLoggedInAs = new JLabel("Logged in as: " + overviewOf);
        GridBagConstraints gbc_lblBody = new GridBagConstraints();
        gbc_lblBody.insets = new Insets(0, 0, 5, 5);
        gbc_lblBody.gridx = 0;
        gbc_lblBody.gridy = 0;
        contentPane.add(lblLoggedInAs, gbc_lblBody);
    }

    private void registerActionGroupForm() {
        JButton btn_RegisterActionGroup = new JButton("Register new action group");
        btn_RegisterActionGroup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //TODO: Register action group
                actionGroupsOverviewRestGateway.createActionGroup("NewActionGroupTitle", "NewActionGroupDescription", (long)0.5, username, "Unicef");
            }
        });

        GridBagConstraints gridBagConstraints_btn_RegisterActionGroup = new GridBagConstraints();
        gridBagConstraints_btn_RegisterActionGroup.insets = new Insets(0, 0, 5, 5);
        gridBagConstraints_btn_RegisterActionGroup.gridx = 2;
        gridBagConstraints_btn_RegisterActionGroup.gridy = 2;
        contentPane.add(btn_RegisterActionGroup, gridBagConstraints_btn_RegisterActionGroup);
    }

    private void registerCharityForm() {
        JButton btn_RegisterCharity = new JButton("Register new charity");
        btn_RegisterCharity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //TODO: Register charity
                int a = 0;
            }
        });

        GridBagConstraints gridBagConstraints_btn_RegisterCharity = new GridBagConstraints();
        gridBagConstraints_btn_RegisterCharity.insets = new Insets(0, 0, 5, 5);
        gridBagConstraints_btn_RegisterCharity.gridx = 2;
        gridBagConstraints_btn_RegisterCharity.gridy = 2;
        contentPane.add(btn_RegisterCharity, gridBagConstraints_btn_RegisterCharity);
    }

    private void getCharities() {
        JButton btn_retrieveCharities = new JButton("Retrieve available charities");
        btn_retrieveCharities.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //TODO: Register action group
                actionGroupsOverviewRestGateway.getCharities();
                System.out.println("Retrieving charities executed");
            }
        });

        GridBagConstraints gbc_RetrieveCharities = new GridBagConstraints();
        gbc_RetrieveCharities.insets = new Insets(0, 0, 5, 5);
        gbc_RetrieveCharities.gridx = 2;
        gbc_RetrieveCharities.gridy = 2;
        contentPane.add(btn_retrieveCharities, gbc_RetrieveCharities);
    }
}
