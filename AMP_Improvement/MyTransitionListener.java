import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MyTransitionListener extends ComponentAdapter {
    @Override
    public void componentHidden(ComponentEvent e) {
        JPanel currentPanel = ((JPanel) e.getSource());

        switch (currentPanel.getName()) {
            case "Home":
            Main.setGUI(new InputGUI());
            break;

            default:
            break;
        }

        currentPanel.getParent().add(Main.getGUI().getPanel(), BorderLayout.CENTER);
        currentPanel.getParent().revalidate();
        currentPanel.getParent().repaint();
    }
}
