package controller;

import components.GridComponent;
import entity.GridStatus;
import minesweeper.*;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Collection;

import main.Main;
import minesweeper.Select1;
import minesweeper.MainFrame;


public class GameController {

    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;
    private int counterP1 = 0;
    private int counterP2 = 0;
    private int counterP3 = 0;
    private int counterP4 = 0;
    private int xCount;
    private int yCount;
    private int mineCount;

    public void setCounterP1(int counterP1) {
        this.counterP1 = counterP1;
    }

    public void setCounterP2(int counterP2) {
        this.counterP2 = counterP2;
    }

    public void setCounterP3(int counterP3) {
        this.counterP3 = counterP3;
    }

    public void setCounterP4(int counterP4) {
        this.counterP4 = counterP4;
    }

    public void setOnTurn(Player onTurn) {
        this.onTurn = onTurn;
    }

    public int getCounterP1() {
        return counterP1;
    }

    public int getCounterP2() {
        return counterP2;
    }

    public int getCounterP3() {
        return counterP3;
    }

    public int getCounterP4() {
        return counterP4;
    }

    public Player getOnTurn() {
        return onTurn;
    }

    private Player onTurn;

    private GamePanel gamePanel;
    private ScoreBoard scoreBoard1;
    private ScoreBoard scoreBoard2;
    private ScoreBoard scoreBoard3;
    private ScoreBoard scoreBoard4;

    public GameController(Player p1, Player p2, int p) {
        this.init(p1, p2, p);
    }

    public GameController(Player p1, Player p2, Player p3, int p) {
        this.init(p1, p2, p3, p);
    }

    public GameController(Player p1, Player p2, Player p3, Player p4, int p) {
        this.init(p1, p2, p3, p4, p);
    }

    public GameController(Player p1, Player p2, int xCount, int yCount, int mineCount, int p) {
        this.init(p1, p2, p);
        this.onTurn = p1;
        this.xCount = xCount;
        this.yCount = yCount;
        this.mineCount = mineCount;
    }

    /**
     * 初始化游戏。在开始游戏前，应先调用此方法，给予游戏必要的参数。
     *
     * @param p1 玩家1
     * @param p2 玩家2
     */
    public void init(Player p1, Player p2, int p) {
        this.p1 = p1;

        this.p2 = p2;
        if (p == 1) {
            this.onTurn = p1;
        } else if (p == 2) {
            this.onTurn = p2;
        }
    }

