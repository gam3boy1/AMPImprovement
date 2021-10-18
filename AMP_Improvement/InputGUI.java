import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.FileDialog;
import java.awt.Frame;

public class InputGUI extends GUI implements ActionListener {
  private JButton submit;
  private JTextField tachyInput, vitalInput, turningInput, mutationsInput;
  private JLabel label1, label2, label3, label4;
  public InputGUI() {
    super("Input");

    label1 = new JLabel("Please enter the amino acid sequences with spaces in between each acid");
    label2 = new JLabel("Please enter the positions of the vital amino acids with the first amino acid being the first position. Do not include turning amino acids.");
    label3 = new JLabel("Please enter the positions of the turning amino acids with the first amino acid being the first position.");
    label4 = new JLabel("Please enter how many mutations you would like. Max: 2. Maximum set due to output quantity.");

    tachyInput = new JTextField(); vitalInput = new JTextField(); turningInput = new JTextField(); mutationsInput = new JTextField(); 
    submit = new JButton("Save Output");

    submit.addActionListener(this);

    this.getPanel().add(label1);
    this.getPanel().add(tachyInput);
    this.getPanel().add(label2);
    this.getPanel().add(vitalInput);
    this.getPanel().add(label3);
    this.getPanel().add(turningInput);
    this.getPanel().add(label4);
    this.getPanel().add(mutationsInput);
    this.getPanel().add(submit);

    this.setComponentAlignment();

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    ArrayList<Character> tachy;
    ArrayList<Integer> vital, turning;
    FileDialog fileDialog;
    int mutations;

    try {
      tachy = convertInputToArray(tachyInput, Character.class);
      vital = convertInputToArray(vitalInput, Integer.class);
      turning = convertInputToArray(turningInput, Integer.class);
      mutations = Integer.parseInt(mutationsInput.getText());

    } catch (Exception exception) {
      showMessage("Your input has an error", "All number positions and amino acids must be separated by commas and spaces");
      return;
    }

    fileDialog = new FileDialog(new Frame(), "Save file to directory/folder/location", FileDialog.SAVE);
    fileDialog.setDirectory(System.getProperty("user.dir"));
    fileDialog.setFile("output.FASTA");
    fileDialog.setVisible(true);
    fileDialog.setMultipleMode(false);

    String fileName = fileDialog.getFile();
    String fileExtension = fileName.substring(fileName.length()-5);

    if (fileExtension.equals("FASTA")) {
      String path = fileDialog.getDirectory() + fileName;
      new AMP(path, tachy.size(), mutations, tachy, vital, turning);
    } else {
      showMessage("Invalid File Name", "Please enter a file name with the extension \"FASTA\"");
    }
  }

  private <T> ArrayList<T> convertInputToArray(JTextField textField, Class<T> classType) throws Exception {
    ArrayList<T> array = new ArrayList<>();
    String[] strArray = textField.getText().split(", ");

    for (String str : strArray) {
      if (classType == Integer.class) {
        try {
          array.add(classType.cast(Integer.parseInt(str)));
        } catch (Exception e) {
          throw new Exception();
        }
      } else {
        char c = Character.toUpperCase(str.toCharArray()[0]);
        if (str.length() == 1 && Character.isLetter(c)) {
          array.add(classType.cast(c));
        } else {
          str.length();
          throw new Exception();
        }
      }
    }

    return array;
  }
  
}
