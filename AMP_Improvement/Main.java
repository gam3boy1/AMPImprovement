import java.awt.*;
import javax.swing.*;

public class Main {
  private static JFrame frame;
  private static GUI UI;
  public static void main(String[] args) {
    initializeFrame();
  }

  private static void initializeFrame() {
    frame = new JFrame();
    UI = new HomeGUI();
    frame.add(UI.getPanel(), BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    frame.setTitle("AMP Improvement");
    frame.pack();
    frame.setVisible(true);
    try {
      // Set cross-platform Java L&F (also called "Metal")
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      // handle exception
      e.printStackTrace();
    }
  }

    public static void setGUI(GUI ui) {
      Main.UI = ui;
    }

    public static JFrame getFrame() {
      return Main.frame;
    }

    public static GUI getGUI() {
      return Main.UI;
    }
}