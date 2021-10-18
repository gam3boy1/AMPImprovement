import java.io.*;
import java.util.*;

public class AMP {
  public int total = 1;
  public int sizes;
  public ArrayList<Character> allAA = new ArrayList<Character>(Arrays.asList('A', 'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y', 'V'));
  public ArrayList<Character> cPosPhilic = new ArrayList<Character>(Arrays.asList('R', 'K', 'H'));
  public ArrayList<Character> phobicClassification = new ArrayList<Character>(Arrays.asList('V', 'I', 'L', 'M', 'F', 'W', 'C', 'A', 'I'));
  public ArrayList<Character> phobic = new ArrayList<Character>(Arrays.asList('V', 'I', 'L', 'F'));
  public ArrayList<Character> middle = new ArrayList<Character>(Arrays.asList('T', 'S', 'Y', 'H'));
  public ArrayList<Character> philic = new ArrayList<Character>(Arrays.asList('N', 'D', 'Q', 'E', 'K', 'R'));
  public ArrayList<Character> turningAA = new ArrayList<Character>(Arrays.asList('P', 'N', 'D', 'R', 'G'));

  public AMP(String path, int sizes, int mutations, ArrayList<Character> Tachy_I_Base, ArrayList<Integer> vital, ArrayList<Integer> turning) {
    this.sizes = sizes;

    try {
      System.setOut(new PrintStream(path));
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    }

    for (int i = 0; i < vital.size(); i++) {
      vital.set(i, vital.get(i)-1);
    }

    for (int i = 0; i < turning.size(); i++) {
      turning.set(i, turning.get(i)-1);
    }

    System.out.println(">Tachy_I_Original");
    for (int i = 0; i < sizes; i++){
      System.out.print(Tachy_I_Base.get(i));
    }
    System.out.println(); 

    bSheet(Tachy_I_Base, vital, turning, mutations);
  }


  public void bSheet (ArrayList<Character> Tachy_I_Base, ArrayList<Integer> vital, ArrayList<Integer> turning, int mutations){
    char classification[] = new char[sizes];
    for (int i = 0; i < sizes; i++){
      if (find(vital, i)){
        classification[i] = 'V';                        // V is vital for structure
      } else if (find(turning, i)) {
        classification[i] = 'T';                        // T is Beta turn
      } else if (find(phobicClassification, Tachy_I_Base.get(i))){
        classification[i] = 'O';                        // O is hydrophobic
      } else if (find(philic, Tachy_I_Base.get(i))){
        classification[i] = 'I';                        // I is hydrophilic
      } else if (find(middle, Tachy_I_Base.get(i))) {
        classification[i] = 'M';                        // M is medium phobicity
      }
    }
    char previous = 'I';
    for (int i = 1; i < sizes; i++){
      if ((classification[i]!= 'T') && (classification[i] != 'V')){
        if (previous == 'O'){
          classification[i] = 'I';
          Tachy_I_Base.set(i, 'R');     // default amino acid for adjusting to template is Argenine for new hydrophilic/cationic residues, chosen for high positive charge and hydrophilicity
        } else if (previous == 'I'){
          classification[i] = 'O';
          Tachy_I_Base.set(i, 'V');     // default amino acid for adjusting to template is Valine for new hydrophobic residues, chosen arbitrarily based on hydrophobicity
        }
        previous = classification[i]; 
      } 
    }

    // for (int i = 0; i < 17; i++) {
    //   System.out.print(classification[i]);
    // }

    alterAmtMid(Tachy_I_Base, classification, 0, mutations, 0);
  }

  public void alterAmtMid(ArrayList<Character> toBeAltered, char classification[], int Min, int mutations, int current){
    char temp;

    for (int i = Min; i < (sizes-(mutations-current));i++){
      temp = toBeAltered.get(i);
      ArrayList<Character> tempArray = new ArrayList<Character>();

      switch (classification[i]) {
        case 'T':
        tempArray = turningAA;
        break;

        case 'I':
        tempArray = cPosPhilic;
        break;

        case 'O':
        tempArray = phobic;
        break;

        default:
        break;
      }

      for (int z = 0; z < tempArray.size(); z++){
        toBeAltered.set(i, tempArray.get(z));
        if (current < mutations-1){
          alterAmtMid(toBeAltered, classification, i+1, mutations, current+1);
        } else if (current == mutations-1){
          alterAmtMid(toBeAltered, classification, i+1, mutations, current+1);
        } else if (current == mutations){
          output(toBeAltered);
        }
      }

      toBeAltered.set(i, temp);
    }
  }

  // public void removeScreen(ArrayList<Character> Tachy_I_Base, ArrayList<Character> Tachy_Vars, char screenTarget){
  //   for (int i = 0; i < Tachy_I_Base.size(); i++){
  //       if (Tachy_I_Base.get(i) == screenTarget){
  //         output(Tachy_Vars, screenTarget);
  //         Tachy_Vars.set(i, screenTarget);
  //       }
  //   }
  // }

  // public void removeAllScreen(ArrayList<Character> Tachy_I_Base, ArrayList<Character> Tachy_Vars){
  //   for (int i = 0; i < Tachy_I_Base.size(); i++){
  //       output(Tachy_Vars);
  //       Tachy_Vars.add(i, Tachy_Vars.remove(i));
  //     }
  // }

  // public void replaceRotateScreen(ArrayList<Character> Tachy_Vars, char allAA[]){
  //   char temp;
  //   for (int x = 0; x < 17; x++){
  //     for (int i = 0; i < 20; i++){
  //       if (Tachy_Vars.get(x) != allAA[i]){
  //         temp = Tachy_Vars.get(x);
  //         Tachy_Vars.set(x, allAA[i]);
  //         output(Tachy_Vars, temp, allAA[i]);
  //         Tachy_Vars.set(x, allAA[temp]);
  //       } 
  //     }
  //   }
  // }

  // public void replaceRotateDouble(ArrayList<Character> Tachy_Vars, char allAA[]){
  //   char temp;
  //   for (int x = 3; x < 14; x++){
  //     for (int i = 0; i < 20; i++){
  //       if (Tachy_Vars.get(x) != allAA[i]){
  //         temp = Tachy_Vars.get(x);
  //         Tachy_Vars.set(x, allAA[i]);
  //         for (int z = x; z < 14; z++){
  //           for (int kl = 0; kl < 20; kl++){
  //             if (Tachy_Vars.get(z) != allAA[kl]){
  //               char temp1 = Tachy_Vars.get(z);
  //               Tachy_Vars.set(z, allAA[kl]);
  //               total++;
  //               System.out.println("Tachy_I_double_" + total);
  //               for (int m = 0; m < Tachy_Vars.size(); m++) {
  //                 System.out.print(Tachy_Vars.get(m));
  //               }
  //               System.out.println();
  //               Tachy_Vars.set(z, allAA[temp1]);
  //             }
              
  //           }
  //         }
  //         Tachy_Vars.set(x, allAA[temp]);
  //       }
        
  //     }
  //   }
  // }

  public void output(ArrayList<Character> array, char... chars) {
    total++;
    String str = "";
    for (char c : chars) {
      str += c;
    }
    System.out.println(">Tachy_I_" + str + total);
    for (int x = 0; x < array.size(); x++) {
      System.out.print(array.get(x));
    }
    System.out.println();
  }

  public boolean find(ArrayList<?> index, Object target){
    for(int i = 0; i < index.size(); i++){
      if (index.get(i) == target){
        return true;
      }
    }
    return false; 
  }
}
