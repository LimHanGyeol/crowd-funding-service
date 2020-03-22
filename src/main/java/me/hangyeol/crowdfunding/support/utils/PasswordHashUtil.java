package me.hangyeol.crowdfunding.support.utils;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
public class PasswordHashUtil {

    public String getSha256(String password) {
        String convertPassword = "";
        try {
            MessageDigest encrypt = MessageDigest.getInstance("SHA-256");
            encrypt.update(password.getBytes());
            byte[] byteData = encrypt.digest();
            StringBuffer stringBuffer = new StringBuffer();
            int byteSize = byteData.length;

            for (int i = 0; i < byteSize; i++)
                stringBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

            convertPassword = stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            convertPassword = null;
        }
        return convertPassword;
    }
}
