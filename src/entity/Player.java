//该类主要用于设置游戏玩家，以及分数，失误次数的加减，需要其他类来调该类方法

package entity;

import java.util.Random;

public class Player {
    private static final Random ran = new Random();

    private String userName;
    private int score;
    private int mistake;

    /**
     * 通过特定名字初始化一个玩家对象。
     * @param userName 玩家的名字
     */
    public Player(String userName,int score,int mistake){
        this.userName = userName;
        this.score=score;
        this.mistake=mistake;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMistake(int mistake) {
        this.mistake = mistake;
    }

    /**
     * 通过默认名字初始化一个玩家对象。
     */
    public Player(int score,int mistake){
        userName = "无名英雄"+(ran.nextInt(9000)+1000);
    }

    /**
     * 为玩家加一分。
     */
    public void addScore(){
        score++;
    }

    /**
     * 为玩家扣一分。
     */
    public void costScore(){
        score--;
    }

    /**
     * 为玩家增加一次失误数。
     */
    public void addMistake() { mistake++; }


    public int getScore(){
        return score;
    }
    public String getUserName(){ return userName; }
    public int getMistake(){ return mistake; }

}
