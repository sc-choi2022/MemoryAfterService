package com.ssafy.mas.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Date;

@Component
public class RandomStringGenerator {
    public String generateRandomPassword(int size) {
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        for (int i = 0; i < size; i++) {
            idx = sr.nextInt(charSet.length);
            sb.append(charSet[idx]);
        }

        return sb.toString();
    }

    public String generateRandomAuthCode(int size) {
        StringBuilder sb = new StringBuilder();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(System.currentTimeMillis());

        for (int i = 0; i < size; i++) {
            sb.append(sr.nextInt(10));
        }

        return sb.toString();
    }
}
