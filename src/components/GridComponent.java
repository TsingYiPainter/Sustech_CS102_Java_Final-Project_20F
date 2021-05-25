package components;

import entity.GridStatus;
import minesweeper.GamePanel;
import minesweeper.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import main.Main;
import minesweeper.Music;

public class GridComponent extends BasicComponent {
    public static int gridSize = 28;//一个格子的宽度


    private int row;
    private int col;
    private GridStatus status = GridStatus.Covered;
    private int content = 0;
    private int xCount;
    private int yCount;
    private int mineCount;
    private int clickNum = 0;
    private boolean flagTest=false;

    public void setFlagTest(boolean flagTest) {
        this.flagTest = flagTest;
    }

    public GridComponent() {

    }

    public GridComponent(int x, int y, int xCount, int yCount, int mineCount) {
        this.setSize(gridSize, gridSize);//设置一个正方形格子
        this.row = x;//查找位置（x，y）
        this.col = y;
        this.xCount = xCount;
        this.yCount = yCount;
        this.mineCount = mineCount;
    }

    public GridComponent(int states, int x, int y, int xCount, int yCount, String s) {
        this.setSize(gridSize, gridSize);//设置一个正方形格子
        this.row = x;//查找位置（x，y）
        this.col = y;
        this.xCount = xCount;
        this.yCount = yCount;
        if (states == 0) {
            this.status = GridStatus.Covered;
        }
        if (states == 1) {
            this.status = GridStatus.Clicked;
            clickNum++;
        }
        if (states == 2) {
            this.status = GridStatus.Flag;
            clickNum++;
            flagTest=true;
        }
    }

    public GridStatus getStatus() {
        return status;
    }

    @Override
    public void onMouseLeftClicked() {
        System.out.printf("Gird (%d,%d) is left-clicked.\n", row, col);//在非用户界面显示的信息
        if (this.status == GridStatus.Covered || this.status == GridStatus.Enter) {
            Main.login.getSelect().getMainFrame().gamePanel.addCount();
            recursion(this);
            if (getContent() != 9) {
                Music.play1();
            }
            if (getContent() == 9) {
                Music.play3();
                MainFrame.controller.getOnTurn().costScore();
                MainFrame.controller.getOnTurn().addMistake();
                MainFrame.controller.getGamePanel().changeMineRest();
                Main.login.getSelect().getMainFrame().upDateMine();

            }
            MainFrame.controller.nextTurn();
        }

    }

    public int getContent() {
        return content;
    }


