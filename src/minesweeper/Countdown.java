package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Countdown extends JPanel {
    private static final long serialVersionUID = 1L;
    JPanel Mainpan=new JPanel();
    JLabel jlabel;//显示时间的标签
    int time;//输入框接收的时间，格式为纯数字或者00:00的形式
    int m;//分针
    int s;//秒针
    JButton btn;//开始按钮
    JButton Reset;//重置按钮
    JButton shop;//暂停按钮
    JButton keepOn;//继续按钮
    JTextField txtField;//输入框，用于接收输入数据
    JPanel jpanelNorth;//窗口上部分面板
    JPanel jpanelCenter;//窗口下部分面板


    Countdown.TimerThread timerThread = new Countdown.TimerThread();//执行的线程程序部分
    Thread th;//线程对象

    public void initUI(){
        // 设置窗口布局
        jpanelNorth = new JPanel();
        jpanelCenter = new JPanel();

        this.setSize(120, 100);
        this.setLayout(new FlowLayout()); //设置布局方式为边框布局

        // 初始化面板

        // 设置输入框标签
        txtField = new JTextField(10);
        txtField.setFont(new Font("黑体",Font.ITALIC, 16));
        txtField.setForeground(Color.gray);
        txtField.setHorizontalAlignment(JTextField.CENTER);
        jpanelNorth.add(txtField);

        // 设置开始按钮
        Dimension preferredSize=new Dimension(80, 25);    //设置尺寸
        btn=new JButton("开始");
        btn.setFont(new Font("黑体",Font.ITALIC,16));
        btn.setForeground(Color.black);
        btn.setBackground(Color.white);
        btn.setPreferredSize(preferredSize);
        btn.addActionListener(new Countdown.BtnActionListener());//添加监听
        jpanelCenter.add(btn);
        // 运行时显示的画面
        // 显示时间
        jlabel = new JLabel();
        jlabel.setFont(new Font("黑体",Font.ITALIC, 25));
        jlabel.setForeground(Color.white);
        jlabel.setOpaque(false);

        Reset=new JButton("重置");
        Reset.setFont(new Font("黑体",Font.ITALIC,16));
        Reset.setForeground(Color.black);
        Reset.setBackground(Color.white);
        Reset.addActionListener(new Countdown.BtnActionListener2());

        //设置暂停按钮
        shop = new JButton("暂停");
        shop.setFont(new Font("黑体",Font.ITALIC,16));
        shop.setForeground(Color.black);
        shop.setBackground(Color.white);
        shop.addActionListener(new Countdown.ShopActionListener());

        //设置继续按钮
        keepOn = new JButton("继续");
        keepOn.setFont(new Font("黑体",Font.ITALIC,16));
        keepOn.setForeground(Color.black);
        keepOn.setBackground(Color.white);
        keepOn.addActionListener(new Countdown.KeepOnActionListener());

        this.add(jpanelNorth, BorderLayout.NORTH); //把面板放到Frame上方
        this.add(jpanelCenter, BorderLayout.CENTER); //把面板放到Frame中间

        this.setVisible(true);//窗口设为可见

    }
    /**
     * 功能：线程执行的程序
     * @author 荆棘
     *
     */
    class TimerThread implements Runnable{
        @Override
        public void run() {
            while (true) {
                // 判断这一分钟是否到末尾，若到末尾则分针减1，秒针回到59
                if(s<0 && m>0){
                    s=59;
                    m--;
                }
                // 规定时间显示的格式
                DecimalFormat f1 = new DecimalFormat("00");
                jlabel.setText(f1.format(m)+":"+f1.format(s));

                // 判断时间是否走完，若走完则删除暂停按钮
                if(s == 0 && m == 0){
                    jlabel.setText("00:00");
                    jpanelCenter.remove(shop);
                    jpanelCenter.remove(keepOn);
                    jpanelCenter.updateUI();
                    btn.setEnabled(true);
                    return;
                }
                //线程休眠一秒，秒针-1
                try {
                    Thread.sleep(1000);
                    s--;
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
    /**
     * 开始按钮单击事件的匿名内部类
     */
   class BtnActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //将开始按钮设为不可用
            btn.setEnabled(false);
            //删除原面板
            jpanelNorth.remove(txtField);
            jpanelCenter.remove(btn);

            //把标签放到面板中
            jpanelNorth.add(jlabel);
            jpanelCenter.add(Reset);
            jpanelCenter.add(shop);
            //刷新面板
            jpanelNorth.updateUI();
            jpanelCenter.updateUI();

            //接收输入框传入的字符
            String str = txtField.getText();
            //System.out.println(str);
            //解析字符
            Pattern p1 = Pattern.compile("^(\\d{1,2}):(\\d{1,2})$");
            Matcher m1 = p1.matcher(str);
            Pattern p2 = Pattern.compile("^\\d+");
            Matcher m2 = p2.matcher(str);

            /*
             * 若输入的是00:00格式的字符，则匹配第一个，m为分针，s为秒针
             * 若输入的是纯数字格式的字符，则默认为输入多少秒，计算出分针和秒针的位置
             */
            if(m1.find()){
                m = Integer.parseInt(m1.group(1));
                s = Integer.parseInt(m1.group(2));
            }else if(m2.find()){
                time = Integer.parseInt(m2.group());
                m = time/60;
                s = time%60;
            }else{
                jlabel.setText("输入错误");
                return;
            }

            //开启一个新线程并执行
            th = new Thread(timerThread);
            th.start();
        }
    }

    /**
     * 功能：重置按钮的功能
     * @author 荆棘
     *
     */
    class BtnActionListener2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // 终止线程
            th.interrupt();
            timerThread = new Countdown.TimerThread();
            // 删除面板中的时间显示、重置按钮、暂停按钮、继续按钮
            jpanelNorth.remove(jlabel);
            jpanelCenter.remove(Reset);
            jpanelCenter.remove(shop);
            jpanelCenter.remove(keepOn);

            // 添加开始按钮和输入框
            jpanelNorth.add(txtField);
            jpanelCenter.add(btn);
            btn.setEnabled(true);

            // 刷新面板
            jpanelNorth.updateUI();
            jpanelCenter.updateUI();
        }
    }

    /**
     * 功能：暂停按钮
     * @author 荆棘
     *
     */
    class ShopActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // 终止线程
            th.interrupt();
            // 将暂停按钮变成继续按钮
            jpanelCenter.remove(shop);
            jpanelCenter.add(keepOn);
            jpanelCenter.updateUI();
        }

    }

    /**
     * 功能：继续按钮
     * @author 荆棘
     *
     */
    class KeepOnActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // 继续线程
            th = new Thread(timerThread);
            th.start();
            // 讲继续按钮变成暂停按钮
            jpanelCenter.remove(keepOn);
            jpanelCenter.add(shop);
            jpanelCenter.updateUI();
        }

    }

    public int getM() {
        return m;
    }

    public int getS() {
        return s;
    }
}

