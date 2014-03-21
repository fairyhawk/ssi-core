package com.yizhilu.os.ssicore.action;

import com.opensymphony.xwork2.Action;

import javax.servlet.http.HttpSession;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.Random;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

@SuppressWarnings("serial")
public class RandomCodeAction extends CommonAction {
    public static String RAND_CODE_NAME = "back_rand_code";
    public static String RAND_CODE_NAME_TEMP_CUS = "back_rand_code_temp_cus";
    private InputStream imageInputStream;

    @Override
    public String execute() throws Exception {
        genericRandomCode(RandomCodeAction.RAND_CODE_NAME);
        return Action.SUCCESS;
    }

    public String tempCusRandomCode() throws Exception {
        genericRandomCode(RandomCodeAction.RAND_CODE_NAME_TEMP_CUS);
        return Action.SUCCESS;
    }

    /**
     * 
     * @param randomCodeName
     * @throws IOException
     */
    private void genericRandomCode(String randomCodeName) throws IOException {
        this.getServletResponse().setContentType("image/jpeg");
        this.getServletResponse().setHeader("Pragma", "No-cache");
        this.getServletResponse().setHeader("Cache-Control", "no-cache");
        this.getServletResponse().setDateHeader("Expires", 0);
        HttpSession session = this.getServletRequest().getSession();

        int width = 60, height = 20;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics g = image.getGraphics();

        // 生成随机类
        Random random = new Random();

        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110),
                    20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 13 * i + 6, 16);
        }

        // 将认证码存入SESSION
        session.setAttribute(randomCodeName, sRand);
        // 图象生效
        g.dispose();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", output);
        this.imageInputStream = new ByteArrayInputStream(output.toByteArray());
        output.flush();
        output.close();
    }

    Color getRandColor(int fc, int bc) {
        // 给定范围获得随机颜色
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public InputStream getImageInputStream() {
        return imageInputStream;
    }

    public void setImageInputStream(InputStream imageInputStream) {
        this.imageInputStream = imageInputStream;
    }

}
