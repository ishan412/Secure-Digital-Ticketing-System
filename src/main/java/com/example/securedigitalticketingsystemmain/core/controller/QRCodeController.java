package com.example.securedigitalticketingsystemmain.core.controller;

import com.example.securedigitalticketingsystemmain.core.util.AesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QRCodeController {

    @PostMapping("/scan")
    public String scanQRCode(@RequestParam("result") String encryptedResult) {
        String result = AesUtil.decrypt(encryptedResult);
        // 在这里处理解密后的二维码内容
        return "scan_result";
    }
}
