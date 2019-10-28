package com.btsoft.jf.cloud.core.base.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.ValidateCodeDTO;
import com.btsoft.jf.cloud.core.base.service.IValidateCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码生成Service实现
 * @author jeo_cb
 * @date 2019/10/24 17:31
 **/
@Service
public class ValidateCodeServiceImpl implements IValidateCodeService {

    private final static Logger logger= LoggerFactory.getLogger(ValidateCodeServiceImpl.class);

    /** 图片宽度*/
    private final static int IMAGE_WIDTH=100;
    /** 图片高度*/
    private final static int IMAGE_HEIGHT=32;
    /** 干扰线数量*/
    private final static int LINE_NUMBER=40;
    /** 验证码长度*/
    private final static int CODE_LENGTH=4;
    /** 噪点率*/
    private final static float DOT_RATE=0.05f;
    /** 噪点率*/
    private final static String FORMAT_NAME="PNG";
    /** 随机码*/
    private final static String CODE_LIBRARYS="1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public String generateRandomCode() {
        return generateRandomCode(CODE_LENGTH);
    }

    @Override
    public String generateRandomCode(int codeLength) {
        StringBuilder code=new StringBuilder();
        Random random = new Random();
        int length=CODE_LIBRARYS.length();
        for (int i = 0; i < codeLength; i++) {
            code.append(CODE_LIBRARYS.charAt(random.nextInt(length)));
        }
        return code.toString();
    }

    @Override
    public BufferedImage generateValidateCodeImage(String code) {
        return generateValidateCodeImage(code,IMAGE_WIDTH, IMAGE_HEIGHT, LINE_NUMBER, DOT_RATE);
    }

    @Override
    public BufferedImage generateValidateCodeImage(String code, int width, int height, int lineNum, float dotRate) {
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics g=image.getGraphics();
        //刻画画布
        g.fillRect(0,0,width,height);
        //随机对象
        Random random = new Random();
        //随机设置字体大小
        g.setFont(getFont(random,height));
        //绘制干扰线
        drawLine(g,random,lineNum,width,height);
        //绘制噪点
        drawHot(image,random,width,height);
        //绘制验证码
        drawCode(g,random,code,width,height);
        //清除资源
        g.dispose();
        return image;
    }

    @Override
    public String writeValidateCodeImage(HttpServletResponse resp) {
        return writeValidateCodeImage(resp,new ValidateCodeDTO());
    }

    @Override
    public String writeValidateCodeImage(HttpServletResponse resp, ValidateCodeDTO dto) {
        buildResponse(resp);
        buildValidateCodeDTO(dto);
        String code=generateRandomCode();
        BufferedImage image=generateValidateCodeImage(code,dto.getWidth(),dto.getHeight(),dto.getLineNum(),dto.getDotRate());
        try {
            ImageIO.write(image,FORMAT_NAME,resp.getOutputStream());
            resp.getOutputStream().flush();
        }catch (IOException e){
            logger.error("验证码生成出错:",e);
        }finally {
            return code;
        }
    }

    private void buildResponse(HttpServletResponse resp){
        resp.setContentType("image/png");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Expire", "0");
        resp.setHeader("Pragma", "no-cache");
    }

    private void buildValidateCodeDTO(ValidateCodeDTO dto){
        if(dto==null){
            dto=new ValidateCodeDTO();
        }
       if(dto.getHeight()==null || dto.getHeight()==0){
           dto.setHeight(IMAGE_HEIGHT);
       }
       if(dto.getWidth()==null || dto.getWidth()==0){
           dto.setWidth(IMAGE_WIDTH);
       }
       if(dto.getLineNum()==null || dto.getLineNum()==0){
           dto.setLineNum(LINE_NUMBER);
       }
       if(dto.getDotRate()==null || dto.getDotRate()==0) {
           dto.setDotRate(DOT_RATE);
       }
    }


    /**
     * 获取随机颜色
     * @author jeo_cb
     * @param random 随机对象
     * @date 2019/10/24
     * @return 颜色
     */
    private Color getColor(Random random){
        return new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
    }

    /**
     * 获取字体
     * @author jeo_cb
     * @param random 随机对象
     * @param height 画布高度
     * @date 2019/10/24
     * @return 字体
     */
    private Font getFont(Random random,int height){
        return new Font("Times New Roman", Font.PLAIN, 30);
    }

    /**
     * 获取随机颜色rgb
     * @author jeo_cb
     * @date 2019/10/25
     * @param random 随机对象
     * @return rgb
     */
    private int getColorRgb(Random random){
        StringBuilder sb=new StringBuilder();
        sb.append(random.nextInt(256));
        sb.append(random.nextInt(256));
        sb.append(random.nextInt(256));
        return Integer.parseInt(sb.toString());
    }

    /**
     * 刻画干扰线
     * @author jeo_cb
     * @date 2019/10/24
     */
    private void drawLine(Graphics g, Random random,int lineNum,int width,int height){
        for (int i = 0; i <lineNum ; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width/8);
            int y2 = random.nextInt(height/4);
            g.setColor(getColor(random));
            g.drawLine(x1,y1,x1+x2,y1+y2);
        }
    }

    /**
     * 刻画噪点
     * @author jeo_cb
     * @param image image对象
     * @param random 随机对象
     * @param width 画布宽度
     * @param height 画布高度
     * @date 2019/10/24
     */
    private void drawHot(BufferedImage image, Random random, int width, int height){
        //根据画布大小计算噪点区域比例
        int area = (int) (DOT_RATE * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            image.setRGB(x, y, getColorRgb(random));
        }
    }

    /**
     * 刻画验证码
     * @author jeo_cb
     * @param g 画笔
     * @param random 随机对象
     * @param code 验证码
     * @param width 画布宽度
     * @param height 画布高度
     * @date 2019/10/24
     */
    private void drawCode(Graphics g,Random random,String code,int width,int height){
        //验证码长度
        int length=code.length();
        //每个验证码所占宽度
        int codeWidth=width/length;
        //验证码居中位置
        int xCenter=codeWidth/4;
        //遍历刻画验证码
        for (int i = 0; i <length ; i++) {
            //设置每个验证码的随机颜色
            g.setColor(getColor(random));
            //刻画验证码
            g.drawString(String.valueOf(code.charAt(i)),codeWidth*i+xCenter,30);
        }
    }
}
