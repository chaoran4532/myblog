package com.blog.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerificationCodeUtils {
    // 验证码的字体库
    private static Font[] codeFont = { new Font("Times New Roman", Font.PLAIN, 30),
            new Font("Times New Roman", Font.PLAIN, 30), new Font("Times New Roman", Font.PLAIN, 30),
            new Font("Times New Roman", Font.PLAIN, 30) };

    // 验证码数字颜色库
    private static Color[] color = { Color.BLACK, Color.RED, Color.DARK_GRAY, Color.BLUE };

    // 验证码的字符库
    private static final String IMAGE_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // 验证码的宽度
    private static final Integer IMAGE_WIDTH = 120;

    private static StringBuilder codeNumbers;


    // 验证码的高度
   private static final Integer IMAGE_HEIGHT = 40;
   //生成背景随机颜色
    private static Color getRandColor(){
        Random random=new Random();
        int R=random.nextInt(256);
        int G=random.nextInt(256);
        int B= random.nextInt(256);
        Color color=new Color(R,G,B);
        return color;
    }
    //在画布上画一个字符，i表示第几个
    private static void drawCode(Graphics graphics,int i){
        graphics.setFont(codeFont[i]);
        graphics.setColor(color[i]);
        char number=IMAGE_CHAR.charAt((int)(Math.random()*IMAGE_CHAR.length()));
        codeNumbers.append(number);
        graphics.drawString(String.valueOf(number),20+20*i,30);
    }
    //返回Object数组，Object[0]为验证码字符串,Object[1]为验证码图片
    public static Object[] getVerificationCode(){
        codeNumbers=new StringBuilder();
        BufferedImage image=new BufferedImage(IMAGE_WIDTH,IMAGE_HEIGHT,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(getRandColor());
        graphics.fillRect(0,0,IMAGE_WIDTH,IMAGE_HEIGHT);
        for(int i=0;i<4;i++){
            drawCode(graphics,i);
        }
        System.out.println("生成验证码："+codeNumbers.toString());
        Object[] codeAndImg=new Object[2];
        codeAndImg[0]=codeNumbers.toString();
        codeAndImg[1]=image;
        return codeAndImg;
    }

}
