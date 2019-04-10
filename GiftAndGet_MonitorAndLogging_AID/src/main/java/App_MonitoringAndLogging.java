import java.awt.*;

public class App_MonitoringAndLogging {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Logging logging = new Logging();
                    logging.startLogging("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
