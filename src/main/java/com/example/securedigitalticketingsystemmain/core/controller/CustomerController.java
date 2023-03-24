package com.example.securedigitalticketingsystemmain.core.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.example.securedigitalticketingsystemmain.core.dao.CustomerRepository;
import com.example.securedigitalticketingsystemmain.core.entity.CustomerEntity;
import com.example.securedigitalticketingsystemmain.core.util.SaResult;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Base64;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Value("${aes.key}")
    String key;


    /**
     * 登录
     *
     * @param name 用户名
     * @param pwd  密码
     * @return
     */
    @GetMapping("/login")
    public SaResult login(String name, String pwd) {
        CustomerEntity one = customerRepository.one(name, pwd);
        if (one == null) {
            return SaResult.error("Invalid username or password!");
        }
        return SaResult.ok("Login Success");
    }

    /**
     * 注册
     *
     * @param name  用户名
     * @param pwd1  密码
     * @param pwd2
     * @param email 邮箱
     * @return
     */
    @GetMapping("/register")
    public SaResult register(HttpServletResponse response, String name, String pwd1, String pwd2, String email, String starttime, String endtime) throws Exception {
        if (pwd1 == null || pwd2 == null) {
            return SaResult.error("Please fill in the password");
        }
        if (!pwd1.equals(pwd2)) {
            return SaResult.error("Two passwords are inconsistent");
        }

        if (name == null || email == null) {
            return SaResult.error("Please fill in the end of the form");
        }
        CustomerEntity one = customerRepository.one(name);
        if (one != null) {
            return SaResult.error("Username already exists");
        }
        Random random = new Random();
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(random.nextInt());
        customerEntity.setName(name);
        customerEntity.setPassword(pwd1);
        customerEntity.setEmail(email);
        customerEntity.setStarttime(starttime);
        customerEntity.setEndtime(endtime);
        customerRepository.save(customerEntity);
        String json = JSONUtil.parse(customerEntity).toString();
        SaResult encryptedQR = getEncryptedQR(json);
        return SaResult.data(encryptedQR.getData());
    }

    /**
     * 文本信息转成二维码
     *
     * @return
     */
    @GetMapping("/getqr")
    public SaResult getEncryptedQR(String txt) throws Exception {
        // AES加密明文文本
        //构建
        AES aes = SecureUtil.aes(key.getBytes());
        // 加密为16进制表示
        String encryptedTxt = aes.encryptHex(txt);
        // 二维码尺寸
        int width = 350;
        int height = 350;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = qrCodeWriter.encode(encryptedTxt, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        // 将 BufferedImage 类型的图像转化成 Base64 编码字符串
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        String base64Encoded = Base64.getEncoder().encodeToString(imageBytes);
        return SaResult.ok().setData(base64Encoded);
    }


}