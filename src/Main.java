import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        new mainUI().setVisible(true);
    }

    public static String calc(String path) {
        return md5CalcUtil.md5Calc(fileIO.readFile(path));
    }
}