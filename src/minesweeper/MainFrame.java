
package minesweeper;

import com.sun.org.apache.bcel.internal.generic.Select;
import controller.GameController;
import entity.GridStatus;
import entity.Player;
import main.Main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;


public class MainFrame extends JFrame {
    public static GameController controller;//创建一个静态字段来监听场上情况，调用GameController里面的方法？//////////
    private int xCount;
    private int yCount;
    private int mineCount;
    private int mineCountcudang;
    private JButton endBtn;
    public JTextField mineCounter;
    public JTextField title;
    public GamePanel gamePanel;
    public JButton cheatBtn;
    public Countdown countdown1;
    public Countdown countdown2;
    public Countdown countdown3;
    public Countdown countdown4;
    private int clickNum = 0;
    private int clickwall=0;
    private ScoreBoard scoreBoard1;
    private ScoreBoard scoreBoard2;
    private ScoreBoard scoreBoard3;
    private ScoreBoard scoreBoard4;
    ArrayList<String>name1234=new ArrayList<>();
    ArrayList<String>namefinal=new ArrayList<>();

    public int getmineCount(){
        return mineCount;
    }

    public int getMineCountcudang() {
        return mineCountcudang;
    }

    public ArrayList<String> getNamefinal(){
        return namefinal;
    }
    public void upDateMine() {
        mineCounter.setText(Integer.toString(gamePanel.getMineRest()));
    }

    public void upDatetitle() {
        title.setText(controller.getOnTurn().getUserName() + " : Hey, It's my turn to perform");
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public int getpplayer(String name) {
        if (name.equals(name1234.get(0))){
            return 1;
        } else if (name.equals(name1234.get(1))) {
            return 2;
        } else if (name.equals(name1234.get(2))) {
            return 3;
        } else if (name.equals(name1234.get(3))) {
            return 4;
        } else {
            return 1;
        }
    }

    public MainFrame(int x, int y, int mine, int[][] chessboard, int[][] stateboard, int mineCount2, String nowturn,
                     ArrayList<String[]> information,ArrayList<String>name) {
        if(name.size()!=0){
        namefinal=name;}
        if(information!=null){
            mineCountcudang=0;
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if(chessboard[i][j]==9){
                        mineCountcudang++;
                    }
                }
            }
       }
        xCount = x;
        yCount = y;
        mineCount = mine;// mine count
        this.setIconImage(new ImageIcon("images/saolei.png").getImage());
        this.setTitle("扫雷大战");//给游戏命名
        this.setLayout(null);//不采用特定的布局方式
        this.setSize(1300, 690);//整个大边框的大小，但+20 +200是为啥///////
        this.setLocationRelativeTo(null);//棋盘出现在屏幕正中间

        Player p1;
        Player p2;
        Player p3;
        Player p4;
        int p = 0;

