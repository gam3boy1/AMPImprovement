import javax.swing.*;


import java.awt.Font;
import java.awt.event.*;

public class HomeGUI extends GUI implements ActionListener {
  private JButton continueButton;
  private JLabel introLabel, infoLabel;
  
  public HomeGUI() {
    super("Home");

    introLabel = new JLabel("Welcome to AMP Improvement!");
    infoLabel = new JLabel("Click \"Continue\" to start the program");
    continueButton = new JButton("Continue");

    introLabel.setFont(new Font(introLabel.getFont().getName(), Font.PLAIN, 60));
    continueButton.addActionListener(this);

    this.getPanel().add(introLabel);
    this.getPanel().add(infoLabel);
    this.getPanel().add(continueButton);

    this.setComponentAlignment();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.getPanel().setVisible(false);
  }
}