package com.example.demo.controller;

import com.example.demo.security.Crypto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("valtoken")
public class ValidasiTokenController {

    @GetMapping
    public String checkToken(HttpServletRequest request){
//        String token = request.getHeader("Authorization");
//        token = token.replace("Bearer ", "");
//        token = Crypto.performDecrypt(token);
//
//        String tokenArr [] = token.split("#");
//        for (int i = 0; i < tokenArr.length; i++) {
//            System.out.println(tokenArr[i]);
//        }
//        if (!tokenArr[0].equals("C1")){
//            return "Gagal Validasi 1";
//        }
//        Long longNow = System.currentTimeMillis();
//        longNow = (longNow-Long.parseLong(tokenArr[2]))/1000;
//        System.out.println("Saat ini : "+longNow + " Detik");
//        if (longNow >60){
//            return "Gagal Validasi 2";
//        }
        return "OK";
    }
}
