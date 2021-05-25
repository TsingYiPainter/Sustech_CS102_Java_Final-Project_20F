package minesweeper;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class Music {
    private static Clip bgm;//背景乐
    private static Clip hit;//音效
    private static AudioInputStream ais;

    Music(){}
    public static void play(){
        try {
            bgm= AudioSystem.getClip();
            InputStream is=Music.class.getClassLoader().getResourceAsStream("music/前沢秀憲、禎清宏 - 迷路要塞1 (3D).wav");
            //getclassLoader得到当前类的加载器.getResourceAsStream加载资源，只能加载wav的音乐格式
            if (is != null) {
                ais=AudioSystem.getAudioInputStream(is);//获取输入流
            }
            bgm.open(ais);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        bgm.start();//开始播放
        bgm.loop(Clip.LOOP_CONTINUOUSLY);//循环播放
    }
    public static void stop()
    {
        if(ais!=null)
            bgm.close();
}
public static void play1(){
        try {
            hit= AudioSystem.getClip();
            InputStream is=Music.class.getClassLoader().getResourceAsStream("music/点击按钮-游戏ui(Button32)_爱给网_aigei_com.wav");
            //getclassLoader得到当前类的加载器.getResourceAsStream加载资源，只能加载wav的音乐格式
            if (is != null) {
                ais=AudioSystem.getAudioInputStream(is);//获取输入流
            }
            hit.open(ais);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        hit.start();//开始播放
}
    public static void play2(){
        try {
            hit= AudioSystem.getClip();
            InputStream is=Music.class.getClassLoader().getResourceAsStream("music/正确-正确胜利成功-游戏提示_系统提示_爱给网_aigei_com.wav");
            //getclassLoader得到当前类的加载器.getResourceAsStream加载资源，只能加载wav的音乐格式
            if (is != null) {
                ais=AudioSystem.getAudioInputStream(is);//获取输入流
            }
            hit.open(ais);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        hit.start();//开始播放
    }
    public static void play3(){
        try {
            hit= AudioSystem.getClip();
            InputStream is=Music.class.getClassLoader().getResourceAsStream("music/错误-s系统-错误-重-004_爱给网_aigei_com.wav");
            //getclassLoader得到当前类的加载器.getResourceAsStream加载资源，只能加载wav的音乐格式
            if (is != null) {
                ais=AudioSystem.getAudioInputStream(is);//获取输入流
            }
            hit.open(ais);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        hit.start();//开始播放
    }
    public static void play4(){
        try {
            hit= AudioSystem.getClip();
            InputStream is=Music.class.getClassLoader().getResourceAsStream("music/战斗胜利成功-游戏提示-综艺节目_爱给网_aigei_com.wav");
            //getclassLoader得到当前类的加载器.getResourceAsStream加载资源，只能加载wav的音乐格式
            if (is != null) {
                ais=AudioSystem.getAudioInputStream(is);//获取输入流
            }
            hit.open(ais);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        hit.start();//开始播放
    }

}