    /*public void onMouseRightClicked() {

        System.out.printf("Gird (%d,%d) is right-clicked.\n", row, col);
        if (this.status == GridStatus.Covered && getContent() == 9) {
            MainFrame.controller.getOnTurn().addScore();
            MainFrame.controller.getGamePanel().changeMineRest();
            Main.login.getSelect().getMainFrame().upDateMine();
            Main.login.getSelect().getMainFrame().gamePanel.addCount();
            this.status = GridStatus.Flag;
            repaint();
            if (getContent() != 9 && this.status == GridStatus.Covered) {
                MainFrame.controller.getOnTurn().addMistake();
                this.status = GridStatus.Clicked;
                repaint();
            }
            MainFrame.controller.nextTurn();
        }
        //TODO: 在右键点击一个格子的时候，还需要做什么？
        //实现剩余雷数的变动
    }*/

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }

    @Override
    public void onMouseRightPressed() {
        if (this.status == GridStatus.Covered || this.status == GridStatus.Enter) {
            this.status = GridStatus.Flag;
            repaint();
        }
    }

    public int getClickNum() {
        return clickNum;
    }

    public boolean isFlagTest() {
        return flagTest;
    }

    public void onMouseRightReleased() {
        clickNum++;
        System.out.printf("Gird (%d,%d) is right-clicked.\n", row, col);
        if (this.status == GridStatus.Flag && getContent() == 9) {
            Music.play2();
            MainFrame.controller.getOnTurn().addScore();
            MainFrame.controller.getGamePanel().changeMineRest();
            Main.login.getSelect().getMainFrame().upDateMine();
            Main.login.getSelect().getMainFrame().gamePanel.addCount();
            this.status = GridStatus.Flag;
            flagTest=true;
            repaint();

        } else {
            Music.play3();
            MainFrame.controller.getOnTurn().addMistake();
            this.status = GridStatus.Clicked;
            repaint();
        }
        MainFrame.controller.nextTurn();
    }

    @Override
    public void onMouseEnter() {
        if (this.status == GridStatus.Covered) {
            this.status = GridStatus.Enter;
            repaint();
        }
    }

    @Override
    public void onMouseExited() {
        if (this.status == GridStatus.Enter) {
            this.status = GridStatus.Covered;
            repaint();
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void recursion(GridComponent gridComponent) {
        gridComponent.clickNum++;
        if (gridComponent.getContent() == 9) {//雷
            //首发触雷
            if (Main.login.getSelect().getMainFrame().gamePanel.getCount() == 1) {
                while (getContent() == 9) {
                    int[][] chessboard = MainFrame.controller.getGamePanel().generateChessBoard(xCount, yCount, mineCount);
                    MainFrame.controller.getGamePanel().changeContent(chessboard, xCount, yCount);
                }
            }
//            this.status = GridStatus.Clicked;
//            repaint();
            if (Main.login.getSelect().getMainFrame().gamePanel.getCount() != 1) {
                this.status = GridStatus.Clicked;
                gridComponent.repaint();
                return;
            }
        }
        if (gridComponent.getStatus() == GridStatus.Flag || gridComponent.getStatus() == GridStatus.Clicked) {
            //已经开了的不开
            return;
        }
        if (gridComponent.getContent() != 9 && gridComponent.getContent() != 0) {
            // 不是雷不是0，只开自身
            gridComponent.status = GridStatus.Clicked;
            gridComponent.repaint();
            return;//return表示终止了
        }//剩下的是为0的时候
        gridComponent.status = GridStatus.Clicked;
        gridComponent.repaint();
        //一种不扩大棋盘检测周围8个格子的情况
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
                }//获得新的gridComponent
                GridComponent gridComponent1 = Main.login.getSelect().getMainFrame().gamePanel.getMineField()[i + gridComponent.getRow()][j + gridComponent.getCol()];
                recursion(gridComponent1);
            }
        }
    }

    public void setStatus(GridStatus status) {
        this.status = status;
    }

    public void draw(Graphics g) {
        int spacing = (int) (gridSize * 0.05);

        if (this.status == GridStatus.Enter) {
            Image image = new ImageIcon("images/格子11.jpg").getImage();
            g.drawImage(image, spacing, spacing, getWidth() - 2 * spacing, getHeight() - 2 * spacing, this);
        }

        if (this.status == GridStatus.Covered) {

            Image image = new ImageIcon("images/格子4.jpg").getImage();
            g.drawImage(image, spacing, spacing, getWidth() - 2 * spacing, getHeight() - 2 * spacing, this);

        }
        if (this.status == GridStatus.Clicked) {
            if (content != 9) {
                Image image = new ImageIcon("images/背景1.jpg").getImage();
                g.drawImage(image, spacing, spacing, getWidth() - 2 * spacing, getHeight() - 2 * spacing, this);
                g.setColor(Color.red);
                if (content == 0) {
                } else {
                    g.drawString(Integer.toString(content), getWidth() / 2 - 5, getHeight() / 2 + 5);//大概是设置字体居中的意思  //字体颜色
                }
            } else {
                Image image = new ImageIcon("images/地雷2.png").getImage();
                g.drawImage(image, spacing, spacing, getWidth() - 2 * spacing, getHeight() - 2 * spacing, this);
            }
        }
        if (this.status == GridStatus.Flag) {

            Image image = new ImageIcon("images/旗子.png").getImage();
            g.drawImage(image, spacing, spacing, getWidth() - 2 * spacing, getHeight() - 2 * spacing, this);
        }
    }

    public void setContent(int content) {
        this.content = content;
    }

    @Override
    public void paintComponent(Graphics g) {//调用库的方法来画图？
        super.printComponents(g);
        draw(g);
    }
}