        if (chessboard != null) {
            gamePanel = new GamePanel(x, y, chessboard, stateboard, mineCount2);
            this.add(gamePanel);
            ArrayList<String> now = new ArrayList<>();
            String[] s1;
            int score1 = 0;
            int mistake1 = 0;
            int count1 = 0;
            String[] s2;
            int score2 = 0;
            int mistake2 = 0;
            int count2 = 0;
            String[] s3;
            int score3 = 0;
            int mistake3 = 0;
            int count3 = 0;
            String[] s4;
            int score4 = 0;
            int mistake4 = 0;
            int count4 = 0;
            for (int i = 0; i < information.size() ; i++) {
                if (i == 0) {
                    now.add(information.get(0)[0]);

                }
                if (i == 1) {
                    s1 = information.get(1);
                    name1234.add(s1[0]);
                    score1 = Integer.parseInt(s1[1]);
                    mistake1 = Integer.parseInt(s1[2]);
                    count1 = Integer.parseInt(s1[3]);
                }
                if (i == 2) {
                    s2 = information.get(2);
                    name1234.add(s2[0]);
                    score2 = Integer.parseInt(s2[1]);
                    mistake2 = Integer.parseInt(s2[2]);
                    count2 = Integer.parseInt(s2[3]);
                }
                if (i == 3) {
                    s3 = information.get(3);
                    name1234.add(s3[0]);
                    score3 = Integer.parseInt(s3[1]);
                    mistake3 = Integer.parseInt(s3[2]);
                    count3 = Integer.parseInt(s3[3]);
                }
                if (i == 4) {
                    s4 = information.get(4);
                    name1234.add(s4[0]);
                    score4 = Integer.parseInt(s4[1]);
                    mistake4 = Integer.parseInt(s4[2]);
                    count4 = Integer.parseInt(s4[3]);
                }
            }
            if(namefinal.size()==0){
                namefinal=name1234;
            }
            p1 = new Player(name1234.get(0), score1, mistake1);
            p2 = new Player(name1234.get(1), score2, mistake2);
            int max1=Math.max(1,name1234.size()-2);
            int max2= name1234.size()-1;
            p3 = new Player(name1234.get(max1), score3, mistake3);
            p4 = new Player(name1234.get(max2), score4, mistake4);

            if (information.size() - 1 == 2&&!information.get(2)[0].equals("")) {
                controller = new GameController(p1, p2, getpplayer(now.get(0)));
                scoreBoard1 = new ScoreBoard(p1, 1, y);//传参给计分板
                scoreBoard2 = new ScoreBoard(p2, 2, y);//传参给计分板
                controller.setScoreBoard2(scoreBoard2);
                controller.setScoreBoard1(scoreBoard1);
                controller.setCounterP1(count1);
                controller.setCounterP2(count2);
                this.add(gamePanel);
                this.add(scoreBoard1);
                this.add(scoreBoard2);
            } else if (information.size() - 2 == 2) {
                controller = new GameController(p1, p2, p3, getpplayer(now.get(0)));
                scoreBoard1 = new ScoreBoard(p1, 1, y);//传参给计分板
                scoreBoard2 = new ScoreBoard(p2, 2, y);//传参给计分板
                scoreBoard3 = new ScoreBoard(p3, 3, y);
                controller.setScoreBoard2(scoreBoard2);
                controller.setScoreBoard1(scoreBoard1);
                controller.setScoreBoard3(scoreBoard3);
                controller.setCounterP1(count1);
                controller.setCounterP2(count2);
                controller.setCounterP3(count3);
                this.add(gamePanel);
                this.add(scoreBoard1);
                this.add(scoreBoard2);
                this.add(scoreBoard3);
            } else if (information.size() - 3 == 2) {
                controller = new GameController(p1, p2, p3, p4, getpplayer(now.get(0)));
                scoreBoard1 = new ScoreBoard(p1, 1, y);//传参给计分板
                scoreBoard2 = new ScoreBoard(p2, 2, y);//传参给计分板
                scoreBoard3 = new ScoreBoard(p3, 3, y);
                scoreBoard4 = new ScoreBoard(p4, 4, y);
                controller.setScoreBoard2(scoreBoard2);
                controller.setScoreBoard1(scoreBoard1);
                controller.setScoreBoard3(scoreBoard3);
                controller.setScoreBoard4(scoreBoard4);
                controller.setCounterP1(count1);
                controller.setCounterP2(count2);
                controller.setCounterP3(count3);
                controller.setCounterP4(count4);
                this.add(gamePanel);
                this.add(scoreBoard1);
                this.add(scoreBoard2);
                this.add(scoreBoard3);
                this.add(scoreBoard4);
            }
            else{ controller = new GameController(p1, p2, xCount, yCount, mineCount, 1);
                ScoreBoard scoreBoard1 = new ScoreBoard(p1, 1, yCount);//传参给计分板
                ScoreBoard scoreBoard2 = new ScoreBoard(p2, 2, yCount);//传参给计分板
                controller.setScoreBoard2(scoreBoard2);
                controller.setScoreBoard1(scoreBoard1);
                this.add(gamePanel);
                this.add(scoreBoard1);
                this.add(scoreBoard2);}
            controller.setGamePanel(gamePanel);
        }


