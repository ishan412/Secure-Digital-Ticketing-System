package com.example.securedigitalticketingsystemmain.core.controller;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.extra.qrcode.BufferedImageLuminanceSource;
import com.example.securedigitalticketingsystemmain.core.util.SaResult;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@RestController
public class QRCodeController {

    @Value("${aes.key}")
    String key;


    /**
     * 检测二维码是否有效
     *
     * @param qrc
     * @return
     */
    @PostMapping("/scan")
    public SaResult scan(MultipartFile qrc) throws Exception {
        BufferedImage image = ImageIO.read(qrc.getInputStream());
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        Reader reader = new MultiFormatReader();
        Result result = reader.decode(binaryBitmap);
        System.out.println(result.getText());
        //二维码里的内容 密文
        String content = result.getText();
        AES aes = SecureUtil.aes(key.getBytes());
        //解密为字符串 明文
        String decryptStr = aes.decryptStr(content, CharsetUtil.CHARSET_UTF_8);
        System.out.println(decryptStr);
        return SaResult.ok();
    }
}