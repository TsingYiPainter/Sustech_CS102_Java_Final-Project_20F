package minesweeper;

import components.GridComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    private GridComponent[][] mineField;//注意雷区的属性
    private int[][] chessboard;
    private final Random random = new Random();
    private int mine;
    //count用来判断点击次数
    private int count = 0;

    public int[][] getChessboard() {
        return chessboard;
    }
    public int getMine() {
        return mine;
    }

    public int getCount() {
        return count;
    }

    public int addCount() {
        return count++;
    }

    //剩余雷数
    public int mineRest;

    //当加分减分时，使剩余雷数-1
    public int changeMineRest() {
        return mineRest--;
    }

    public int getMineRest() {
        return mineRest;
    }

    public GridComponent[][] getMineField() {
        return mineField;
    }

    /**
     * 初始化一个具有指定行列数格子、并埋放了指定雷数的雷区。
     *
     * @param xCount    count of grid in column
     * @param yCount    count of grid in row
     * @param mineCount mine count
     */

    public GamePanel(int xCount, int yCount, int mineCount) {
        mineRest = mineCount;
        mine = mineCount;
        this.setVisible(true);//游戏界面可见的
        this.setFocusable(true);//可聚焦，啥意思
        this.setLayout(null);//不采用特定的布局方式
        this.setBackground(Color.WHITE);
        this.setSize(GridComponent.gridSize * yCount+8, GridComponent.gridSize * xCount+8);//雷区的大小
        this.setLocation(625-14*yCount,40);


        initialGame(xCount, yCount, mineCount);

        repaint();
    }

    public GamePanel(int x,int y,int[][]chessboard,int[][]stateboard,int minecount2){
        this.chessboard=chessboard;
        mineRest=minecount2;
        count=1;
        this.setVisible(true);//游戏界面可见的
        this.setFocusable(true);//可聚焦，啥意思
        this.setLayout(null);//不采用特定的布局方式
        this.setBackground(Color.WHITE);
        this.setSize(GridComponent.gridSize * y+8, GridComponent.gridSize * x+8);//雷区的大小
        this.setLocation(625-14*y,40);
        mineField = new GridComponent[x][y];
        for (int i = 0; i < x; i++) {//添加格子
            for (int j = 0; j < y; j++) {
                GridComponent gridComponent = new GridComponent(stateboard[i][j],i, j,x,y,"null");//相对于雷区左上的坐标
                gridComponent.setContent(chessboard[i][j]);
                gridComponent.setLocation(j * GridComponent.gridSize+4, i * GridComponent.gridSize+4);
                mineField[i][j] = gridComponent;
                this.add(mineField[i][j]);
            }
        }repaint();
    }

    public void initialGame(int xCount, int yCount, int mineCount) {
        mineField = new GridComponent[xCount][yCount];

        generateChessBoard(xCount, yCount, mineCount);

        for (int i = 0; i < xCount; i++) {//添加格子
            for (int j = 0; j < yCount; j++) {
                GridComponent gridComponent = new GridComponent(i, j, xCount, yCount, mineCount);//相对于雷区左上的坐标
                gridComponent.setContent(chessboard[i][j]);
                gridComponent.setLocation(j * GridComponent.gridSize+4, i * GridComponent.gridSize+4);
                mineField[i][j] = gridComponent;
                this.add(mineField[i][j]);
            }
        }
    }

    public void changeContent(int[][] chessboard, int xCount, int yCount) {
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                mineField[i][j].setContent(chessboard[i][j]);
            }
        }
    }

    public int[][] generateChessBoard(int xCount, int yCount, int mineCount) {
        //todo: generate chessboard by your own algorithm
        chessboard = new int[xCount][yCount];
        do {
            chessboard = new int[xCount][yCount];
            ArrayList<Integer> wholeXCoordinate = new ArrayList<>();//存棋盘的x坐标
            ArrayList<Integer> wholeYCoordinate = new ArrayList<>();//存棋盘的y坐标
            ArrayList<Integer> mineXCoordinate = new ArrayList<>();//存雷区的x坐标
            ArrayList<Integer> mineYCoordinate = new ArrayList<>();//存雷区的y坐标
            for (int i = 0; i < xCount; i++) {
                for (int j = 0; j < yCount; j++) {
                    wholeXCoordinate.add(i);
                    wholeYCoordinate.add(j);
                }//现在wholeXCoordinate wholeYCoordinate长度均为x*y
            }
            for (int i = 0; i < mineCount; i++) {
                //
                int randomNumber = random.nextInt(xCount * yCount - i);//有bug????????????
                //存到雷区坐标中，并从棋盘的xy中将其移出
                mineXCoordinate.add(wholeXCoordinate.get(randomNumber));
                mineYCoordinate.add(wholeYCoordinate.get(randomNumber));
                wholeXCoordinate.remove(randomNumber);
                wholeYCoordinate.remove(randomNumber);
            }
            //遍历雷的坐标集合，把chessboard中相应元素改变！
            for (int i = 0; i < mineCount; i++) {
                int mineX = mineXCoordinate.get(i);
                int mineY = mineYCoordinate.get(i);
                chessboard[mineX][mineY] = 9;
            }

        } while (!testDensity(chessboard));
        //后边写对其他数的填充
        int[][] chessboardPlus = new int[xCount + 2][yCount + 2];
        for (int i = 1; i < xCount + 1; i++) {
            for (int j = 1; j < yCount + 1; j++) {
                chessboardPlus[i][j] = chessboard[i - 1][j - 1];
            }
        }
        for (int i = 1; i < xCount + 1; i++) {
            for (int j = 1; j < yCount + 1; j++) {
                if (chessboardPlus[i][j] == 9) {
                    //可以把-1改成雷的图片
                } else {
                    chessboard[i - 1][j - 1] = testNumberOfMine(chessboardPlus, i, j);
                }
            }
        }
        return chessboard;
    }

    public int testNumberOfMine(int[][] chessboardPlus, int i, int j) {
        int counter = 0;
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                if (chessboardPlus[k][l] == 9) {
                    counter++;
                }
            }
        }
        return counter;
    }

    //避免3*3全是雷,但对于行数列数没有3的，也需要另外讨论
    public boolean testDensity(int[][] chessboard) {
        boolean test = true;
        Outer:
        for (int i = 0; i + 2 < chessboard.length; i++) {//行边界限制
            for (int j = 0; j + 2 < chessboard[0].length; j++) {//列边界限制
                //选择3*3
                int counter = 0;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (chessboard[i + k][j + l] == 9) {
                            counter++;
                        }
                    }
                }
                if (counter == 9) {
                    test = false;
                    break Outer;
                }
            }
        }
        return test;
    }

    /**
     * 获取一个指定坐标的格子。
     * 注意请不要给一个棋盘之外的坐标哦~
     *
     * @param x 第x列
     * @param y 第y行
     * @return 该坐标的格子
     */
    public GridComponent getGrid(int x, int y) {
        try {
            return mineField[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void generateChessboardFromFile1(int[][] filechessboard){
        this.chessboard=new int[filechessboard.length][];
        chessboard=filechessboard;
        initialGameFromFile(chessboard);
    }
    public void generateChessboardFromFile2(int[][] filemineField){

    }

    private void initialGameFromFile(int[][] chessboard) {
        this.chessboard=chessboard;
    }
}