        else {
            gamePanel = new GamePanel(xCount, yCount, mineCount);//传参给游戏面板

            p1 = new Player(name.get(0), 0, 0);
            p2 = new Player(name.get(1), 0, 0);
            int max1=Math.max(1,name.size()-2);
            int max2= name.size()-1;
            p3 = new Player(name.get(max1), 0, 0);
            p4 = new Player(name.get(max2), 0, 0);


            if (name.size() == 2&&!name.get(1).equals("")) {
                controller = new GameController(p1, p2, 1);
                scoreBoard1 = new ScoreBoard(p1, 1, yCount);//传参给计分板
                scoreBoard2 = new ScoreBoard(p2, 2, yCount);//传参给计分板
                controller.setScoreBoard2(scoreBoard2);
                controller.setScoreBoard1(scoreBoard1);
                this.add(gamePanel);
                this.add(scoreBoard1);
                this.add(scoreBoard2);
            } else if (name.size() == 3) {
                controller = new GameController(p1, p2, p3, 1);
                scoreBoard1 = new ScoreBoard(p1, 1, yCount);//传参给计分板
                scoreBoard2 = new ScoreBoard(p2, 2, yCount);//传参给计分板
                scoreBoard3 = new ScoreBoard(p3, 3, yCount);
                controller.setScoreBoard2(scoreBoard2);
                controller.setScoreBoard1(scoreBoard1);
                controller.setScoreBoard3(scoreBoard3);
                this.add(gamePanel);
                this.add(scoreBoard1);
                this.add(scoreBoard2);
                this.add(scoreBoard3);
            } else if (name.size() == 4) {
                controller = new GameController(p1, p2, p3, p4, 1);
                scoreBoard1 = new ScoreBoard(p1, 1, yCount);//传参给计分板
                scoreBoard2 = new ScoreBoard(p2, 2, yCount);//传参给计分板
                scoreBoard3 = new ScoreBoard(p3, 3, yCount);
                scoreBoard4 = new ScoreBoard(p4, 4, yCount);
                controller.setScoreBoard2(scoreBoard2);
                controller.setScoreBoard1(scoreBoard1);
                controller.setScoreBoard3(scoreBoard3);
                controller.setScoreBoard4(scoreBoard4);
                this.add(gamePanel);
                this.add(scoreBoard1);
                this.add(scoreBoard2);
                this.add(scoreBoard3);
                this.add(scoreBoard4);
            } else {
                //人机部分
                controller = new GameController(p1, p2, xCount, yCount, mineCount, 1);
                ScoreBoard scoreBoard1 = new ScoreBoard(p1, 1, yCount);//传参给计分板
                ScoreBoard scoreBoard2 = new ScoreBoard(p2, 2, yCount);//传参给计分板
                controller.setScoreBoard2(scoreBoard2);
                controller.setScoreBoard1(scoreBoard1);
                this.add(gamePanel);
                this.add(scoreBoard1);
                this.add(scoreBoard2);
            }

            controller.setGamePanel(gamePanel);
        }

        //创建层级面板，装P1头像
        JLayeredPane layeredPane1 = new JLayeredPane();
        layeredPane1.setSize(160, 160);
        layeredPane1.setLocation(450 - 14 * yCount, 50);


        JLabel jlpic1 = setlabel1("images/jide3.png", 160, 160, 0, 0);
        layeredPane1.add(jlpic1, 0);

        JLabel jlpic3 = setlabel1("images/jide1.png", 160, 160, 0, 0);
        layeredPane1.add(jlpic3, 1);
        this.getContentPane().add(layeredPane1);

        //创建层级面板，装P2头像
        JLayeredPane layeredPane2 = new JLayeredPane();
        layeredPane2.setSize(160, 160);
        layeredPane2.setLocation(450 - 14 * yCount, 320);

