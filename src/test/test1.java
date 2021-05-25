package test;

import javax.swing.*;
import java.awt.*;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class test1 {

    public static void main(String[] args) {
        readFile();
}

    public static void readFile() {
        String pathname = "output.txt"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader)// 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            int x=0;
            int y=0;
            int k=0;
            int counter=0;
            //网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                if(counter==0){
                    x=Integer.parseInt(line);
                    counter++;
                }
                else if (counter == 1) {
                    y=Integer.parseInt(line);
                    counter++;
                    k=3*y-2;
                }
                else if(counter==2) {
                    int[][] borad = new int[x][y];
                    for (int i = 0; i < x - 1; i++) {
                        String aline = line.substring(2 + 4 * i+k*i, 2 +4*i+ k * (i + 1));
                        String[] tokens = aline.split(", ");
                        for (int j = 0; j < y; j++) {
                            borad[i][j] = Integer.parseInt(tokens[j]);
                        }
                    }

                    counter++;
                };
        }
        }catch (IOException e) {
            e.printStackTrace();
        }


    /**
     * 写入TXT文件
     */
  /*  public static void writeFile() {
        try {
            File writeName = new File("output.txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write("我会写入文件啦1\r\n"); // \r\n即为换行
                out.write("我会写入文件啦2\r\n"); // \r\n即为换行
                out.write("我在学习写文件");
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/}}
/*读写文件
棋盘   整个棋盘的数字，格子的状态
      棋盘数字  int【xcount】【ycount】  周围雷数
      棋盘状态  int【xcount】【ycount】  状态 0未翻开 1翻开且是数字 2翻开且是旗子 3翻开且是雷
剩余雷数  int转string
onturn 谁的回合，已经走了几步，头像面板位置
音乐 开还是关
计分板 名字 得分 失误数
游戏计时器 每个玩家的剩余时间，每个玩家当前是暂停还是正在计时
 */
