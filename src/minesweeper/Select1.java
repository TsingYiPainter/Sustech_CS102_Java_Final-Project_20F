package minesweeper;

import com.sun.org.apache.bcel.internal.generic.Select;
import minesweeper.MainFrame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class Select1 extends JFrame {
    public static MainFrame mainFrame;
    public int playnum=2;
    JLabel jLabel=new JLabel("My Minesweeper Area");
    JButton btn1 = new JButton("初级9X9");
    JButton btn2 = new JButton("中级16X16");
    JButton btn3 = new JButton("高级16X30");
    JButton btn4 = new JButton("自定义棋盘");
    JLabel jlpic=new JLabel();
    JPanel jPanel=new JPanel();
    ArrayList<String> playname=new ArrayList<>();

    public Select1(int x, int y, int mine, int[][]board, int[][]state, int nowmine, String nowturn, ArrayList<String[]>info){
        mainFrame=new MainFrame(x,y,0,board,state,nowmine,nowturn,info,playname);
        mainFrame.setVisible(true);
    }

    public Select1(int x,int y,int mine,int playnum,ArrayList<String>name1234){
        this.playnum=Math.max(playnum,name1234.size());

        mainFrame=new MainFrame(x,y,mine,null,null,100000,null,null,name1234);
        mainFrame.setVisible(true);
        this.setVisible(false);
    }

    public Select1() {

        this.setTitle("游戏模式");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 535);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        jLabel.setSize(400,120);
        jLabel.setLocation(200,60);
        jLabel.setFont(new Font("楷体", Font.ITALIC,36));
        jLabel.setForeground(Color.white);
        this.add(jLabel);

        btn1.setFocusable(false);
        btn1.setContentAreaFilled(false);
        btn1.setFont(new Font("黑体", Font.ITALIC,18));
        btn1.setForeground(Color.white);
        btn1.setSize(150, 80);
        btn1.setLocation(10, 400);
        btn1.addActionListener(e -> {
             playname=showCustomDialogplay(this,this);
            this.setVisible(false);
            mainFrame = new MainFrame(9,9,10,null,null,100000,null,null,playname);
            mainFrame.setVisible(true);
        });
        this.add(btn1);

        btn2.setFocusable(false);
        btn2.setContentAreaFilled(false);
        btn2.setFont(new Font("黑体", Font.ITALIC,18));
        btn2.setForeground(Color.white);
        btn2.setSize(150, 80);
        btn2.setLocation(180, 400);
        btn2.addActionListener(e -> {
            ArrayList<String> playname=showCustomDialogplay(this,this);
            this.setVisible(false);
             mainFrame = new MainFrame(16,16,40,null,null,100000,null,null,playname);
            mainFrame.setVisible(true);
        });
        this.add(btn2);

        btn3.setFocusable(false);
        btn3.setContentAreaFilled(false);
        btn3.setFont(new Font("黑体", Font.ITALIC,18));
        btn3.setForeground(Color.white);
        btn3.setSize(150, 80);
        btn3.setLocation(350, 400);
        btn3.addActionListener(e -> {
            ArrayList<String> playname=showCustomDialogplay(this,this);
            this.setVisible(false);
            mainFrame = new MainFrame(16,30,99,null,null,100000,null,null,playname);
            mainFrame.setVisible(true);
        });
        this.add(btn3);

        btn4.setFocusable(false);
        btn4.setContentAreaFilled(false);
        btn4.setFont(new Font("黑体", Font.ITALIC,18));
        btn4.setForeground(Color.white);
        btn4.setSize(150, 80);
        btn4.setLocation(520, 400);
        btn4.addActionListener(e -> {
            ArrayList<String> playname=showCustomDialogplay(this,this);
            String size=showCustomDialog(this,this);
            String[] tokens=size.split(" ");
            if (tokens.length!=3) {
                JOptionPane.showMessageDialog(this, "您输入的数字不合规哎", "弹窗2号",JOptionPane.WARNING_MESSAGE);
            }else {
                int row=Integer.parseInt(tokens[0]);
                int column=Integer.parseInt(tokens[1]);
                int mine=Integer.parseInt(tokens[2]);
                if (row*column<=720 && mine<=(row*column)/2){
                    this.setVisible(false);
                    mainFrame = new MainFrame(row,column,mine,null,null,100000,null,null,playname);
                    mainFrame.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(this, "您输入的数字不合规哎", "弹窗2号",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        this.add(btn4);


        JTextArea textArea = new JTextArea("本游戏作为一款乞丐版扫雷，是某两个计算机小白的第一个project，若有不合理之处，请多包含。" +
                "下面是一些游戏需要注意的地方。玩家须知:当开始游戏时，玩家须点击计时器传入限制时间,回合开始时点击开始，结束时点击暂停，若时间用完，以后每回合" +
                "只有30秒时间。当玩家结束回合时，须手动点击结束回合按钮。为防止程序崩溃请不要恶意输入非法数据");
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setLineWrap(true);                         // 自动换行
        textArea.setFont(new Font("黑体", Font.PLAIN, 15));   // 设置字体

        // 创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条一直显示, 水平滚动条从不显示
        JScrollPane scrollPane = new JScrollPane(
                textArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.setForeground(Color.white);
        scrollPane.setLocation(580,10);
        scrollPane.setSize(100,150);
        this.add(scrollPane);

        JLabel label = new JLabel("玩家设置");
        label.setSize(100,60);
        label.setLocation(20,300);
        label.setFont(new Font("黑体",Font.ITALIC,18));
        label.setForeground(Color.white);
        this.add(label);

        // 需要选择的条目
        String[] listData = new String[]{"双人对战", "三人对战", "四人对战", "人机对战"};
        // 创建一个下拉列表框
        final JComboBox<String> comboBox = new JComboBox<String>(listData);
        // 添加条目选中状态改变的监听器
        comboBox.setSelectedIndex(0);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // 只处理选中的状态
                int a=comboBox.getSelectedIndex();
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if(a==0){playnum=2;}
                    else if(a==1){playnum=3;}
                    else if(a==2){playnum=4;}
                    else if(a==3){playnum=1;}
                }
            }
        });

        comboBox.setLocation(110,315);
        comboBox.setSize(100,30);
        comboBox.setOpaque(false);
        comboBox.setFont(new Font("黑体",Font.ITALIC,18));
        comboBox.setForeground(Color.black);
        this.add(comboBox);


        this.setIconImage(new ImageIcon("images/saolei.png").getImage());
        ImageIcon icon = new ImageIcon("images/select4.jpg");
        jPanel.setSize(700,535);
        jlpic.setIcon(icon);
        jPanel.add(jlpic);
        this.getContentPane().add(jPanel);

    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
    private static String showCustomDialog(Select1 owner, Select1 parentComponent) {
        // 创建一个模态对话框
        final JDialog dialog = new JDialog(owner, "自定义模式", true);
        // 设置对话框的宽高
        dialog.setSize(350, 250);
        // 设置对话框大小不可改变
        dialog.setResizable(false);
        // 设置对话框相对显示的位置
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        JPanel panel01 = new JPanel();
        panel01.add(new JLabel("X count"));
        final JTextField textField1 = new JTextField(8);
        textField1.setFont(new Font(null, Font.ITALIC , 20));
        panel01.add(textField1);

        JPanel panel02 = new JPanel();
        panel02.add(new JLabel("Y count"));
        final JTextField textField2 = new JTextField(8);
        textField2.setFont(new Font(null, Font.ITALIC , 20));
        panel02.add(textField2);

        JPanel panel03 = new JPanel();
        panel03.add(new JLabel("M count"));
        final JTextField textField3 = new JTextField(8);
        textField3.setFont(new Font(null, Font.ITALIC , 20));
        panel03.add(textField3);

        JPanel panel04 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button=new JButton("我选好了");
        button.setBackground(Color.white);
        button.addActionListener(e -> {
            dialog.setVisible(false);
        });
        panel04.add(button);

        Box vBox = Box.createVerticalBox();
        vBox.add(panel01);
        vBox.add(panel02);
        vBox.add(panel03);
        vBox.add(panel04);

        dialog.setContentPane(vBox);
        dialog.pack();
        dialog.setVisible(true);
        String strx=textField1.getText();
        String stry=textField2.getText();
        String strmine=textField3.getText();
        return strx+" "+stry+" "+strmine;
}

    private static ArrayList<String> showCustomDialogplay(Select1 owner, Select1 parentComponent) {
        // 创建一个模态对话框
        final JDialog dialog = new JDialog(owner, "玩家昵称", true);
        // 设置对话框的宽高
        dialog.setSize(350, 80* owner.playnum);
        // 设置对话框大小不可改变
        dialog.setResizable(false);
        // 设置对话框相对显示的位置
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        JPanel panel01 = new JPanel();
        panel01.add(new JLabel("1号玩家"));
        final JTextField textField1 = new JTextField(8);
        textField1.setFont(new Font(null, Font.ITALIC , 20));
        panel01.add(textField1);

        JPanel panel02 = new JPanel();
        final JTextField textField2 = new JTextField(8);
        if(owner.playnum!=1)
        { panel02.add(new JLabel("2号玩家"));
            textField2.setFont(new Font(null, Font.ITALIC , 20));
            panel02.add(textField2);}

        JPanel panel03 = new JPanel();
        final JTextField textField3 = new JTextField(8);
        JPanel panel04=new JPanel();
        final JTextField textField4 = new JTextField(8);
        if(owner.playnum>=3){
        panel03.add(new JLabel("3号玩家"));
        textField3.setFont(new Font(null, Font.ITALIC , 20));
        panel03.add(textField3);
        if(owner.playnum==4){
            panel04.add(new JLabel("4号玩家"));
            textField4.setFont(new Font(null, Font.ITALIC , 20));
            panel04.add(textField4);
        }
        }

        JPanel panel05 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button=new JButton("我选好了");
        button.setBackground(Color.white);
        button.addActionListener(e -> {
            dialog.setVisible(false);
        });
        panel05.add(button);

        Box vBox = Box.createVerticalBox();
        vBox.add(panel01);
        vBox.add(panel02);
        if(owner.playnum>=3){
        vBox.add(panel03);
        if(owner.playnum==4){
        vBox.add(panel04);}}
        vBox.add(panel05);

        dialog.setContentPane(vBox);
        dialog.pack();
        dialog.setVisible(true);
        ArrayList<String> name=new ArrayList<>();
        String str1=textField1.getText();
        String str2=textField2.getText();
        name.add(str1);
        name.add(str2);
        if(owner.playnum>=3){
        String str3=textField3.getText();
        name.add(str3);
        if(owner.playnum==4){
            String str4=textField4.getText();
            name.add(str4);
        }}
        return name;
    }
}