        JLabel jlpic2 = setlabel1("images/kenan21png.png", 160, 160, 0, 0);
        layeredPane2.add(jlpic2, 0);

        JLabel jlpic4 = setlabel1("images/kenan22.jpg", 160, 160, 0, 0);
        layeredPane2.add(jlpic4, 1);
        this.getContentPane().add(layeredPane2);

        //创建层级面板，装P3头像
        JLayeredPane layeredPane3 = new JLayeredPane();
        layeredPane3.setSize(160, 160);
        layeredPane3.setLocation(660 + 14 * yCount, 50);

        JLabel jlpic5 = setlabel1("images/yue2.png", 160, 160, 0, 0);
        layeredPane3.add(jlpic5, 0);

        JLabel jlpic6 = setlabel1("images/yue4.png", 160, 160, 0, 0);
        layeredPane3.add(jlpic6, 1);
        if (information == null) {
            if (name.size() == 3) {
                this.getContentPane().add(layeredPane3);
            }
        } else if (information.size() - 1 == 3) {
            this.getContentPane().add(layeredPane3);
        }


        //创建层级面板，装P4头像
        JLayeredPane layeredPane4 = new JLayeredPane();
        layeredPane4.setSize(160, 160);
        layeredPane4.setLocation(660 + 14 * yCount, 320);

        JLabel jlpic7 = setlabel1("images/jila6.png", 160, 160, 0, 0);
        layeredPane4.add(jlpic7, 0);

        JLabel jlpic8 = setlabel1("images/jila4.png", 160, 160, 0, 0);
        layeredPane4.add(jlpic8, 1);
        if (information == null) {
            if (name.size() == 4) {
                this.getContentPane().add(layeredPane3);
                this.getContentPane().add(layeredPane4);
            }
        } else if (information.size() - 1 == 4) {
            this.getContentPane().add(layeredPane3);
            this.getContentPane().add(layeredPane4);
        }
        JButton againbtn1 = setbtn("重新开始", 100, 30, 5, 5);
        againbtn1.addActionListener(e -> {
            for (int i = 0; i < xCount; i++) {
                for (int j = 0; j < yCount; j++) {
                    gamePanel.getMineField()[i][j].setStatus(GridStatus.Covered);
                    gamePanel.getMineField()[i][j].repaint();
                    gamePanel.getMineField()[i][j].setClickNum(0);
                }
            }
            clickNum = 0;
            if (namefinal.size() == 2) {
                p1.setScore(0);
                p2.setScore(0);
                p1.setMistake(0);
                p2.setMistake(0);
                controller.setCounterP1(0);
                controller.setCounterP2(0);
                controller.setOnTurn(p1);
                scoreBoard1.update(p1);
                scoreBoard2.update(p2);
            } else if (namefinal.size()== 3) {
                p1.setScore(0);
                p2.setScore(0);
                p1.setMistake(0);
                p2.setMistake(0);
                controller.setCounterP1(0);
                controller.setCounterP2(0);
                p3.setScore(0);
                p3.setMistake(0);
                controller.setCounterP3(0);
                controller.setOnTurn(p1);
                scoreBoard1.update(p1);
                scoreBoard2.update(p2);
                scoreBoard3.update(p3);
            } else if (namefinal.size() == 4) {
                p1.setScore(0);
                p2.setScore(0);
                p1.setMistake(0);
                p2.setMistake(0);
                controller.setCounterP1(0);
                controller.setCounterP2(0);
                p3.setScore(0);
                p3.setMistake(0);
                controller.setCounterP3(0);
                p4.setScore(0);
                p4.setMistake(0);
                controller.setCounterP4(0);
                controller.setOnTurn(p1);
                scoreBoard1.update(p1);
                scoreBoard2.update(p2);
                scoreBoard3.update(p3);
                scoreBoard4.update(p4);
            }else if (Login.select.playnum==1 ){
                p1.setScore(0);
                p2.setScore(0);
                p1.setMistake(0);
                p2.setMistake(0);
                controller.setOnTurn(p1);
                scoreBoard1.update(p1);
                scoreBoard2.update(p2);
            }
        });
        this.add(againbtn1);

