package minesweeper;

import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Login extends JFrame implements ActionListener {
    public static Select1 select;
    JButton button = new JButton("开始游戏");
    JButton button1 = new JButton("读取存档");
    JButton button2=new JButton("扫雷高玩榜");
    String text = "<html> Welcome to my <br/>   Minesweeper!<br/></html>";
    JLabel jLabel = new JLabel(text);
    JLabel jlpic = new JLabel();
    JPanel jPanel = new JPanel();

    public Login() {
        button.setFocusable(false);        //去除按钮获得焦点时，文字周围的虚线
        button.setBackground(Color.white);
        setLayout(null);
        setVisible(true);
        button.setSize(170, 60);
        button.setLocation(300, 360);
        button.setContentAreaFilled(false);
        button.setFont(new Font("黑体", Font.ITALIC, 18));
        button.setForeground(Color.white);
        button.addActionListener(e -> {
            this.setVisible(false);
            select = new Select1();
            select.setVisible(true);
        });
        this.add(button);

        button1.setFocusable(false);        //去除按钮获得焦点时，文字周围的虚线
        button1.setBackground(Color.white);
        setLayout(null);
        setVisible(true);
        button1.setSize(170, 60);
        button1.setLocation(300, 280);
        button1.setContentAreaFilled(false);
        button1.setFont(new Font("黑体", Font.ITALIC, 18));
        button1.setForeground(Color.white);
        button1.addActionListener(this::actionPerformed2);
        this.add(button1);

        button2.setFocusable(false);        //去除按钮获得焦点时，文字周围的虚线
        button2.setBackground(Color.white);
        setLayout(null);
        setVisible(true);
        button2.setSize(170, 60);
        button2.setLocation(300, 200);
        button2.setContentAreaFilled(false);
        button2.setFont(new Font("黑体", Font.ITALIC, 18));
        button2.setForeground(Color.white);
        button2.addActionListener(this::actionPerformed3);
        this.add(button2);

        jLabel.setSize(340, 120);
        jLabel.setLocation(270, 50);
        jLabel.setFont(new Font("楷体", Font.ITALIC, 36));
        jLabel.setForeground(Color.white);
        this.add(jLabel);

        this.setIconImage(new ImageIcon("images/saolei.png").getImage());
        ImageIcon icon = new ImageIcon("images/login1.jpg");
        jPanel.setSize(766, 522);
        jlpic.setIcon(icon);
        jPanel.add(jlpic);
        this.getContentPane().add(jPanel);


        this.setTitle("我的扫雷");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(766, 522);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void actionPerformed3(ActionEvent e) {
        List list=new List();
        list.setVisible(true);
    }

    public Select1 getSelect() {
        return select;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public ArrayList<int[][]> readFileData(String fileName, int x, int y, int playnum, int nowmine, String nowturn,ArrayList<String[]>info) {
        //todo: read date from file
        ArrayList<int[][]>chess=new ArrayList<>();
        try (FileReader reader = new FileReader(fileName);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            int k = 0;
            String nowtrun = "";
            int counter = 0;
            //网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                if (counter == 0) {
                    int[][]a=new int[2][2];
                    a[0][0]=Integer.parseInt(line);
                    chess.add(a);
                    counter++;
                }
                else if (counter == 1) {
                    int[][]a=new int[2][2];
                    a[0][0]=Integer.parseInt(line);
                    chess.add(a);
                    counter++;
                } else if (counter == 2) {
                    int[][]a=new int[2][2];
                    a[0][0]=Integer.parseInt(line);
                    chess.add(a);
                    counter++;
                    k = 3 * a[0][0] - 2;
                } else if (counter == 3) {
                    x=chess.get(1)[0][0];
                    y=chess.get(2)[0][0];
                    int[][] board = new int[x][y];
                    for (int i = 0; i < x ; i++) {
                        String aline1 = line.substring(2 + 4 * i + k * i, 2 + 4 * i + k * (i + 1));
                        String[] tokens1 = aline1.split(", ");
                        for (int j = 0; j < y; j++) {
                            board[i][j] = Integer.parseInt(tokens1[j]);
                        }
                    }
                    chess.add(board);
                    counter++;
                } else if (counter == 4) {
                    int[][] state = new int[x][y];
                    for (int i = 0; i < x ; i++) {
                        String aline2 = line.substring(2 + 4 * i + k * i, 2 + 4 * i + k * (i + 1));
                        String[] tokens2 = aline2.split(", ");
                        for (int j = 0; j < y; j++) {
                            state[i][j] = Integer.parseInt(tokens2[j]);
                        }
                    }
                    chess.add(state);
                    counter++;
                } else if (counter == 5) {
                    int[][]a=new int[2][2];
                    a[0][0]=Integer.parseInt(line);
                    chess.add(a);
                    counter++;
                } else if (counter == 6) {
                    String[]a=new String[1];
                    a[0]=line;
                   info.add(a);
                    counter++;
                } else if (counter == 7) {
                    String[] tokens3 = line.split(" ");
                    info.add(tokens3);
                    counter++;
                } else if (counter == 8) {
                    String[] tokens4 = line.split(" ");
                    info.add(tokens4);
                    counter++;
                } else if (counter == 9) {
                    String[] tokens5 = line.split(" ");
                    info.add(tokens5);
                    counter++;
                } else if (counter == 10) {
                    String[] tokens6 = line.split(" ");
                    info.add(tokens6);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }return chess;
    }

    private void actionPerformed2(ActionEvent e) {
        int x = 0;
        int y = 0;
        int playnum = 0;
        int nowmine = 0;
        int[][] board;
        int[][] state;
        String nowturn = "";
        ArrayList<String[]> info = new ArrayList<>();
        String readDocunment=JOptionPane.showInputDialog(Main.login,"请输入存档文件名：","txt文件");
        ArrayList<int[][]> chess = readFileData(readDocunment, x, y, playnum, nowmine, nowturn, info);
        playnum = chess.get(0)[0][0];
        x = chess.get(1)[0][0];
        y = chess.get(2)[0][0];
        board = chess.get(3);
        state = chess.get(4);
        nowmine = chess.get(5)[0][0];
        select = new Select1(x, y, 0, board, state, nowmine, nowturn, info);
        select.setVisible(false);
        this.setVisible(false);
    }
}
