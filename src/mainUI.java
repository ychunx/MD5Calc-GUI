import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

public class mainUI extends JFrame {

    public mainUI() {

        super("简易MD5计算器");

        int WIDTH = 500;
        int HEIGHT = 350;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((int)(screenSize.getWidth()-WIDTH)/2,(int)(screenSize.getHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
    }

    public void init() {

        JPanel JP = new JPanel();
        JP.setLayout(null);
        JP.add(setPage());
        this.add(JP);
    }

    public void close() {
        this.dispose();
    }

    public JPanel setPage() {

        JPanel jp = new JPanel();
        jp.setBounds(0,0,500,350);
        jp.setLayout(null);

        JTextArea srcPath = new JTextArea();
        srcPath.setText("\n\n\n       拖拽文件到此区域计算MD5");
        srcPath.setLineWrap(true);
        srcPath.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        srcPath.setEditable(false);
        srcPath.setBounds(0,0,500,350);
        // 文本域拖拽文件功能
        new DropTarget(srcPath, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter()
        {
            @Override
            public void drop(DropTargetDropEvent dtde)
            {
                try
                {
                    if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))//如果拖入的文件格式受支持
                    {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//接收拖拽来的数据
                        srcPath.setFont(new Font("微软雅黑", Font.PLAIN, 18));
                        String tempPath = dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor).toString();
                        srcPath.setText(tempPath.substring(1, tempPath.length()-1));
                        dtde.dropComplete(true);//指示拖拽操作已完成
                        srcPath.setText(Main.calc(srcPath.getText()));
                    }
                    else
                    {
                        dtde.rejectDrop();//否则拒绝拖拽来的数据
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        jp.add(srcPath);

        return jp;
    }
}