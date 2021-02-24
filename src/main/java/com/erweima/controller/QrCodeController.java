package com.erweima.controller;

import com.erweima.Utils.QRCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;


@Controller
public class QrCodeController {


    /**
     * 根据 text 生成 普通二维码
     */
    @RequestMapping(value = "/createCommonQRCode")
    public void createCommonQRCode(HttpServletResponse response, String text) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            //使用工具类生成二维码
            QRCodeUtil.encode(text, stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    /**
     * 根据 text 生成 带有logo二维码
     */
    @RequestMapping(value = "/createLogoQRCode")
    public void createLogoQRCode(HttpServletResponse response, String text) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            String logoPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()
                    + "templates" + File.separator + "logo.jpg";
            //使用工具类生成二维码
            QRCodeUtil.encode(text, logoPath, stream, true);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    /**
     * 生成二维码并存放到某文件夹下
     */
    @RequestMapping(value = "/createSaveQRCode")
    public void createSaveQRCode(HttpServletResponse response, String text) throws Exception {
        try {
            String logoPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()
                    + "templates" + File.separator + "logo.jpg";
            //String logoPath = "D:\\img\\1575892281966maxresdefault.jpg";	//二维码的logo图片
            String destPath = "D:\\img.jpg";                    //生成的二维码存储本地的地址
            //使用工具类生成二维码
            QRCodeUtil.encode(text, logoPath, destPath, true);
            System.out.println(QRCodeUtil.decode(destPath));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