        JButton againbtn2 = setbtn("新的一局", 100, 30, 115, 5);
        againbtn2.addActionListener(e -> {
            int playm;
            int hasmine=0;
            clickNum=0;
            for (int i = 0; i < xCount; i++) {
                for (int j = 0; j < yCount; j++) {
                    if(gamePanel.getMineField()[i][j].getContent()==9){
                        hasmine++;
                    }
                }
            }
            if (information != null) {
                playm = information.size() - 1;
            } else {
                playm = Login.select.playnum;
            }

            if(name1234.size()!=0){
            Select1 select1 = new Select1(xCount, yCount, hasmine, playm,name1234);}
            else {Select1 select1 = new Select1(xCount, yCount, hasmine, playm,name);}
            this.setVisible(false);
        });

        this.add(againbtn2);

        JButton againbtn3 = setbtn("返回主界面", 120, 30, 220, 5);
        againbtn3.addActionListener(e -> {
            Login.select = new Select1();
            Login.select.setVisible(true);
            this.setVisible(false);
        });
        this.add(againbtn3);

        JPanel jPanel = new JPanel();
        jPanel.setSize(320, 100);
        jPanel.setLocation(950, 560);
        jPanel.setLayout(null);
        jPanel.setOpaque(false);
        this.add(jPanel);

