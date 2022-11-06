import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class fileIO {

    public static byte[] readFile(String srcPath) {
        byte[] bs = new byte[0];
        try {
            // 如果第一次使用且读取为目录文件，则需要先创建该文件
            if (srcPath.equals("data/catalog.crypt")) {
                File file = new File(srcPath);
                file.createNewFile();
            }

            InputStream is = Files.newInputStream(Paths.get(srcPath));
            int iAvail = is.available();
            bs = new byte[iAvail];
            is.read(bs);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bs;
    }
}
