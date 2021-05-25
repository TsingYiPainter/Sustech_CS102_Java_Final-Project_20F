package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class List extends JFrame {
    public List(){

        ArrayList<String[]> listname=readlistdata();
        double[] percent=new double[listname.size()];
        for (int i = 0; i < listname.size(); i++) {
            int a=Integer.parseInt(listname.get(i)[1]);
            int b=Integer.parseInt(listname.get(i)[2]);
            percent[i]=(double)a/b;
        }

        String[] temp;
        double temple;
            for (int i = 0; i <percent.length-1 ; i++) {
                for (int j = 0; j < percent.length - 1 - i; j++) {

                    if (percent[j] <= percent[j + 1]) {
                        temple=percent[j];
                        percent[j]=percent[j+1];
                        percent[j+1]=temple;
                        temp = listname.get(j);
                        listname.remove(j);
                        listname.add(j+1,temp);
                    }
                }
            }
            String e="           排雷率榜单";
            String d=" 称号       "+"昵称"+"  "+"分数"+"  "+"总雷数";
            String a="雷区杀手： "+listname.get(0)[0]+"    "+listname.get(0)[1]+"     "+listname.get(0)[2];
            String b="扫雷达人： "+listname.get(1)[0]+"    "+listname.get(1)[1]+"     "+listname.get(1)[2];
            String c="扫雷小将： "+listname.get(2)[0]+"    "+listname.get(2)[1]+"     "+listname.get(2)[2];
        JPanel listsc=new JPanel();
        JLabel jLabel5=setlabel2(e,400,50,0,0);
        JLabel jLabel4=setlabel2(d,500,50,0,100);
        JLabel jLabel1=setlabel2(a,400,50,0,180);
        JLabel jLabel2=setlabel2(b,400,50,0,260);
        JLabel jLabel3=setlabel2(c,400,50,0,340);

        listsc.setOpaque(false);
        listsc.setLocation(150,50 );
        listsc.setSize(500, 380);
        listsc.add(jLabel5);
        listsc.add(jLabel4);
        listsc.add(jLabel1);
        listsc.add(jLabel2);
        listsc.add(jLabel3);
        listsc.setLayout(null);
        this.add(listsc);


        this.setIconImage(new ImageIcon("images/saolei.png").getImage());
        ImageIcon icon = new ImageIcon("images/list.jpg");
        JPanel jPanel=new JPanel();
        jPanel.setSize(840, 535);
        JLabel jlpic=new JLabel();
        jlpic.setIcon(icon);
        jPanel.add(jlpic);
        this.getContentPane().add(jPanel);

        this.setTitle("扫雷高玩榜");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(840, 535);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
    public ArrayList<String[]> readlistdata(){
        ArrayList<String[]>list=new ArrayList<>();
        try (FileReader reader = new FileReader("list.txt");
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            int counter = 0;
            //网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                String[] strings=new String[3];
                strings=line.split(" ");
                list.add(strings);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }return list;
    }

    public JLabel setlabel2(String name, int width, int hight, int x, int y) {
        JLabel label = new JLabel(name);
        label.setSize(width, hight);
        label.setLocation(x, y);
        label.setBackground(Color.white);
        label.setFont(new Font("黑体", Font.ITALIC, 28));
        label.setForeground(Color.white);
        label.setOpaque(false);
        return label;
    }
    }