    public void init(Player p1, Player p2, Player p3, int p) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        if (p == 1) {
            this.onTurn = p1;
        } else if (p == 2) {
            this.onTurn = p2;
        } else if (p == 3) {
            this.onTurn = p3;
        }
    }

    public void init(Player p1, Player p2, Player p3, Player p4, int p) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        if (p == 1) {
            this.onTurn = p1;
        } else if (p == 2) {
            this.onTurn = p2;
        } else if (p == 3) {
            this.onTurn = p3;
        } else if (p == 4) {
            this.onTurn = p4;
        }
    }

    /**
     * 进行下一个回合时应调用本方法。
     * 在这里执行每个回合结束时需要进行的操作。
     * <p>
     * (目前这里没有每个玩家进行n回合的计数机制的，请自行修改完成哦~）
     */
    public void nextTurn() {
        scoreBoard1.update(onTurn);
        scoreBoard2.update(onTurn);
        if (Select1.mainFrame.getNamefinal().size()== 3) {
            scoreBoard3.update(onTurn);
        }
        if (Select1.mainFrame.getNamefinal().size() == 4) {
            scoreBoard3.update(onTurn);
            scoreBoard4.update(onTurn);
        }
        if (Select1.mainFrame.getNamefinal().size() == 2) {
            if (onTurn == p1) {
                counterP1++;
                if (counterP1 == 5) {
                    JOptionPane.showMessageDialog(gamePanel, "表演时间结束了,请点击结束按钮", "名字叫弹窗的弹窗", JOptionPane.WARNING_MESSAGE);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                }

            } else if (onTurn == p2) {
                counterP2++;
                if (counterP2 == 5) {
                    JOptionPane.showMessageDialog(gamePanel, "表演时间结束了,请点击结束按钮", "名字叫弹窗的弹窗", JOptionPane.WARNING_MESSAGE);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                }
            }
        }
        if (Select1.mainFrame.getNamefinal().size() == 3) {
            if (onTurn == p1) {
                counterP1++;
                if (counterP1 == 5) {
                    JOptionPane.showMessageDialog(gamePanel, "表演时间结束了,请点击结束按钮", "名字叫弹窗的弹窗", JOptionPane.WARNING_MESSAGE);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                }

            } else if (onTurn == p2) {
                counterP2++;
                if (counterP2 == 5) {
                    JOptionPane.showMessageDialog(gamePanel, "表演时间结束了,请点击结束按钮", "名字叫弹窗的弹窗", JOptionPane.WARNING_MESSAGE);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                }
            } else if (onTurn == p3) {
                counterP3++;
                if (counterP3 == 5) {
                    JOptionPane.showMessageDialog(gamePanel, "表演时间结束了,请点击结束按钮", "名字叫弹窗的弹窗", JOptionPane.WARNING_MESSAGE);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                }
            }
        }
        if (Select1.mainFrame.getNamefinal().size()== 4) {
            if (onTurn == p1) {
                counterP1++;
                if (counterP1 == 5) {
                    JOptionPane.showMessageDialog(gamePanel, "表演时间结束了,请点击结束按钮", "名字叫弹窗的弹窗", JOptionPane.WARNING_MESSAGE);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                }

            } else if (onTurn == p2) {
                counterP2++;
                if (counterP2 == 5) {
                    JOptionPane.showMessageDialog(gamePanel, "表演时间结束了,请点击结束按钮", "名字叫弹窗的弹窗", JOptionPane.WARNING_MESSAGE);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                }
            } else if (onTurn == p3) {
                counterP3++;
                if (counterP3 == 5) {
                    JOptionPane.showMessageDialog(gamePanel, "表演时间结束了,请点击结束按钮", "名字叫弹窗的弹窗", JOptionPane.WARNING_MESSAGE);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                }
            } else if (onTurn == p4) {
                counterP4++;
                if (counterP4 == 5) {
                    JOptionPane.showMessageDialog(gamePanel, "表演时间结束了,请点击结束按钮", "名字叫弹窗的弹窗", JOptionPane.WARNING_MESSAGE);
                    Main.login.getSelect().getMainFrame().upDatetitle();
                }
            }
        }
        if (Select1.mainFrame.getNamefinal().size()== 2&&Select1.mainFrame.getNamefinal().get(1).equals("")) {
            if (onTurn == p1) {
                counterP1++;
                if (counterP1 == 2) {
                    onTurn = p2;
                    System.out.println("Now it is " + onTurn.getUserName() + "'s turn.");//需要注意，只有人（p1）能通过检测点击调到nextTurn方法，当人的1-5次点击结束后，应该执行机器的操作，最后再把onTurn改回p1。
                    foolishComputer();
                    scoreBoard2.update(p2);
                    onTurn = p1;
                    counterP1 = 0;
                }
            }
        }


        System.out.println("Now it is " + onTurn.getUserName() + "'s turn.");
        scoreBoard1.update(onTurn);
        scoreBoard2.update(onTurn);
        if (Select1.mainFrame.getNamefinal().size() == 3) {
            scoreBoard3.update(onTurn);
        }
        if (Select1.mainFrame.getNamefinal().size() == 4) {
            scoreBoard3.update(onTurn);
            scoreBoard4.update(onTurn);
        }

        //每个回合之后，判断是否可以游戏结束
        if (Select1.mainFrame.getNamefinal().size() == 2) {
            String message;
            if (Math.abs(p1.getScore() - p2.getScore()) > gamePanel.getMineRest()) {
                //这个if包括了
                // 剩余雷不为0时获胜的情况
                // 剩余雷为0时，分数不相等的情况
                Music.play4();
                if (p1.getScore() > p2.getScore()) {
                    message = "恭喜" + p1.getUserName() + "获得胜利！";
                   setlist(p1);
                } else {
                    if(p2.getUserName().equals("")){
                        message="恭喜冷酷的电脑获得胜利！";
                    }
                    else{
                        message = "恭喜" + p2.getUserName() + "获得胜利！";
                    setlist(p2);}
                }
                showCustomDialog(Select1.mainFrame, Select1.mainFrame, message);
                //JOptionPane.showMessageDialog(Select1.mainFrame,message,"GAME OVER",JOptionPane.INFORMATION_MESSAGE);
            } else if (gamePanel.getMineRest() == 0 && p1.getScore() == p2.getScore()) {
                Music.play4();
                if (p1.getMistake() > p2.getMistake()) {
                    if(p2.getUserName().equals("")){
                        message="恭喜冷酷的电脑获得胜利！";
                    }
                    else{
                        message = "恭喜" + p2.getUserName() + "获得胜利！";
                        setlist(p2);}
                    showCustomDialog(Select1.mainFrame, Select1.mainFrame, message);
                } else if (p1.getMistake() < p2.getMistake()) {
                    message = "恭喜" + p1.getUserName() + "获得胜利！";
                    setlist(p1);
                    showCustomDialog(Select1.mainFrame, Select1.mainFrame, message);
                } else {
                    showCustomDialog(Select1.mainFrame, Select1.mainFrame, "两位势均力敌！不如再来一盘？");
                }
            }
        } else {
            //每回合排序，取前两名
            Player[] players = new Player[Select1.mainFrame.getNamefinal().size()];
            players[0] = p1;
            players[1] = p2;
            players[2] = p3;
            if (Select1.mainFrame.getNamefinal().size()== 4) {
                players[3] = p4;
            }

            //从小到大
            for (int i = 0; i < Select1.mainFrame.getNamefinal().size(); i++) {
                for (int j = 0; j < Select1.mainFrame.getNamefinal().size() - 1 - i; j++) {
                    if (players[j].getScore() > players[j + 1].getScore()) {
                        Player temp = players[j];
                        players[j] = players[j + 1];
                        players[j + 1] = temp;
                    }
                }
            }
            for (int i = 0; i < players.length / 2; i++) {
                Player temp = players[i];
                players[i] = players[players.length - 1 - i];
                players[players.length - 1 - i] = temp;
            }
            comparison(players[0], players[1]);
        }
    }

    public void comparison(Player p1, Player p2) {
        String message;
        if (Math.abs(p1.getScore() - p2.getScore()) > gamePanel.getMineRest()) {
            //这个if包括了
            // 剩余雷不为0时获胜的情况
            // 剩余雷为0时，分数不相等的情况
            Music.play4();
            if (p1.getScore() > p2.getScore()) {
                message = "恭喜" + p1.getUserName() + "获得胜利！";
                setlist(p1);

            } else {
                message = "恭喜" + p2.getUserName() + "获得胜利！";
                setlist(p2);

            }
            showCustomDialog(Select1.mainFrame, Select1.mainFrame, message);
            //JOptionPane.showMessageDialog(Select1.mainFrame,message,"GAME OVER",JOptionPane.INFORMATION_MESSAGE);
        } else if (gamePanel.getMineRest() == 0 && p1.getScore() == p2.getScore()) {
            Music.play4();
            if (p1.getMistake() > p2.getMistake()) {
                message = "恭喜" + p2.getUserName() + "获得胜利！";
                setlist(p2);
                showCustomDialog(Select1.mainFrame, Select1.mainFrame, message);
            } else if (p1.getMistake() < p2.getMistake()) {
                message = "恭喜" + p1.getUserName() + "获得胜利！";
                setlist(p1);
                showCustomDialog(Select1.mainFrame, Select1.mainFrame, message);
            } else {
                showCustomDialog(Select1.mainFrame, Select1.mainFrame, "这都能平局？再来一盘吧！");
            }
        }
    }

    private static void showCustomDialog(Frame owner, Component parentComponent, String message) {
        // 创建一个模态对话框
        final JDialog dialog = new JDialog(owner, "GAME OVER", true);
        // 设置对话框的宽高
        dialog.setSize(250, 100);
        // 设置对话框大小不可改变
        dialog.setResizable(false);
        // 设置对话框相对显示的位置
        dialog.setLocationRelativeTo(parentComponent);

        // 创建一个标签显示消息内容
        JLabel messageLabel = new JLabel(message);

        // 创建一个按钮用于关闭对话框
        JButton returnBtn = new JButton("返回主界面");
        returnBtn.setFocusable(false);
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 关闭对话框
                dialog.dispose();
                //游戏界面不可见
                Select1.mainFrame.setVisible(false);
                Select1 select1=new Select1();
                select1.setVisible(true);
            }
        });

        // 创建对话框的内容面板, 在面板内可以根据自己的需要添加任何组件并做任意是布局
        JPanel panel = new JPanel();

        panel.add(messageLabel);
        panel.add(returnBtn);

        // 设置对话框的内容面板
        dialog.setContentPane(panel);
        // 显示对话框
        dialog.setVisible(true);
    }

    public void foolishComputer() {


        //计划：设置优先级：开雷>安全格子>非安全格子，当雷和安全格子开启后，非安全格子的有雷概率小于50%时，点开，同时调递归方法；否则跳过回合。同时把onTurn改成p1。
        //对每一个已开且非0非雷格子，检测周围8个格已开（或已插旗的）的雷数，如果和本身相同，则剩余格子标记为安全格子；
        //如果本身数字减去周围已开或已经插旗的雷数等于剩余格子数，则剩余格子标记为雷格子；
        //如果剩余格子被标记为一次安全或雷格子后，无视掉其他的非安全格子标记，执行相应操作。
        //如果只有非安全格子标记，对于每个标记，有一个概率超过50%，就点开。
        boolean test = false;
        //点击次数只能为1次
        GridComponent goalComponent = new GridComponent();//?
        double minProbability = 1;//找最小概率的
        Outer:
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {//对已经开了的检测周围格子
                if (gamePanel.getMineField()[i][j].getStatus() == GridStatus.Clicked && gamePanel.getMineField()[i][j].getContent() != 9 && gamePanel.getMineField()[i][j].getContent() != 0) {//????????????
                    GridComponent gridComponent = gamePanel.getMineField()[i][j];
                    //找安全格子和雷格子或者概率
                    double temp = testSafety(gridComponent);
                    System.out.println(temp);
                    if (temp >= 1) {
                        //对周围格子改变状态
                        for (int k = -1; (k + gridComponent.getRow() < xCount) && k < 2; k++) {
                            if (k + gridComponent.getRow() < 0) {
                                continue;
                            }
                            for (int l = -1; (l + gridComponent.getCol() < yCount) && l < 2; l++) {
                                if (l + gridComponent.getCol() < 0) {
                                    continue;//越界直接跳到下一个循环
                                }
                                if (k == 0 && l == 0) {//自身不再检查
                                    continue;
                                }//
                                if (gamePanel.getMineField()[k + gridComponent.getRow()][l + gridComponent.getCol()].getStatus() == GridStatus.Covered) {
                                    if (temp == 2) {//雷
                                        Music.play2();
                                        System.out.println(66);
                                        gamePanel.getMineField()[k + gridComponent.getRow()][l + gridComponent.getCol()].setStatus(GridStatus.Flag);
                                        gamePanel.getMineField()[k + gridComponent.getRow()][l + gridComponent.getCol()].setClickNum(1);
                                        gamePanel.getMineField()[k + gridComponent.getRow()][l + gridComponent.getCol()].setFlagTest(true);
                                        gamePanel.getMineField()[k + gridComponent.getRow()][l + gridComponent.getCol()].repaint();
                                        MainFrame.controller.getP2().addScore();
                                        MainFrame.controller.getGamePanel().changeMineRest();
                                        Main.login.getSelect().getMainFrame().upDateMine();
                                        Main.login.getSelect().getMainFrame().gamePanel.addCount();

                                        test = true;
                                        break Outer;
                                    } else {//安全格
                                        System.out.println(55);
                                        Music.play1();
                                        gamePanel.getMineField()[k + gridComponent.getRow()][l + gridComponent.getCol()].recursion(gamePanel.getMineField()[k + gridComponent.getRow()][l + gridComponent.getCol()]);
                                        gamePanel.getMineField()[k + gridComponent.getRow()][l + gridComponent.getCol()].repaint();
                                        test = true;
                                        break Outer;
                                    }
                                }
                            }
                        }
                    }
                    if (temp < 1 && temp != 0) {
                        if (minProbability > temp) {
                            minProbability = temp;
                            goalComponent = gamePanel.getMineField()[i][j];
                            System.out.println(i);//把最小概率的先存好
                            System.out.println(j);//把最小概率的先存好
                        }
                    }
                }
            }
        }//如果一步也没走，再看概率，
        if (!test) {
            System.out.println(99);
            Outer:
            for (int i = -1; (i + goalComponent.getRow() < xCount) && i < 2; i++) {
                if (i + goalComponent.getRow() < 0) {
                    continue;
                }
                for (int j = -1; (j + goalComponent.getCol() < yCount) && j < 2; j++) {
                    if (j + goalComponent.getCol() < 0) {
                        continue;//越界直接跳到下一个循环
                    }
                    if (i == 0 && j == 0) {//自身不再检查
                        continue;
                    }//周围cover格子打开遍历到的第一个
                    if (gamePanel.getMineField()[i + goalComponent.getRow()][j + goalComponent.getCol()].getStatus() == GridStatus.Covered) {
                            gamePanel.getMineField()[i + goalComponent.getRow()][j + goalComponent.getCol()].recursion(gamePanel.getMineField()[i + goalComponent.getRow()][j + goalComponent.getCol()]);
                            if (gamePanel.getMineField()[i + goalComponent.getRow()][j + goalComponent.getCol()].getContent() != 9) {
                                Music.play1();
                            }
                            if (gamePanel.getMineField()[i + goalComponent.getRow()][j + goalComponent.getCol()].getContent() == 9) {
                                Music.play3();
                                MainFrame.controller.getP2().costScore();
                                MainFrame.controller.getP2().addMistake();
                                MainFrame.controller.getGamePanel().changeMineRest();
                                Main.login.getSelect().getMainFrame().upDateMine();
                            }
                            break Outer;
                    }
                }
            }
        }
    }

    //计划当安全格子时返回1，雷格子返回2，其他格子返回概率
    public double testSafety(GridComponent gridComponent) {
        int mineNum = 0;//已开雷数
        boolean testRest = false;
        int coveredNum = 0;//未开格子数
        for (int i = -1; (i + gridComponent.getRow() < xCount) && i < 2; i++) {
            if (i + gridComponent.getRow() < 0) {
                continue;
            }
            for (int j = -1; (j + gridComponent.getCol() < yCount) && j < 2; j++) {
                if (j + gridComponent.getCol() < 0) {
                    continue;//越界直接跳到下一个循环
                }
                if (i == 0 && j == 0) {//自身不再检查
                    continue;
                }//已经开的雷
                if (gamePanel.getMineField()[i + gridComponent.getRow()][j + gridComponent.getCol()].getContent() == 9 && gamePanel.getMineField()[i + gridComponent.getRow()][j + gridComponent.getCol()].getStatus() != GridStatus.Covered) {
                    mineNum++;
                }//是否有未开格子
                if (gamePanel.getMineField()[i + gridComponent.getRow()][j + gridComponent.getCol()].getStatus() == GridStatus.Covered) {
                    testRest = true;
                    coveredNum++;
                }
            }
        }
//        System.out.println(mineNum);
//        System.out.println(coveredNum);
//        System.out.println(gridComponent.getRow());
//        System.out.println(gridComponent.getCol());
//        System.out.println(gridComponent.getContent());
        //也就是说，这两个检测都是保证周围有空格子
        if (mineNum == gridComponent.getContent() && testRest) {//已开雷数等于中间数字，周围格子安全
            return 1;
        } else if (mineNum + coveredNum == gridComponent.getContent() && testRest) {//已开地雷数加cover数等于中间数字，周围cover数均为雷.
            return 2;
        } else if (testRest) {
            System.out.println(100);
            return (gridComponent.getContent() - mineNum) / (double)coveredNum; //未开雷数/未开格子数
        }
        return 0;

    }


    private Collection<JPanel> getContentPane() {
        return null;
    }


    /**
     * 获取正在进行当前回合的玩家。
     *
     * @return 正在进行当前回合的玩家
     */
    public Player getOnTurnPlayer() {
        return onTurn;
    }


    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }


    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ScoreBoard getScoreBoard1() {
        return scoreBoard1;
    }

    public ScoreBoard getScoreBoard2() {
        return scoreBoard2;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setScoreBoard1(ScoreBoard scoreBoard) {
        this.scoreBoard1 = scoreBoard;
    }

    public void setScoreBoard2(ScoreBoard scoreBoard) {
        this.scoreBoard2 = scoreBoard;
    }

    public void setScoreBoard3(ScoreBoard scoreBoard) {
        this.scoreBoard3 = scoreBoard;
    }

    public void setScoreBoard4(ScoreBoard scoreBoard) {
        this.scoreBoard4 = scoreBoard;
    }

    //这两个类方法需要完善，如将文件名更新到用户界面
    public void writeDataToFile(String fileName) {
        int x = Select1.mainFrame.getXCount();
        int y = Select1.mainFrame.getYCount();
        int playnumber = Login.select.playnum;
        int[][] logicboard = this.gamePanel.getChessboard();
        int[][] stateboard = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int state = 9;
                if (Select1.mainFrame.getGamePanel().getMineField()[i][j].getStatus() == GridStatus.Covered) {
                    state = 0;
                } else if (Select1.mainFrame.getGamePanel().getMineField()[i][j].getStatus() == GridStatus.Clicked) {
                    state = 1;
                } else if (Select1.mainFrame.getGamePanel().getMineField()[i][j].getStatus() == GridStatus.Flag) {
                    state = 2;
                }
                stateboard[i][j] = state;
            }
        }
        try {
            try (FileWriter writer = new FileWriter(fileName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(Integer.toString(playnumber));
                out.write("\n");
                out.write(Integer.toString(x));
                out.write("\n");
                out.write(Integer.toString(y));
                out.write("\n");
                out.write(Arrays.deepToString(logicboard) + "\n");// \r\n即为换行
                out.write(Arrays.deepToString(stateboard) + "\n");
                out.write(Integer.toString(gamePanel.getMineRest()) + "\n");
                out.write(onTurn.getUserName() + "\n");
                out.write(p1.getUserName() + " " + p1.getScore() + " " + p1.getMistake() + " " + getCounterP1() + "\n");
                out.write(p2.getUserName() + " " + p2.getScore() + " " + p2.getMistake() + " " + getCounterP1() + "\n");
                if (Select1.mainFrame.getNamefinal().size()>= 3) {
                    out.write(p3.getUserName() + " " + p3.getScore() + " " + p3.getMistake() + " " + getCounterP3() + "\n");
                    if (Select1.mainFrame.getNamefinal().size()== 4) {
                        out.write(p4.getUserName() + " " + p4.getScore() + " " + p4.getMistake() + " " + getCounterP4() + "\n");
                    }
                }
                out.flush();// 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDataToFilelist(String fileName,String playername,int score,int mineCount) {
        try {
            try (FileWriter writer = new FileWriter(fileName,true);
                 BufferedWriter out = new BufferedWriter(writer)
            ) { out.write(playername);
            out.write(" ");
            out.write(Integer.toString(score));
            out.write(" ");
            out.write(Integer.toString(mineCount));
            out.write("\n");
                out.flush();// 把缓存区内容压入文件
            }
    }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player getP3() {
        return p3;
    }

    public Player getP4() {
        return p4;
    }
    private void setlist(Player player){
        if(Select1.mainFrame.getmineCount()!=0){
            this.writeDataToFilelist("list.txt",player.getUserName(),player.getScore(),Select1.mainFrame.getmineCount());}
        else{this.writeDataToFilelist("list.txt",player.getUserName(),player.getScore(),Select1.mainFrame.getMineCountcudang());}
    }
}
