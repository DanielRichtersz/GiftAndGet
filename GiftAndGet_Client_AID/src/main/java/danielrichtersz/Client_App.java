package danielrichtersz;

import danielrichtersz.ActionGroupsOverviewFrame.ActionGroupsOverviewJMSGateway;
import danielrichtersz.LoginClientFrame.LoginClientFrame;

import java.awt.*;

public class Client_App {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginClientFrame frame = new LoginClientFrame();

                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
