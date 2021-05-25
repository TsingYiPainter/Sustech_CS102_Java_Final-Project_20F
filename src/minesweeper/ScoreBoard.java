package minesweeper;

import components.GridComponent;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * 此类的对象是一个计分板容器，通过传入玩家对象，
 * 可以用update()方法实时更新玩家的分数以及失误数。
 */
public class ScoreBoard extends JPanel {
    private static ArrayList<Player> players;

    Player p1;
    Player p2;
    Player p3;
    Player p4;

    JLabel score1 = new JLabel();
    JLabel score2 = new JLabel();
    JLabel score3 = new JLabel();
    JLabel score4 = new JLabel();

    /**
     * 通过进行游戏的玩家来初始化计分板。这里只考虑了两个玩家的情况。
     * 如果想要2-4人游戏甚至更多，请自行修改(建议把所有玩家存在ArrayList)~
     */
    public ScoreBoard(Player p, int a, int yCount) {
        if (a == 1) {
            this.setSize(200, 50);
            this.setLocation( 440-14*yCount, 210);
            this.setOpaque(false);
            JLabel jLabel1 = setlabel2("Score Board -P1 ", 100, 80, 440-14*yCount, 210);
            this.add(jLabel1);
            jLabel1.setVisible(true);
            p1 = p;
            score1.setLocation(yCount * GridComponent.gridSize + 20, 200);
            score1.setFont(new Font("黑体", Font.ITALIC, 15));
            score1.setForeground(Color.white);
            this.add(score1);
            score1.setVisible(true);
            update(p1);
        } else if (a == 2) {
            this.setSize(200, 50);
            this.setLocation(440-14*yCount, 480);
            this.setOpaque(false);
            JLabel jLabel2 = setlabel2("Score Board -P2 ", 100, 80, 440-14*yCount, 480);
            this.add(jLabel2);
            jLabel2.setVisible(true);
            p2 = p;
            score2.setLocation(yCount * GridComponent.gridSize + 20, 430);
            score2.setFont(new Font("黑体", Font.ITALIC, 15));
            score2.setForeground(Color.white);
            this.add(score2);
            score2.setVisible(true);
            update(p2);
        } else if (a == 3) {
            this.setSize(200, 50);
            this.setLocation(660+14*yCount, 210);/////
            this.setOpaque(false);
            JLabel jLabel3 = setlabel2("Score Board -P3 ", 100, 80, 855+14*yCount, 210);
            this.add(jLabel3);/////
            jLabel3.setVisible(true);
            p3 = p;
            score3.setLocation(yCount * GridComponent.gridSize + 220, 200);
            score3.setFont(new Font("黑体", Font.ITALIC, 15));
            score3.setForeground(Color.white);
            this.add(score3);
            score3.setVisible(true);
            update(p3);
        } else if (a == 4) {
            this.setSize(200, 50);
            this.setLocation(660+14*yCount, 480);/////
            this.setOpaque(false);
            JLabel jLabel4 = setlabel2("Score Board -P4 ", 100, 80, 855+14*yCount, 480);
            this.add(jLabel4);
            jLabel4.setVisible(true);
            p4 = p;
            score4.setLocation(yCount * GridComponent.gridSize + 220, 430);
            score4.setFont(new Font("黑体", Font.ITALIC, 15));
            score4.setForeground(Color.white);
            this.add(score4);
            score4.setVisible(true);
            update(p4);
        }
    }


    /**
     * 刷新计分板的数据。
     * 计分板会自动重新获取玩家的分数，并更新显示。
     */
    public void update(Player p) {
        if (p == p1) {
            score1.setText(String.format("%s 得分 %d " + "失误 %d", p1.getUserName(), p1.getScore(), p1.getMistake()));
        }
        if (p == p2) {
            score2.setText(String.format("%s 得分 %d " + "失误 %d ", p2.getUserName(), p2.getScore(), p2.getMistake()));
        }
        if (p == p3) {
            score3.setText(String.format("%s 得分 %d " + "失误 %d ", p3.getUserName(), p3.getScore(), p3.getMistake()));
        }
        if (p == p4) {
            score4.setText(String.format("%s 得分 %d " + "失误 %d ", p4.getUserName(), p4.getScore(), p4.getMistake()));
        }
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
}
