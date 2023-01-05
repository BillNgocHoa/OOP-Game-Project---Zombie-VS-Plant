import java.io.*;
import java.util.logging.Logger;

public class DataLevel {
    public static String lvl = "1";
    public static String[][] Level = { { "NormalZombie" }, { "NormalZombie", "ConeHeadZombie" } };
    public static int[][][] LevelValue = { { { 0, 99 } }, { { 0, 49 }, { 50, 99 } } };

    public DataLevel() {
        try {
            File file = new File("Level.vbhv");

            if (!file.exists()) {
                BufferedWriter bwr = new BufferedWriter(new FileWriter(file));
                bwr.write("1");
                bwr.close();
                lvl = "1";
            } else {
                BufferedReader br = new BufferedReader(new FileReader(file));
                lvl = br.readLine();
                br.close();
            }
        } catch (Exception ex) {

        }
    }

    public static void write(String level) {
        File file = new File("Level.vbhv");
        try {
            BufferedWriter bwr = new BufferedWriter(new FileWriter(file));
            bwr.write(level);
            bwr.close();
            lvl = level;
        } catch (IOException ex) {
            Logger.getLogger(DataLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }
}