        final JSlider slider = new JSlider(0, 1, 0);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(false);
        slider.setPaintLabels(true);
        slider.setOrientation(SwingConstants.HORIZONTAL);
        slider.setSnapToTicks(true);
        slider.setOpaque(false);
        slider.setSize(120, 50);
        slider.setLocation(140, 0);
        jPanel.add(slider);
        Hashtable<Integer, JComponent> hashtable = new Hashtable<>();
        JLabel open = new JLabel("Music");
        JLabel close = new JLabel("Close");
        open.setFont(new Font("黑体", Font.ITALIC, 16));
        open.setForeground(Color.white);
        close.setFont(new Font("黑体", Font.ITALIC, 16));
        close.setForeground(Color.white);
        hashtable.put(0, close);      //  0  刻度位置，显示 close"
        hashtable.put(1, open);    //  10 刻度位置，显示 "music"
        slider.setLabelTable(hashtable);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int a = slider.getValue();
                if (a == 0) {
                    Music.stop();
                } else {
                    Music.play();
                }
            }
        });

        JButton readBtn = setbtn("读档", 80, 40, 0, 40);
        jPanel.add(readBtn);
        readBtn.addActionListener(e -> {
            Login login = new Login();
            login.setVisible(true);
            this.setVisible(false);

        });
        //设置存档按钮
        JButton fileBtn = setbtn("存档", 80, 40, 80, 40);
        jPanel.add(fileBtn);
        fileBtn.addActionListener(e -> {
            String Docunment = JOptionPane.showInputDialog(Select1.mainFrame, "请输入文件名：", "txt文件");
            controller.writeDataToFile(Docunment);
        });

        //设置作弊按钮
        cheatBtn = setbtn("眼睛", 80, 40, 160, 40);
        jPanel.add(cheatBtn);
        cheatBtn.addActionListener(e -> {
            if (clickNum % 2 == 0) {
                for (int i = 0; i < xCount; i++) {
                    for (int j = 0; j < yCount; j++) {
                        gamePanel.getMineField()[i][j].setStatus(GridStatus.Clicked);
                        gamePanel.getMineField()[i][j].repaint();
                    }
                }
            }
            if (clickNum % 2 == 1) {
                for (int i = 0; i < xCount; i++) {
                    for (int j = 0; j < yCount; j++) {
                        if (gamePanel.getMineField()[i][j].getClickNum() == 0 ) {
                            gamePanel.getMineField()[i][j].setStatus(GridStatus.Covered);
                            gamePanel.getMineField()[i][j].repaint();
                        }if (gamePanel.getMineField()[i][j].isFlagTest()){
                            gamePanel.getMineField()[i][j].setStatus(GridStatus.Flag);
                            gamePanel.getMineField()[i][j].repaint();
                        }
                    }
                }
            }
            clickNum++;
        });

        //设置结束按钮,要满足：至少点击一次棋盘后，点击结束可切换，点击5次新格子后自动切换
        endBtn = setbtn("结束", 80, 40, 240, 40);
        jPanel.add(endBtn);
        endBtn.addActionListener(e -> {
            if ((information == null && name.size() == 2) || (information != null && information.size() - 1 == 2)) {
                if (controller.getOnTurn() == p1 && controller.getCounterP1() != 0) {
                    controller.setOnTurn(p2);
                    System.out.println("Now it is " + controller.getOnTurn().getUserName() + "'s turn.");
                    controller.setCounterP1(0);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                    layeredPane1.setPosition(jlpic1, 1);
                    layeredPane1.setPosition(jlpic3, 0);
                    layeredPane2.setPosition(jlpic4, 0);
                    layeredPane2.setPosition(jlpic2, 1);

                } else if (controller.getOnTurn() == p2 && controller.getCounterP2() != 0) {
                    controller.setOnTurn(p1);
                    System.out.println("Now it is " + controller.getOnTurn().getUserName() + "'s turn.");
                    controller.setCounterP2(0);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                    layeredPane1.setPosition(jlpic1, 0);
                    layeredPane1.setPosition(jlpic3, 1);
                    layeredPane2.setPosition(jlpic4, 1);
                    layeredPane2.setPosition(jlpic2, 0);

                } else {
                    JOptionPane.showMessageDialog(this, "请这位选手至少点击一个格子呦", "名字叫弹窗的弹窗", JOptionPane.WARNING_MESSAGE);
                }
            } else if ((information == null && name.size() == 3) || (information != null && information.size() - 1 == 3)) {
                if (controller.getOnTurn() == p1 && controller.getCounterP1() != 0) {
                    controller.setOnTurn(p2);
                    System.out.println("Now it is " + controller.getOnTurn().getUserName() + "'s turn.");
                    controller.setCounterP1(0);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                    layeredPane1.setPosition(jlpic1, 1);
                    layeredPane1.setPosition(jlpic3, 0);
                    layeredPane2.setPosition(jlpic4, 0);
                    layeredPane2.setPosition(jlpic2, 1);

                } else if (controller.getOnTurn() == p2 && controller.getCounterP2() != 0) {
                    controller.setOnTurn(p3);
                    System.out.println("Now it is " + controller.getOnTurn().getUserName() + "'s turn.");
                    controller.setCounterP2(0);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                    layeredPane3.setPosition(jlpic5, 1);
                    layeredPane3.setPosition(jlpic6, 0);
                    layeredPane2.setPosition(jlpic4, 1);
                    layeredPane2.setPosition(jlpic2, 0);
                } else if (controller.getOnTurn() == p3 && controller.getCounterP3() != 0) {
                    controller.setOnTurn(p1);
                    System.out.println("Now it is " + controller.getOnTurn().getUserName() + "'s turn.");
                    controller.setCounterP3(0);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                    layeredPane3.setPosition(jlpic5, 0);
                    layeredPane3.setPosition(jlpic6, 1);
                    layeredPane1.setPosition(jlpic2, 1);
                    layeredPane1.setPosition(jlpic1, 0);
                } else {
                    JOptionPane.showMessageDialog(this, "请这位选手至少点击一个格子呦", "名字叫弹窗的弹窗", JOptionPane.WARNING_MESSAGE);
                }
            } else if ((information == null && name.size() == 4) || (information != null && information.size() - 1 == 4)) {
                if (controller.getOnTurn() == p1 && controller.getCounterP1() != 0) {
                    controller.setOnTurn(p2);
                    System.out.println("Now it is " + controller.getOnTurn().getUserName() + "'s turn.");
                    controller.setCounterP1(0);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                    layeredPane1.setPosition(jlpic1, 1);
                    layeredPane1.setPosition(jlpic3, 0);
                    layeredPane2.setPosition(jlpic4, 0);
                    layeredPane2.setPosition(jlpic2, 1);

                } else if (controller.getOnTurn() == p2 && controller.getCounterP2() != 0) {
                    controller.setOnTurn(p3);
                    System.out.println("Now it is " + controller.getOnTurn().getUserName() + "'s turn.");
                    controller.setCounterP2(0);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                    layeredPane3.setPosition(jlpic5, 1);
                    layeredPane3.setPosition(jlpic6, 0);
                    layeredPane2.setPosition(jlpic4, 1);
                    layeredPane2.setPosition(jlpic2, 0);
                } else if (controller.getOnTurn() == p3 && controller.getCounterP3() != 0) {
                    controller.setOnTurn(p4);
                    System.out.println("Now it is " + controller.getOnTurn().getUserName() + "'s turn.");
                    controller.setCounterP3(0);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                    layeredPane3.setPosition(jlpic5, 0);
                    layeredPane3.setPosition(jlpic6, 1);
                    layeredPane4.setPosition(jlpic8, 0);
                    layeredPane4.setPosition(jlpic7, 1);
                } else if (controller.getOnTurn() == p4 && controller.getCounterP4() != 0) {
                    controller.setOnTurn(p1);
                    System.out.println("Now it is " + controller.getOnTurn().getUserName() + "'s turn.");
                    controller.setCounterP4(0);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                    layeredPane1.setPosition(jlpic2, 1);
                    layeredPane1.setPosition(jlpic1, 0);
                    layeredPane4.setPosition(jlpic7, 0);
                    layeredPane4.setPosition(jlpic8, 1);
                } else {
                    JOptionPane.showMessageDialog(this, "请这位选手至少点击一个格子呦", "名字叫弹窗的弹窗", JOptionPane.WARNING_MESSAGE);
                }
            }//人机
            if (Login.select.playnum == 1) {
                if (controller.getOnTurn() == p1 && controller.getCounterP1() != 0) {
                    controller.setOnTurn(p2);
                    System.out.println("Now it is " + controller.getOnTurn().getUserName() + "'s turn.");
                    controller.setCounterP1(0);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                    layeredPane1.setPosition(jlpic1, 1);
                    layeredPane1.setPosition(jlpic3, 0);
                    layeredPane2.setPosition(jlpic4, 0);
                    layeredPane2.setPosition(jlpic2, 1);
                }
            }
        });
        //添加地雷计数器
        JPanel jPanelcounter = setpanel(300, 50, 0, 530);

        String text1 = "剩余雷数";
        JLabel label = setlabel2(text1, 100, 30, 0, 0);

        mineCounter = new JTextField(Integer.toString(gamePanel.getMineRest()));//?加一个更新文本的方法，在点开格子的时候更新
        mineCounter.setLocation(80, 0);
        mineCounter.setSize(30, 30);
        mineCounter.setOpaque(false);
        mineCounter.setFont(new Font("黑体", Font.ITALIC, 16));
        mineCounter.setForeground(Color.white);
        mineCounter.setEditable(false);
        mineCounter.setVisible(true);

        JLabel labeltime = setlabel2("游戏计时器", 100, 30, 160, 0);
        jPanelcounter.add(labeltime);
        jPanelcounter.add(label);
        jPanelcounter.add(mineCounter);
        this.getContentPane().add(jPanelcounter);

        countdown1 = setcountdonw(120, 100, 0, 550);
        this.add(countdown1);

        countdown2 = setcountdonw(120, 100, 125, 550);
        this.add(countdown2);
        if ((information == null && namefinal.size()>=3)|| (information != null && information.size() - 1 >= 3)) {
            countdown3 = setcountdonw(120, 100, 250, 550);
            this.add(countdown3);
            if ((information == null && namefinal.size()==4) || (information != null && information.size() - 1 == 4)) {
                countdown4 = setcountdonw(120, 100, 375, 550);
                this.add(countdown4);
            }
        }


        title = new JTextField(controller.getOnTurn().getUserName() + " : Hey, It's my turn to perform");
        title.setSize(450, 40);
        title.setLocation(425, 0);
        title.setEditable(false);
        title.setBackground(Color.white);
        title.setFont(new Font("黑体", Font.ITALIC, 24));
        title.setForeground(Color.white);
        title.setOpaque(false);
        this.add(title);



        JLayeredPane backpanel = new JLayeredPane();
        backpanel.setSize(1300,650);
        backpanel.setLocation(0,0);
        JLabel background1 = setlabel1("images/wallhaven14.jpg", 1300, 650, 0, 0);
        backpanel.add(background1,1);
        JLabel background2 = setlabel1("images/wallhaven4.png", 1300, 650, 0, 0);
        backpanel.add(background2,0);
        JButton wallBtn = setbtn("壁纸", 80, 40, 864,600 );
        wallBtn.addActionListener(e -> {
            if(clickwall%2==0){
                backpanel.setPosition(background1,0);
                backpanel.setPosition(background2,1);
            }
            else{backpanel.setPosition(background1,1);
                backpanel.setPosition(background2,0);}
            clickwall++;
        });
        this.add(wallBtn);

        this.getContentPane().add(backpanel);



        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private static String showInputDialog(MainFrame owner, MainFrame parentComponent) {
        final JDialog dialog = new JDialog(owner, "存档", true);
        // 设置对话框的宽高
        dialog.setSize(350, 180);
        // 设置对话框大小不可改变
        dialog.setResizable(false);
        // 设置对话框相对显示的位置
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        final JTextField textField = new JTextField(8);
        textField.setFont(new Font(null, Font.ITALIC, 20));
        dialog.add(textField);
        dialog.setVisible(true);
        return textField.getText();
    }

    public JButton setbtn(String name, int width, int hight, int x, int y) {
        JButton Btn = new JButton(name);
        Btn.setFocusable(false);
        Btn.setSize(width, hight);
        Btn.setLocation(x, y);
        Btn.setBackground(Color.white);
        Btn.setFont(new Font("黑体", Font.ITALIC, 16));
        Btn.setForeground(Color.white);
        Btn.setContentAreaFilled(false);
        return Btn;
    }

    public JLabel setlabel1(String road, int width, int hight, int x, int y) {
        JLabel label = new JLabel();
        label.setSize(width, hight);
        label.setLocation(x, y);
        ImageIcon icon = new ImageIcon(road);
        label.setIcon(icon);
        return label;
    }

    public JLabel setlabel2(String name, int width, int hight, int x, int y) {
        JLabel label = new JLabel(name);
        label.setSize(width, hight);
        label.setLocation(x, y);
        label.setBackground(Color.white);
        label.setFont(new Font("黑体", Font.ITALIC, 16));
        label.setForeground(Color.white);
        label.setOpaque(false);
        return label;
    }

    public JPanel setpanel(int width, int hight, int x, int y) {
        JPanel jPanel = new JPanel();
        jPanel.setOpaque(false);
        jPanel.setLocation(x, y);
        jPanel.setSize(width, hight);
        jPanel.setLayout(null);
        return jPanel;
    }

    public Countdown setcountdonw(int width, int hight, int x, int y) {
        Countdown countdown = new Countdown();
        countdown.setOpaque(false);
        countdown.setLocation(x, y);
        countdown.setSize(width, hight);
        countdown.initUI();
        countdown.jpanelNorth.setOpaque(false);
        countdown.jpanelCenter.setOpaque(false);
        return countdown;
    }

    public int getXCount() {
        return xCount;
    }

    public int getYCount() {
        return yCount;
    }
}
